//package com.beauty4u.backend.config.webSocket;
//
//import com.beauty4u.backend.teamspace.query.service.UserStatusService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.event.EventListener;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.messaging.SessionConnectEvent;
//import org.springframework.web.socket.messaging.SessionDisconnectEvent;
//
//@RequiredArgsConstructor
//@Component
//public class WebSocketEventListener {
//
//    private final UserStatusService userStatusService;
//
//    // 클라이언트가 연결했을 때 실행
//    @EventListener
//    public void handleWebSocketConnectListener(SessionConnectEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//
//        System.out.println("headerAccessor: " + headerAccessor);
//        System.out.println(headerAccessor.getSessionId());
//        System.out.println(headerAccessor.getUser());
//
//        if (headerAccessor.getUser() != null) {
//            String userId = headerAccessor.getUser().getName();
//            System.out.println("WebSocket 연결 성공: User ID = " + userId);
//        } else {
//            System.out.println("WebSocket 연결 실패: Principal is null.");
//        }
//
//        String userId = headerAccessor.getUser().getName();
//        System.out.println("User Connected: " + userId);
//        Long teamspaceId = 1L;
//
//        // 예: 접속 알림을 특정 채널에 발송
//        userStatusService.updateUserStatus(teamspaceId, userId, true);
//    }
//
//    // 클라이언트가 연결 해제했을 때 실행
//    @EventListener
//    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//
//        String userId = headerAccessor.getUser().getName();
//        System.out.println("User Disconnected: " + userId);
//
//        Long teamspaceId = 1L;
//
//        // 예: 접속 해제 알림을 특정 채널에 발송
//        userStatusService.updateUserStatus(teamspaceId, userId, false);
//    }
//}
