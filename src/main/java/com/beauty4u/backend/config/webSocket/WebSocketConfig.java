package com.beauty4u.backend.config.webSocket;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/* WebSocket 설정 */
@RequiredArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final StompHandler stompHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")
                .setAllowedOriginPatterns("*")
                .withSockJS();
        registry.addEndpoint("/chat")
                .setAllowedOriginPatterns("*");
    }

    // 메세지 브로커를 구성하는 메서드
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub"); // 구독할 경로 설정
        config.setApplicationDestinationPrefixes("/pub"); // 메세지 전송시 접두사(클라이언트가 메세지 전송)
    }

    // 클라이언트 인바운드 채널을 구성하는 메서드
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(stompHandler);
//    }

    // STOMP에서 64KB 이상의 데이터 전송을 못하는 문제 해결
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry.setMessageSizeLimit(160 * 64 * 1024);
        registry.setSendTimeLimit(100 * 10000);
        registry.setSendBufferSizeLimit(3 * 512 * 1024);
    }
}
