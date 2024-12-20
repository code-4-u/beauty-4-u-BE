package com.beauty4u.backend.teamspace.query.dto.teamspace;

import com.beauty4u.backend.teamspace.query.dto.chatMessage.ChatMessageResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamSpaceDetailsDto {
    private String deptName;
    private List<TeamSpaceUserInfoDto> participants;
    private List<ChatMessageResDto> messages;
}