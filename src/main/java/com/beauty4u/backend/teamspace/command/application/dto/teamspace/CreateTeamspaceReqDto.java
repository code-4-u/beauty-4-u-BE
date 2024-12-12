package com.beauty4u.backend.teamspace.command.application.dto.teamspace;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTeamspaceReqDto {

    // 채팅방에 참여하고 있는 회원의 정보 (채팅방 id로 참여자 정보 조회)
    private String userCode; // 회원 id

    private String userName; // 회원 이름

    private Long roleId; // 회원 권한

    private String deptCode; // 부서 코드
}
