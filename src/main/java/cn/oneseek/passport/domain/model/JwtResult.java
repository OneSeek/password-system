package cn.oneseek.passport.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class JwtResult {
    @JsonProperty("access_token")
    public String accessToken;

    @JsonProperty("refresh_token")
    public String refreshToken;

    @JsonProperty("expires_in")
    public Long expireTime;

    @JsonProperty("refresh_expires_in")
    public Long refreshExpireTime;

    @JsonProperty("token_type")
    public String tokeType = "bearer";

    public static JwtResult of(String token, String refreshToken, Long expireTime, Long refreshExpireTime) {
        JwtResult info = new JwtResult();
        {
            info.accessToken = token;
            info.refreshToken = refreshToken;
            info.expireTime = expireTime;
            info.refreshExpireTime = refreshExpireTime;
        }
        return info;
    }

    public static JwtResult noRefreshOf(String token, Long expireTime) {
        JwtResult info = new JwtResult();
        {
            info.accessToken = token;
            info.expireTime = expireTime;
        }
        return info;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtResult jwtResult = (JwtResult) o;
        return Objects.equals(accessToken, jwtResult.accessToken) &&
                Objects.equals(expireTime, jwtResult.expireTime);
    }

    @Override public int hashCode() {
        return Objects.hash(accessToken, expireTime);
    }
}
