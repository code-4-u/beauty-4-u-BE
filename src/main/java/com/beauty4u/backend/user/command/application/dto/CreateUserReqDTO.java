package com.beauty4u.backend.user.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserReqDTO {

    @NotBlank(message = "사원 번호(아이디)는 필수 입력사항입니다.")
    private String userCode;
    private String jobCode;
    private String deptCode;
    private Long userRoleId;
    private String userName;
    private String email;
    private String phone;
}