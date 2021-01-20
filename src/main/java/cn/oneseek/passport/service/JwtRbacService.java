package cn.oneseek.passport.service;

import cn.oneseek.passport.ApplicationConfiguration;
import cn.oneseek.passport.domain.model.*;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashSet;

/**
 * 提供 Jwt 的生成、更新以及相应的缓存功能。
 */
@ApplicationScoped
public class JwtRbacService {

    @Inject
    ApplicationConfiguration appConfig;

    @Inject
    JWTParser parser;

    public JwtResult createWithUserSession(UserSession userSession) {
        return innerCreateWithUserSession(userSession);
    }


    public JwtResult refreshToken(String token) throws ParseException {
        JwtInfo jwtInfo = parseToken(token, appConfig.getRefreshTokenSecret());
        return innerCreateWithUserSession(jwtInfo.userSession);
    }

    private JwtResult innerCreateWithUserSession(UserSession userSession) {

        ZonedDateTime expiresAt =
                ZonedDateTime.now().plusMinutes(appConfig.getTokenJwtIssueMinutes());
        String accessToken = Jwt.issuer(appConfig.getJwtIssuer())
                .upn(UPN.USER.get())
                .expiresAt(expiresAt.toInstant())
                .groups(new HashSet<>(Collections.singletonList(UserRole.USER.get())))
                .claim("username", userSession.getUserName())
                .sign(); // just sign it, no content protection since access key doesn't contain sensitive data

        ZonedDateTime refreshExpiresAt =
                ZonedDateTime.now().plusMinutes(appConfig.getTokenJwtIssueRefreshMinutes());
        String refreshToken = Jwt.issuer(appConfig.getJwtIssuer())
                .upn(UPN.USER.get())
                .expiresAt(expiresAt.toInstant())
                .groups(new HashSet<>(Collections.singletonList(UserRole.USER.get())))
                .claim("username", userSession.getUserName())
                .jwe().encryptWithSecret(appConfig.getRefreshTokenSecret()); // sign and content protection since ak is claimed.

        return JwtResult.of(accessToken, refreshToken, expiresAt.toEpochSecond(), refreshExpiresAt.toEpochSecond());
    }

    @CacheResult(cacheName = "emailOnly-cache")
    public JwtResult createWithEmailOnly(@CacheKey String email) {
        ZonedDateTime expiresAt =
                ZonedDateTime.now().plusMinutes(appConfig.getTokenJwtIssueMinutes());

        String token = Jwt.issuer(appConfig.getJwtIssuer())
                .upn(UPN.EMAIL_ONLY.get())
                .claim(Claims.email.name(), email)
                .expiresAt(expiresAt.toInstant())
                .groups(new HashSet<>(Collections.singletonList(UserRole.EMAIL_ONLY.get())))
                .sign();

        return JwtResult.noRefreshOf(token, expiresAt.toEpochSecond());
    }

    private JwtInfo parseToken(String token, String secret) throws ParseException {
        JsonWebToken jwt = parser.decrypt(token, secret);

        if (!jwt.containsClaim(Claims.upn.name())) {
           throw new IllegalArgumentException("Should contain an upn in jwt");
        }

        String upnStr = jwt.getClaim(Claims.upn.name());
        UPN upn = UPN.judge(upnStr);
        ZonedDateTime expire =
                ZonedDateTime.ofInstant(
                        Instant.ofEpochSecond(jwt.getExpirationTime()), ZoneId.of("Z"));
        if (UPN.USER.is(upn)) {
            return JwtInfo.fromUserSession(UserSession.of(jwt.getClaim("username")), expire);
        } else {
            throw new IllegalArgumentException("Valid upn is absent in token - " + upnStr);
        }
    }
}
