package cn.oneseek.passport.security.jwt;

import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

/**
 * @program: passport-system
 * @description:
 * @author: chuang
 * @create: 2021/1/15 下午5:16
 */
public class GenerateToken {
    /**
     * Generate JWT token
     */
    public static void main(String[] args) {
        String token =
                Jwt.issuer("https://passport.oneseek.cn/jwt-rbac")
                        .upn("jdoe@quarkus.io")
                        .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                        .claim(Claims.birthdate.name(), "2001-07-13")
                        .sign();
        System.out.println(token);
    }
}
