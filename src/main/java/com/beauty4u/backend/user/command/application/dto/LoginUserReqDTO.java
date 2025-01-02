package com.beauty4u.backend.user.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class LoginUserReqDTO {

    @NotNull(message = "아이디는 필수 입력값입니다.")
    private String userCode;

    @NotNull(message = "비밀번호는 필수 입력값입니다.")
    private String userPassword;

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(userCode, userPassword);
    }
}
