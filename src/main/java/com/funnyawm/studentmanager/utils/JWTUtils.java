package com.funnyawm.studentmanager.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.funnyawm.studentmanager.model.Admin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private static final Logger log = LogManager.getLogger(JWTUtils.class);
    public static final String KEY = "motherfucker";

    public static String genToken(@NotNull Admin admin) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", admin.getId());
        claims.put("name", admin.getName());
        claims.put("password", admin.getPassword());
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .sign(Algorithm.HMAC256(KEY));
    }
    public static boolean verifyToken(String token) {
        try {
            DecodedJWT decoder = JWT.require(Algorithm.HMAC256(KEY)).build().verify(token);
            if (decoder.getClaim("claims") != null) {
                return true;
            }
        } catch (Exception e) {
            log.error(e);
            return false;
        }
        return false;
    }
}
