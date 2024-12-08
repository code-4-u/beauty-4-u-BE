package com.beauty4u.backend.user.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResetUserPasswordReqDTO {

    @NotNull(message = "사원번호는 필수 입력값입니다.")
    private String userCode;
}
