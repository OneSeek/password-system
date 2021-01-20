package cn.oneseek.passport.domain.model;

import java.time.ZonedDateTime;
import java.util.Objects;

public class JwtInfo {

    public UPN upn;

    public UserSession userSession;
    public Email email;

    public ZonedDateTime expireTime;

    public static JwtInfo fromEmail(Email email, ZonedDateTime expireTime) {
        JwtInfo info = new JwtInfo();
        {
            info.upn = UPN.EMAIL_ONLY;
            info.email = email;
            info.expireTime = expireTime;
        }
        return info;
    }

    public static JwtInfo fromUserSession(UserSession userSession, ZonedDateTime expireTime) {
        JwtInfo info = new JwtInfo();
        {
            info.upn = (UPN.USER);
            info.userSession = (userSession);
            info.expireTime = (expireTime);
        }
        return info;
    }

    @Override public String toString() {
        return "JwtInfo{" +
                "upn=" + upn +
                ", user=" + userSession +
                ", email=" + email +
                ", expireTime=" + expireTime +
                '}';
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtInfo jwtInfo = (JwtInfo) o;
        return upn == jwtInfo.upn &&
                Objects.equals(userSession, jwtInfo.userSession) &&
                Objects.equals(email, jwtInfo.email) &&
                Objects.equals(expireTime, jwtInfo.expireTime);
    }

    @Override public int hashCode() {
        return Objects.hash(upn, userSession, email, expireTime);
    }
}
