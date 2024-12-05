package com.beauty4u.backend.security.filter;

import com.beauty4u.backend.security.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private static final String ACCESS_TOKEN_HEADER = "Authorization";
    private static final String REFRESH_TOKEN_HEADER = "Refresh-Token";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String bearerToken = request.getHeader(ACCESS_TOKEN_HEADER);
        String accessToken = null;
        String refreshToken = request.getHeader(REFRESH_TOKEN_HEADER);

        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            accessToken = bearerToken.substring(7);
        }

        log.info("Access token: {}", accessToken);
        log.info("Refresh token: {}", refreshToken);

        if (jwtUtil.validateAccessToken(accessToken)) {
            jwtUtil.setAuthenticationToContext(accessToken);
        } else if (refreshToken != null) {
            try {
                String newAccessToken = jwtUtil.regenerateAccessToken(refreshToken);
                jwtUtil.setAuthenticationToContext(newAccessToken);
                String newRefreshToken = jwtUtil.generateRefreshToken(jwtUtil.getUserId(newAccessToken));

                response.setHeader(ACCESS_TOKEN_HEADER, newAccessToken);
                response.setHeader(REFRESH_TOKEN_HEADER, newRefreshToken);

                log.info("New Access token: {}", newAccessToken);
                log.info("New Refresh token: {}", newRefreshToken);
            } catch (Exception e) {
                log.error("Failed to regenerate access token", e);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired tokens");
                return;
            }
        } else {
            log.error("Both tokens are invalid");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please login again");
            return;
        }

        filterChain.doFilter(request, response);
    }
}