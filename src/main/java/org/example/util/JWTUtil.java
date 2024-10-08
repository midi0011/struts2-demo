package org.example.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtil {
    private static final String SECRET_KEY = "BcnXNHjoLoOlZ7tQcc+K0vxUEO0A6dz1Ohm+qNZaXJE=";

    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 60000; // in milisecond
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 600000;

    public static String generateAccessToken(String username) {
        return generateToken(username, "ACCESS", ACCESS_TOKEN_EXPIRATION_TIME);
    }

    public static String generateRefreshToken(String username) {
        return generateToken(username, "REFRESH", REFRESH_TOKEN_EXPIRATION_TIME);
    }


    public static String generateToken(String username, String tokenType, long expirationTime){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("tokenType", tokenType)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean isTokenExpired(Date expirationDate) {
        return expirationDate.before(new Date());
    }

    public static String getUsernameFromToken(String token) {
        Claims claims = validateToken(token);
        return claims.getSubject();
    }

    public static String getTokenType(String token) {
        Claims claims = validateToken(token);
        return claims.get("tokenType", String.class);
    }
}
