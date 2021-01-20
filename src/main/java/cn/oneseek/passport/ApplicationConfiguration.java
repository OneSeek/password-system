package cn.oneseek.passport;

import io.quarkus.arc.config.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ConfigProperties(prefix = "app")
public interface ApplicationConfiguration {
    @ConfigProperty(name = "token.issue.jwt.lifespan.minutes")
    Long getTokenJwtIssueMinutes();

    @ConfigProperty(name = "token.issue.jwt.refresh-lifespan.minutes")
    Long getTokenJwtIssueRefreshMinutes();

    String getRefreshTokenSecret();

    @ConfigProperty(name = "jwt.issuer")
    String getJwtIssuer();

    @ConfigProperty
    String getVersion();

}
