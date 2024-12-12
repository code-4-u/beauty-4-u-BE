package com.beauty4u.backend.config.webSocket;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/* WebSocket 설정 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

//    private final StompHandler stompHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat") // 모든 Origin 허용 -> 배포시에는 보안을 위해 Origin을 정확히 지정
                .setAllowedOriginPatterns("*");
        registry.addEndpoint("/chat")
                .setAllowedOriginPatterns("*")
                .withSockJS(); // SockJS 사용가능 설정

    }

    // 메세지 브로커를 구성하는 메서드
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // 메시지를 받을 때, 경로를 설정해주는 함수
        // 해당 경로 /sub으로 SimpleBroker를 등록한다.
        // SimpleBroker는 해당하는 경로로 구독하는 client에게 메시지를 전달하는 간단한 작업을 수행한다.
        registry.enableSimpleBroker("/sub");

        // 메시지를 보낼 때, 관련 경로를 설정해주는 함수
        registry.setApplicationDestinationPrefixes("/pub");

    }

    // 클라이언트 인바운드 채널을 구성하는 메서드
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        // stompHandler를 인터셉터로 등록하여 STOMP 메시지 핸들링을 수행
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
