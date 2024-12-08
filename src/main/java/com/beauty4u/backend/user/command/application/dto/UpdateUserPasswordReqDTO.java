package com.beauty4u.backend.user.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserPasswordReqDTO {

    @NotNull(message = "비밀번호는 필수 입력값입니다.")
    private String userPassword;
}
