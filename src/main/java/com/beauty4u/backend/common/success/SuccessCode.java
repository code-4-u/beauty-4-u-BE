package com.beauty4u.backend.common.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    // 회원 (user)
    USER_SAVE_SUCCESS(HttpStatus.CREATED, "회원 등록 성공"),
    USER_UPDATE_SUCCESS(HttpStatus.OK, "회원 수정 성공"),
    USER_LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공"),
    USER_LOGOUT_SUCCESS(HttpStatus.OK, "로그아웃 성공"),
    USER_READ_SUCCESS(HttpStatus.OK, "회원 조회 성공"),
    USERCODE_FIND_SUCCESS(HttpStatus.OK, "사원번호 조회 성공"),
    PASSWORD_RESET_SUCCESS(HttpStatus.OK, "비밀번호 재발급 성공"),
    PASSWORD_UPDATE_SUCCESS(HttpStatus.OK, "비밀번호 수정 성공"),
    EXPIRE_UPDATE_SUCCESS(HttpStatus.OK, "만료 상태 수정 성공"),
    USER_FIND_LIST_SUCCESS(HttpStatus.OK, "회원 목록 조회 성공"),
    USER_FIND_DETAIL_SUCCESS(HttpStatus.OK, "회원 상세 조회 성공"),
    DEPT_READ_SUCCESS(HttpStatus.OK, "부서 조회 성공"),
    JOB_READ_SUCCESS(HttpStatus.OK, "직급 조회 성공"),
    ROLE_READ_SUCCESS(HttpStatus.OK, "권한 조회 성공"),

    // 고객 (customer)
    CUSTOMER_FIND_LIST_SUCCESS(HttpStatus.OK, "고객 목록 조회 성공"),
    CUSTOMER_FIND_DETAIL_SUCCESS(HttpStatus.OK, "고객 상세 조회 성공"),
    CUSTOMER_FIND_ORDERINFO_LIST_SUCCESS(HttpStatus.OK, "고객 주문 이력 조회 성공"),
    CUSTOMER_FIND_LIST_STATS_SUCCESS(HttpStatus.OK, "고객 목록 통계 요약 조회 성공"),

    // 상품(goods)
    GOODS_FIND_LIST_SUCCESS(HttpStatus.OK, "상품 목록 조회 성공"),
    GOODS_FIND_ELASTICSEARCH_SUCCESS(HttpStatus.OK, "엘라스틱서치 상품 검색 성공"),
    BRAND_FIND_LIST_SUCCESS(HttpStatus.OK, "브랜드 목록 조회 성공"),
    BRAND_FIND_LIST_GOODS_SUCCESS(HttpStatus.OK, "브랜드 내 상품 목록 조회 성공"),
    GOODS_FIND_LIST_SUBCATEGORYLIST_SUCCESS(HttpStatus.OK, "상위 카테고리 내 하위 카테고리 목록 조회 성공"),
    GOODS_FIND_LIST_TOPCATEGORY_GOODS_AND_SUBCATEGORY(HttpStatus.OK, "상위 카테고리 내 상품과 하위 카테고리 목록 조회 성공"),
    GOODS_FIND_LIST_SUBCATEGORYLIST_GOODS_SUCCESS(HttpStatus.OK, "하위 카테고리 내 상품 목록 조회 성공"),


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
    INFORM_UPDATE_VIEWCOUNT_SUCCESS(HttpStatus.OK, "조회수 수정 성공"),

    // 문의 (inquiry)
    INQUIRY_SAVE_SUCCESS(HttpStatus.CREATED, "QnA 질문 등록 성공"),
    INQUIRY_UPDATE_SUCCESS(HttpStatus.OK, "QnA 질문 수정 성공"),
    INQUIRY_DELETE_SUCCESS(HttpStatus.OK, "QnA 질문 삭제 성공"),
    INQUIRY_REPLY_SAVE_SUCCESS(HttpStatus.CREATED, "QnA 답변 등록 성공"),
    INQUIRY_REPLY_UPDATE_SUCCESS(HttpStatus.OK, "QnA 답변 수정 성공"),
    INQUIRY_REPLY_DELETE_SUCCESS(HttpStatus.OK, "QnA 답변 삭제 성공"),
    INQUIRY_FIND_LIST_SUCCESS(HttpStatus.OK, "QnA 목록 조회 성공"),
    INQUIRY_FIND_DETAIL_SUCCESS(HttpStatus.OK, "QnA 상세 조회 성공"),
    INQUIRY_UPDATE_VIEWCOUNT_SUCCESS(HttpStatus.OK, "조회수 수정 성공"),

    // FAQ
    FAQ_SAVE_SUCCESS(HttpStatus.CREATED, "FAQ 등록 성공"),
    FAQ_UPDATE_SUCCESS(HttpStatus.OK, "FAQ 수정 성공"),
    FAQ_DELETE_SUCCESS(HttpStatus.OK, "FAQ 삭제 성공"),
    FAQ_FIND_LIST_SUCCESS(HttpStatus.OK, "FAQ 목록 조회 성공"),
    FAQ_FIND_DETAIL_SUCCESS(HttpStatus.OK, "FAQ 상세 조회 성공"),
    FAQ_UPDATE_VIEWCOUNT_SUCCESS(HttpStatus.OK, "조회수 수정 성공"),

    // 알림 (noti)
    SSE_CONNECT_SUCCESS(HttpStatus.OK, "SSE 연결 성공"),
    NOTI_SAVE_SUCCESS(HttpStatus.OK, "알림 등록 성공"),
    NOTI_FIND_LIST_SUCCESS(HttpStatus.OK, "알림 조회 성공"),
    NOTI_READ_UPDATE_SUCCESS(HttpStatus.OK, "알림 읽음 상태 수정 성공"),

    /* 분석 대시보드 (analysis) */
    ANAL_FIND_AGE_GROUP_RADIO_SUCCESS(HttpStatus.OK, "고객 특성 분석 데이터 조회 성공"),
    ANAL_FIND_PUR_BY_AGE_SUCCESS(HttpStatus.OK, "연령별 구매 비율 데이터 조회 성공"),
    ANAL_FIND_PUR_BY_BRAND_SUCCESS(HttpStatus.OK, "연령별 브랜드 별 구매 횟수 데이터 조회 성공"),
    ANAL_FIND_PUR_BY_BRAND_PRODUCT_SUCCESS(HttpStatus.OK, "해당 브랜드 제품별 구매 횟수 데이터 조회 성공"),
    ANAL_FIND_GRADE_GROUP_RADIO_SUCCESS(HttpStatus.OK, "등급별 구매 비율 데티어 조회 성공"),
    ANAL_FIND_GRADE_BY_BRAND_SUCCESS(HttpStatus.OK, "등급별 브랜드 구매 횟수 데이터 조회 성공"),
    ANAL_FIND_GRADE_BY_BRAND_PRODUCT_SUCCESS(HttpStatus.OK, "등급별 브랜드별 제품 구매 횟수 데이터 조회 성공"),
    ANAL_FIND_AGE_SALES_RADIO_SUCCESS(HttpStatus.OK, "연령별 매출 비율 데이터 조회 성공"),
    ANAL_FIND_AGE_SALES_BY_BRAND_SUCCESS(HttpStatus.OK, "연령별 브랜드 매출 데이터 조회 성공"),
    ANAL_FIND_AGE_SALES_BY_BRAND_PRODUCT_SUCCESS(HttpStatus.OK, "연령별 브랜드 제품별 매출 데이터 조회 성공"),
    ANAL_FIND_GRADE_SALES_RADIO_SUCCESS(HttpStatus.OK, "등급별 매출 비율 데이터 조회 성공"),
    ANAL_FIND_GRADE_SALES_BY_BRAND_SUCCESS(HttpStatus.OK, "등급별 브핸드 매출 데이터 조회 성공"),
    ANAL_FIND_GRADE_SALES_BY_BRAND_PRODUCT_SUCCESS(HttpStatus.OK, "등급별 브랜드별 제품 매출 데이터 조회 성공"),

    // 일정
    SCHEDULE_SAVE_SUCCESS(HttpStatus.CREATED, "일정 등록 성공"),
    SCHEDULE_UPDATE_SUCCESS(HttpStatus.OK, "일정 수정 성공"),
    SCHEDULE_DELETE_SUCCESS(HttpStatus.OK, "일정 삭제 성공"),
    SCHEDULE_FIND_LIST_SUCCESS(HttpStatus.OK, "일정 목록 조회 성공"),

    // 이미지 저장
    UPLOAD_IMAGE_SUCCESS(HttpStatus.OK, "이미지 저장 성공"),
    FILE_SAVE_SUCCESS(HttpStatus.OK, "파일 저장 성공"),
    FILE_DELETE_SUCCESS(HttpStatus.OK, "파일 삭제 성공"),

    // Promotion
    PROMOTION_SAVE_SUCCESS(HttpStatus.OK, "프로모션 저장 성공"),
    PROMOTION_FIND_DETAIL_SUCCESS(HttpStatus.OK, "프로모션 상세 조회 성공"),
    PROMOTION_FIND_LIST_SUCCESS(HttpStatus.OK, "프로모션 목록 조회 성공"),
    PROMOTION_UPDATE_SUCCESS(HttpStatus.OK, "프로모션 수정 성공"),
    PROMOTION_DELETE_SUCCESS(HttpStatus.OK, "프로모션 삭제 성공"),

    // 프로모션 타입
    PROMOTIONTYPE_SAVE_SUCCESS(HttpStatus.OK, "프로모션 종류 저장 성공"),
    PROMOTIONTYPE_UPDATE_SUCCESS(HttpStatus.OK, "프로모션 종류 수정 성공"),
    PROMOTIONTYPE_LIST_FIND_SUCCESS(HttpStatus.OK, "프로모션 종류 목록 조회 성공"),
    PROMOTIONTYPE_DETAIL_FIND_SUCCESS(HttpStatus.OK, "프로모션 종류 상세 조회 성공"),

    // 폴더 (folder)
    FOLDER_SAVE_SUCCESS(HttpStatus.OK, "폴더 생성 성공"),
    FOLDER_UPDATE_SUCCESS(HttpStatus.OK, "폴더 수정 성공"),
    FOLDER_LIST_FIND_SUCCESS(HttpStatus.OK, "폴더 목록 조회 성공"),
    FOLDER_DELETE_SUCCESS(HttpStatus.OK, "폴더 삭제 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
