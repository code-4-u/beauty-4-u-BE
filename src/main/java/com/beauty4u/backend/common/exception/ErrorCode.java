package com.beauty4u.backend.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 회원 (user)
    FORBIDDEN(HttpStatus.FORBIDDEN, "인가 실패"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증 실패"),
    LOGIN_FAIL(HttpStatus.UNAUTHORIZED, "로그인 실패"),
    LOGOUT_FAIL(HttpStatus.UNAUTHORIZED, "로그아웃 실패"),

    EMAIL_SEND_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "이메일 전송 실패"),

    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "회원 조회 실패"),
    NOT_FOUND_JOB(HttpStatus.NOT_FOUND, "직급 조회 실패"),
    NOT_FOUND_DEPT(HttpStatus.NOT_FOUND, "부서 조회 실패"),
    NOT_FOUND_ROLE(HttpStatus.NOT_FOUND, "권한 조회 실패"),

    // 템플릿
    NOT_FOUND_TEMPLATE(HttpStatus.NOT_FOUND, "템플릿 조회 실패"),

    // 공지사항 (inform)
    NOT_SAVED_INFORM(HttpStatus.CONFLICT, "공지사항 등록 실패"),

    NOT_FOUND_INFORM(HttpStatus.NOT_FOUND, "공지사항 조회 실패"),

    // 문의 (inquiry)
    NOT_SAVED_INQUIRY(HttpStatus.CONFLICT, "QnA 질문 등록 실패"),
    NOT_FOUND_INQUIRY(HttpStatus.NOT_FOUND, "QnA 질문 조회 실패"),
    NOT_SAVED_INQUIRY_REPLY(HttpStatus.CONFLICT, "QnA 답변 등록 실패"),
    NOT_FOUND_INQUIRY_REPLY(HttpStatus.NOT_FOUND, "QnA 답변 조회 실패"),
    NOT_SAVED_FAQ(HttpStatus.CONFLICT, "FAQ 등록 실패"),
    NOT_FOUND_FAQ(HttpStatus.NOT_FOUND, "FAQ 조회 실패"),

    // 알림 (noti)
    NOT_SAVED_NOTI(HttpStatus.CONFLICT, "알림 등록 실패"),
    NOT_REQUEST_NOTI(HttpStatus.CONFLICT, "알림 전송 실패"),
    NOT_FOUND_NOTI(HttpStatus.NOT_FOUND, "알림 조회 실패");

    private final HttpStatus httpStatus;
    private final String message;
}
