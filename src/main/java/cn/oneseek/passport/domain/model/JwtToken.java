package cn.oneseek.passport.domain.model;

import java.util.Objects;

public class JwtToken {
    public String token;

    public static JwtToken of(String token) {
        JwtToken jwtToken = new JwtToken();
        {
            jwtToken.token = token;
        }
        return jwtToken;
    }

    @Override public String toString() {
        return token;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtToken jwtToken = (JwtToken) o;
        return Objects.equals(token, jwtToken.token);
    }

    @Override public int hashCode() {
        return Objects.hash(token);
    }
}