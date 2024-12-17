package com.beauty4u.backend.config.webSocket;

import com.beauty4u.backend.security.util.CustomUserDetailsService;
import com.beauty4u.backend.security.util.JwtUtil;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class StompHandler implements ChannelInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        // Authorization 헤더 추출
        String authHeader = accessor.getFirstNativeHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("Authorization header is missing or invalid.");
            return message; // 헤더가 없으면 로직 실행 중지
        }

        String token = authHeader.substring(7); // "Bearer " 제거
        System.out.println("Received Token: " + token);

        // JWT 검증
        if (jwtUtil.validateAccessToken(token)) {
            String userId = jwtUtil.getUserId(token);
            System.out.println("User ID: " + userId);


            // Principal 설정
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userId, authHeader,null);

            accessor.setUser(authentication); // Principal을 설정

            System.out.println("Principal 설정 완료: " + authentication);
        } else {
            System.out.println("Invalid Token.");
        }
        return message;
    }
}