package com.beauty4u.backend.teamspace.query.dto.teamspace;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamSpaceUserInfoDto {

    // 채팅방에 참여하고 있는 회원의 정보 (채팅방 id로 참여자 정보 조회)
    private String userCode; // 회원 id

    private String userName; // 회원 이름

    private String email; // 유저 이메일

    private String userRoleName; // 회원 권한

}
