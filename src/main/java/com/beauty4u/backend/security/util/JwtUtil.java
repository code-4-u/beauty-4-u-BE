package com.beauty4u.backend.security.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private final Key key;
    private final CustomUserDetailsService customUserDetailsService;

    @Value("${token.access-token-expiration-time}")
    private long accessTokenValidityTime;

    @Value("${token.refresh-token-expiration-time}")
    private long refreshTokenValidityTime;

    public JwtUtil(
            @Value("${token.secret}") String secretKey,
            CustomUserDetailsService customUserDetailsService
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.customUserDetailsService = customUserDetailsService;
    }

    public boolean validateToken(String token) {

        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT Token claims empty {}", e);
        }

        return false;
    }

    public Authentication getAuthentication(String token) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(this.getUserId(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String getUserId(String token) {
        return parseClaims(token).getSubject();
    }

    // Access Token 생성
    public String generateAccessToken(Authentication authentication) {

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidityTime))
                .signWith(key)
                .compact();
    }

    // Refresh Token 생성
    public String generateRefreshToken(String userId) {

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidityTime))
                .signWith(key)
                .compact();
    }

    // Refresh Token으로 새로운 Access Token 생성
    public String regenerateAccessToken(String refreshToken) {
        try {
            // Refresh Token 검증
            if (!validateToken(refreshToken)) {
                throw new JwtException("Invalid refresh token");
            }

            Authentication authentication = getAuthentication(refreshToken);

            // 새로운 Access Token 생성
            return generateAccessToken(authentication);

        } catch (Exception e) {
            log.error("Failed to regenerate access token", e);
            throw e;
        }
    }
}
