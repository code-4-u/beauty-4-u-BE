package com.beauty4u.backend.teamspace.query.service;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMessageRepository;
import com.beauty4u.backend.teamspace.query.dto.chatMessage.ChatMessageResDto;
import com.beauty4u.backend.teamspace.query.dto.teamspace.TeamSpaceDetailsDto;
import com.beauty4u.backend.teamspace.query.dto.teamspace.TeamSpaceUserInfoDto;
import com.beauty4u.backend.teamspace.query.mapper.TeamSpaceQueryMapper;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import com.beauty4u.backend.user.query.mapper.DeptQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamSpaceQueryService {

    private final TeamSpaceQueryMapper teamSpaceQueryMapper;
    private final DeptQueryMapper deptQueryMapper;
    private final UserRepository userRepository;
    private final ChatMessageRepository chatMessageRepository;

    // 부서 코드로 채팅참여자 정보 조회
    @Transactional(readOnly = true)
    public List<TeamSpaceUserInfoDto> findAllTeamSpaceUser(String deptCode) {
        return teamSpaceQueryMapper.findAllTeamSpaceUser(deptCode);
    }

    // 팀스페이스 id로 부서 코드 조회
    @Transactional(readOnly = true)
    public String getDeptCodeByTeamspaceId(String teamspaceId) {
        return teamSpaceQueryMapper.findTeamSpaceDeptCode(teamspaceId);
    }

    // 부서 코드로 팀스페이스 id 조회
    @Transactional(readOnly = true)
    public Long getMyTeamSpaceIdByDeptCode(String deptCode) {
        return teamSpaceQueryMapper.findTeamSpaceId(deptCode);
    }

    // 팀스페이스 세부 정보 조회
    @Transactional(readOnly = true)
    public TeamSpaceDetailsDto getTeamSpaceDetails(Long teamspaceId, String deptCode) {
        // 1. 부서 이름 조회
        String deptName = getDeptNameByCode(deptCode);

        // 2. 참여자 정보 조회
        List<TeamSpaceUserInfoDto> participants = findAllTeamSpaceUser(deptCode);

        // 3. 채팅 메시지와 사용자 이름 매핑
        List<ChatMessageResDto> messages = getChatMessagesWithUserNames(teamspaceId);

        return new TeamSpaceDetailsDto(deptName, participants, messages);
    }

    private String getDeptNameByCode(String deptCode) {
        return deptQueryMapper.findDeptName(deptCode).getDeptName();
    }

    private List<ChatMessageResDto> getChatMessagesWithUserNames(Long teamspaceId) {
        // 1. 채팅 메시지 조회
        List<ChatMessage> chatMessages = chatMessageRepository.findByTeamspaceId(teamspaceId);

        // 2. userCode 목록 추출 및 유저 정보 조회
        Map<String, String> userCodeToNameMap = getUserCodeToNameMap(
                chatMessages.stream()
                        .map(ChatMessage::getUserCode)
                        .distinct()
                        .toList()
        );

        // 3. 채팅 메시지와 사용자 이름 매핑
        return chatMessages.stream()
                .map(chatMessage -> {
                    String userName = userCodeToNameMap.getOrDefault(chatMessage.getUserCode(), "알수 없음");
                    return convertToDto(chatMessage, userName);
                })
                .toList();
    }

    private Map<String, String> getUserCodeToNameMap(List<String> userCodes) {
        return userRepository.findAllByUserCodeIn(userCodes).stream()
                .collect(Collectors.toMap(UserInfo::getUserCode, UserInfo::getUserName));
    }

    private ChatMessageResDto convertToDto(ChatMessage chatMessage, String userName) {
        return ChatMessageResDto.builder()
                .messageId(chatMessage.getId().toHexString())
                .teamspaceId(chatMessage.getTeamspaceId())
                .userCode(chatMessage.getUserCode())
                .userName(userName)
                .messageStatus(chatMessage.getMessageStatus().toString())
                .messageContent(chatMessage.getMessageContent())
                .messageCreatedTime(chatMessage.getMessageCreatedTime().toString())
                .messageDeletedTime(chatMessage.getMessageDeletedTime() != null
                        ? chatMessage.getMessageDeletedTime().toString()
                        : null)
                .build();
    }
}
