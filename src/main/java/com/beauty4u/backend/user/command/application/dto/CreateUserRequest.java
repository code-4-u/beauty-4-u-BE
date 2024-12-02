package com.beauty4u.backend.user.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class CreateUserRequest {

    @NotBlank(message = "사원 번호(아이디)는 필수 입력사항입니다.")
    private String userCode;

    private String jobCode;
    private String deptCode;
    private Long userRole;
    private String userName;
    private String email;
    private String phone;
    private String userPassword;
    private Instant userCreatedDate;
    private Instant userExpiredDate;
    private Character userExpiredYn;

}