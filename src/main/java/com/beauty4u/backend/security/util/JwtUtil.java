package com.beauty4u.backend.security.util;

import com.beauty4u.backend.config.redis.RedisService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private final Key key;
    private final CustomUserDetailsService customUserDetailsService;
    private final RedisService redisService;

    @Value("${token.access-token-expiration-time}")
    private long accessTokenValidityTime;

    @Value("${token.refresh-token-expiration-time}")
    private long refreshTokenValidityTime;

    private static final String JWT_PREFIX = "JWT_";

    public JwtUtil(
            @Value("${token.secret}") String secretKey,
            CustomUserDetailsService customUserDetailsService,
            RedisService redisService
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.customUserDetailsService = customUserDetailsService;
        this.redisService = redisService;
    }

    /* Access Token 검증 */
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            /* Redis에서 블랙리스트 토큰 확인 */
            String userId = getUserId(token);
            String blacklistKey = JWT_PREFIX + userId;
            String blacklistToken = redisService.getValues(blacklistKey)
                    .orElse(null);

            if (blacklistToken != null && blacklistToken.equals(token)) {
                log.info("블랙리스트에 있는 액세스 토큰입니다.");
                return false;
            }

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

    /* Refresh Token 검증 */
    public boolean validateRefreshToken(String token) {

        String userId = null;
        String redisKey = null;

        try {
            userId = getUserId(token);
            redisKey = JWT_PREFIX + userId;

            String storedToken = redisService.getValues(redisKey)
                    .orElse(null);

            return storedToken != null && storedToken.equals(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT Token claims empty {}", e);
        } finally {
            /* 검증 시도한 유저의 (Redis에 저장된) 리프레시 토큰 삭제 */
            if (userId != null) {
                redisKey = JWT_PREFIX + userId;
                redisService.deleteValues(redisKey);
            }
        }

        return false;
    }

    public Authentication getAuthentication(String token) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(this.getUserId(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserId(String token) {
        return parseClaims(token).getSubject();
    }

    public void setAuthenticationToContext(String token) {
        Authentication authentication = getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /* Access Token 생성 */
    public String generateAccessToken(Authentication authentication) {

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidityTime))
                .signWith(key)
                .compact();
    }

    /* Refresh Token 생성 및 Redis 저장 */
    public String generateRefreshToken(String userId) {

        String refreshToken = Jwts.builder()
                                .setSubject(userId)
                                .setIssuedAt(new Date())
                                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidityTime))
                                .signWith(key)
                                .compact();

        Date expirationDate = parseClaims(refreshToken).getExpiration();
        long remainingTime = expirationDate.getTime() - System.currentTimeMillis();

        redisService.setValues(JWT_PREFIX + userId,
                refreshToken, Duration.ofMillis(remainingTime));

        return refreshToken;
    }

    /* Refresh Token으로 새로운 Access Token 생성 */
    public String regenerateAccessToken(String refreshToken) {
        try {

            /* Refresh Token 검증 */
            if (!validateRefreshToken(refreshToken)) {
                throw new JwtException("Invalid refresh token");
            }

            Authentication authentication = getAuthentication(refreshToken);

            /* 새로운 Access Token 생성 */
            return generateAccessToken(authentication);

        } catch (Exception e) {
            log.error("Failed to regenerate access token", e);
            throw e;
        }
    }
}
