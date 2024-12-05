package com.beauty4u.backend.common.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    // 회원 (user)
    USER_REGISTER_SUCCESS(HttpStatus.CREATED, "회원등록 성공"),
    USER_LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공"),
    USER_LOGOUT_SUCCESS(HttpStatus.OK, "로그아웃 성공"),
    USER_READ_SUCCESS(HttpStatus.OK, "회원 조회 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
