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
    CUSTOMER_FIND_ORDERINFO_LIST_SUCCESS(HttpStatus.OK, "고객 주문 이력 조회 성공"),
    CUSTOMER_FIND_LIST_STATS_SUCCESS(HttpStatus.OK, "고객 목록 통계 요약 조회 성공"),

    // 템플릿(template)
    TEMPLATE_FIND_LIST_SUCCESS(HttpStatus.OK, "템플릿 목록 조회 성공"),
    TEMPLATE_SAVE_SUCCESS(HttpStatus.OK, "템플릿 등록 성공"),
    TEMPLATE_UPDATE_SUCCESS(HttpStatus.OK, "템플릿 수정 성공"),
    TEMPLATE_DELETE_SUCCESS(HttpStatus.OK, "템플릿 삭제 성공"),

    // 마케팅 알림 설정(MarketingSetting)
    MARKETINGSETTING_SAVE_SUCCESS(HttpStatus.OK, "마케팅 알림 설정 등록 성공"),
    MARKETINGSETTING_DELETE_SUCCESS(HttpStatus.OK, "마케팅 알림 설정 삭제 성공"),
    MARKETINGSETTING_UPDATE_SUCCESS(HttpStatus.OK, "마케팅 알림 설정 수정 성공"),
    MARKETINGSETTING_FIND_LIST_SUCCESS(HttpStatus.OK, "마케팅 알림 설정 목록 조회 성공"),
    MARKETINGSETTING_FIND_DETAIL_SUCCESS(HttpStatus.OK, "마케팅 알림 설정 상세 조회 성공"),

    // 공지사항 (inform)
    INFORM_SAVE_SUCCESS(HttpStatus.CREATED, "공지사항 등록 성공"),
    INFORM_UPDATE_SUCCESS(HttpStatus.OK, "공지사항 수정 성공"),
    INFORM_DELETE_SUCCESS(HttpStatus.OK, "공지사항 삭제 성공"),
    INFORM_FIND_LIST_SUCCESS(HttpStatus.OK, "공지사항 목록 조회 성공"),
    INFORM_FIND_DETAIL_SUCCESS(HttpStatus.OK, "공지사항 상세 조회 성공"),

    // 문의 (inquiry)
    INQUIRY_SAVE_SUCCESS(HttpStatus.CREATED, "QnA 질문 등록 성공"),
    INQUIRY_UPDATE_SUCCESS(HttpStatus.OK, "QnA 질문 수정 성공"),
    INQUIRY_DELETE_SUCCESS(HttpStatus.OK, "QnA 질문 삭제 성공"),
    INQUIRY_REPLY_SAVE_SUCCESS(HttpStatus.CREATED, "QnA 답변 등록 성공"),
    INQUIRY_REPLY_UPDATE_SUCCESS(HttpStatus.OK, "QnA 답변 수정 성공"),
    INQUIRY_REPLY_DELETE_SUCCESS(HttpStatus.OK, "QnA 답변 삭제 성공"),
    INQUIRY_FIND_LIST_SUCCESS(HttpStatus.OK, "QnA 목록 조회 성공"),
    FAQ_SAVE_SUCCESS(HttpStatus.CREATED, "FAQ 등록 성공"),
    FAQ_UPDATE_SUCCESS(HttpStatus.OK, "FAQ 수정 성공"),
    FAQ_DELETE_SUCCESS(HttpStatus.OK, "FAQ 삭제 성공"),
    FAQ_FIND_LIST_SUCCESS(HttpStatus.OK, "FAQ 목록 조회 성공"),
    FAQ_FIND_DETAIL_SUCCESS(HttpStatus.OK, "FAQ 상세 조회 성공"),

    // 알림 (noti)
    SSE_CONNECT_SUCCESS(HttpStatus.OK, "SSE 연결 성공"),
    NOTI_SAVE_SUCCESS(HttpStatus.OK, "알림 등록 성공");


    private final HttpStatus httpStatus;
    private final String message;
}
