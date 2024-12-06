package com.beauty4u.backend.user.command.application.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.common.util.MailUtil;
import com.beauty4u.backend.config.redis.RedisService;
import com.beauty4u.backend.user.command.application.dto.CreateUserRequest;
import com.beauty4u.backend.user.command.application.dto.LoginUserReqDTO;
import com.beauty4u.backend.user.command.domain.service.UserDomainService;
import com.beauty4u.backend.user.query.dto.FindUserCodeReqDTO;
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
    private final MailUtil mailUtil;

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

    public void findUserCode(FindUserCodeReqDTO findUserCodeReqDTO) {

        String name = findUserCodeReqDTO.getUserName();
        String phone = findUserCodeReqDTO.getPhone();
        String email = findUserCodeReqDTO.getEmail();

        String userCode = userDomainService.findUserCode(name, phone, email);

        String title = "[beauty4u] 요청하신 사원번호(아이디) 안내";
        String body = String.format(
                "%s님, 안녕하세요.\n" +
                        "요청하신 사원번호(아이디)를 안내해 드립니다.\n\n" +
                        "사원번호: %s\n\n" +
                        "감사합니다.\n" +
                        "beauty4u 드림",
                name, userCode
        );

        try {
            mailUtil.sendEmail(email, title, body);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.EMAIL_SEND_FAIL);
        }
    }
}