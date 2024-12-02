package com.beauty4u.backend.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    FORBIDDEN(HttpStatus.FORBIDDEN, "인가 실패"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증 실패"),
    LOGIN_FAIL(HttpStatus.UNAUTHORIZED, "로그인 실패"),
    NOT_FOUND_JOB(HttpStatus.NOT_FOUND, "직급 조회 실패"),
    NOT_FOUND_DEPT(HttpStatus.NOT_FOUND, "부서 조회 실패"),
    NOT_FOUND_ROLE(HttpStatus.NOT_FOUND, "권한 조회 실패");

    private final HttpStatus httpStatus;
    private final String message;
}