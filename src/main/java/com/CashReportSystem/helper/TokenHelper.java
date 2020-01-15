package com.CashReportSystem.helper;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class TokenHelper {
    private final byte[] secretKey = {-1, -9, -51, 11, -42, 120, 66, -7, 38, -57, 36, -65, -86, 93, 73, 97, 112, 23, 48, 2, -1, 36, -35, -56, -11, 82, 34, -15, 3, -102, -86, -56};
    private Key Secret = Keys.hmacShaKeyFor(secretKey);

    public String tokenCryptBuilder(String subject) {
        return Jwts.builder().setIssuer("ACDP")
                .setSubject(subject)
               // .setExpiration(Date.valueOf(LocalDateTime.now())) Need to add time TODO and catch error
                .signWith(Secret, SignatureAlgorithm.HS256)
                .compact();
    }

    public String tokenDeCrypt(String token) {
        Jws<Claims> jws;
        try {
            jws = Jwts.parser().setSigningKey(Secret).parseClaimsJws(token);
            return jws.getBody().getSubject();
        } catch (JwtException e) {
            //don't trust the JWT!
            throw new RuntimeException(e);
        }
    }
}