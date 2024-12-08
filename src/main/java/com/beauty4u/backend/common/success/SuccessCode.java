package com.beauty4u.backend.common.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    // 회원 (user)
    USER_SAVE_SUCCESS(HttpStatus.CREATED, "회원 등록 성공"),
    USER_LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공"),
    USER_LOGOUT_SUCCESS(HttpStatus.OK, "로그아웃 성공"),
    USER_READ_SUCCESS(HttpStatus.OK, "회원 조회 성공"),

    // 고객 (customer)
    CUSTOMER_FIND_LIST_SUCCESS(HttpStatus.OK, "고객 목록 조회 성공"),
    CUSTOMER_FIND_DETAIL_SUCCESS(HttpStatus.OK, "고객 상세 조회 성공"),

    // 공지사항 (inform)
    INFORM_SAVE_SUCCESS(HttpStatus.CREATED, "공지사항 등록 성공"),
    INFORM_UPDATE_SUCCESS(HttpStatus.OK, "공지사항 수정 성공"),
    INFORM_DELETE_SUCCESS(HttpStatus.OK, "공지사항 삭제 성공"),
    INFORM_FIND_LIST_SUCCESS(HttpStatus.OK, "공지사항 목록 조회 성공"),
    INFORM_FIND_DETAIL_SUCCESS(HttpStatus.OK, "공지사항 상세 조회 성공"),

    // 문의 (inquiry)
    INQUIRY_SAVE_SUCCESS(HttpStatus.CREATED, "QnA 등록 성공"),
    INQUIRY_UPDATE_SUCCESS(HttpStatus.OK, "QnA 수정 성공"),
    INQUIRY_DELETE_SUCCESS(HttpStatus.OK, "QnA 삭제 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
