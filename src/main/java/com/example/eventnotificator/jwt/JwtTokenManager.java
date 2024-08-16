package com.example.eventnotificator.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Service
public class JwtTokenManager {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenManager.class);

    private final Key signKey;

    public JwtTokenManager(@Value("${jwt.key}") String signKey) {
        this.signKey = new SecretKeySpec(signKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
    }

    public boolean validateToken(String token) {
        log.info("Validating JWT token");
        try {
            Jwts.parser()
                    .setSigningKey(signKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public String getLoginFromToken(String token) {
        log.info("Getting login from token");
        return Jwts.parser()
                .setSigningKey(signKey)
                .build()
                .parseClaimsJws(token)
                .getPayload()
                .getSubject();
    }

    public String getRoleFromToken(String token) {
        log.info("Getting role from token");
        return Jwts.parser()
                .setSigningKey(signKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}
