package com.beauty4u.backend.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/* WebSocket 설정 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/connection")
                .setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // 메시지를 받을 때, 경로를 설정해주는 함수
        // 내장 브로커를 사용하겠다는 설정
        // /sub가 api에 prefix로 붙은 경우, messageBroker가 해당 경로를 가로채 처리
        // 해당 경로 /sub으로 SimpleBroker를 등록한다.
        // SimpleBroker는 해당하는 경로로 구독하는 client에게 메시지를 전달하는 간단한 작업을 수행한다.
        registry.enableSimpleBroker("/sub");

        // 메시지를 보낼 때, 관련 경로를 설정해주는 함수
        // client에서 SEND 요청을 처리
        // 클라이언트가 메시지를 보낼 때, 경로 앞에 /pub가 붙어있으면 Broker로 보내진다.
        registry.setApplicationDestinationPrefixes("/pub");

    }


}
