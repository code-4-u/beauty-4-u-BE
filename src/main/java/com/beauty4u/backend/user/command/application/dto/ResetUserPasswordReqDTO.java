package com.beauty4u.backend.user.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetUserPasswordReqDTO {

    @NotNull(message = "사원번호는 필수 입력값입니다.")
    private String userCode;

    @NotNull(message = "이름은 필수 입력값입니다.")
    private String userName;

    @NotNull(message = "이메일은 필수 입력값입니다.")
    private String email;
}