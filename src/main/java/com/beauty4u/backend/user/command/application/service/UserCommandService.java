package com.beauty4u.backend.user.command.application.service;

import com.beauty4u.backend.config.redis.RedisService;
import com.beauty4u.backend.user.command.application.dto.CreateUserRequest;
import com.beauty4u.backend.user.command.application.dto.LoginUserReqDTO;
import com.beauty4u.backend.user.command.domain.service.UserDomainService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private static final String JWT_PREFIX = "JWT_";
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Value("${token.secret}")
    private String key;

    private final UserDomainService userDomainService;
    private final RedisService redisService;

    public void saveUser(CreateUserRequest newUser) {

        userDomainService.saveUser(newUser);
    }

    public Authentication loginUser(LoginUserReqDTO loginUserReqDTO) {

        UsernamePasswordAuthenticationToken authenticationToken = loginUserReqDTO.toAuthentication();

        return authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    }

    public void logoutUser(String userCode, String accessToken) {

        Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(accessToken)
                        .getBody();

        Date expirationDate = claims.getExpiration();
        long remainingTime = expirationDate.getTime() - System.currentTimeMillis();

        redisService.setValues(JWT_PREFIX + userCode,
                accessToken, Duration.ofMillis(remainingTime));
    }
}