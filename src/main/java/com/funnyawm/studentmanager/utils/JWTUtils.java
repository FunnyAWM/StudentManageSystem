package com.funnyawm.studentmanager.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.funnyawm.studentmanager.model.Admin;
import com.funnyawm.studentmanager.model.Keys;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    public static String genToken(Admin admin) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", admin.getId());
        claims.put("name", admin.getName());
        claims.put("password", admin.getPassword());
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .sign(Algorithm.HMAC256(Keys.KEY));
    }
    public static boolean verifyToken(String token) {
        try {
            DecodedJWT decoder = JWT.require(Algorithm.HMAC256(Keys.KEY)).build().verify(token);
            Map<String, Claim> claims = decoder.getClaims();
            Claim claim = claims.get("claims");
            Map<String, Object> claimsMap = claim.asMap();
            int id = Integer.parseInt((String.valueOf(claimsMap.get("id"))));
            String name = claimsMap.get("name").toString();
            String password = claimsMap.get("password").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static String getUsernameFromToken(String token) {
        if (verifyToken(token)) {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(Keys.KEY)).build().verify(token);
            Claim claim = jwt.getClaim("claims");
            Map<String, Object> claimsMap = claim.asMap();
            return claimsMap.get("name").toString();
        }
        return null;
    }
}
