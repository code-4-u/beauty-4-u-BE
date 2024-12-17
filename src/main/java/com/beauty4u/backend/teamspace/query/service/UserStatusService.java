package com.beauty4u.backend.teamspace.query.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserStatusService {

    private final SimpMessagingTemplate messagingTemplate;

    // teamspaceId -> (userId -> isOnline)
    private final Map<Long, Map<String, Boolean>> teamspaceUserStatus = new ConcurrentHashMap<>();

    public UserStatusService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void updateUserStatus(Long teamspaceId, String userId, boolean isOnline) {
        teamspaceUserStatus
                .computeIfAbsent(teamspaceId, k -> new ConcurrentHashMap<>())
                .put(userId, isOnline);

        // 브로드캐스트: 상태 변경 알림
        messagingTemplate.convertAndSend(
                "/sub/teamspace/status" + teamspaceId,
                new UserStatus(userId, isOnline)
        );
    }

    public void updateUserStatusForAllTeams(String userId, boolean isOnline) {
        teamspaceUserStatus.forEach((teamspaceId, users) -> {
            if (users.containsKey(userId)) {
                users.put(userId, isOnline);
                messagingTemplate.convertAndSend(
                        "/sub/teamspace/status" + teamspaceId,
                        new UserStatus(userId, isOnline)
                );
            }
        });
    }

    public static class UserStatus {
        public String userId;
        public boolean isOnline;

        public UserStatus(String userId, boolean isOnline) {
            this.userId = userId;
            this.isOnline = isOnline;
        }
    }

}
