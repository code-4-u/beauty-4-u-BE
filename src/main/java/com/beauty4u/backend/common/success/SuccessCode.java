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
    TOP_CATEGORY_LIST_FIND_SUCCESS(HttpStatus.OK, "상위 카테고리 리스트 조회 성공"),

    // 매출
    GOODS_FIND_LIST_RATE_SUCCESS(HttpStatus.OK, "매출률 상품 목록 조회 성공"),

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
    INQUIRY_REPLY_FIND_SUCCESS(HttpStatus.OK, "문의 답글 조회 성공"),

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

    /* 프로모션 통계 (promotion statistical) */
    PROMO_STAT_FIND_TYPE(HttpStatus.OK, "프로모션 종류 조회"),
    PROMO_STAT_SEARCH_SUCCESS(HttpStatus.OK, "프로모션 검색 성공"),
    PROMO_STAT_FIND_TYPE_BY_PROMOTION(HttpStatus.OK, "프로모션 타입별 조회 성공"),
    PROMO_STAT_FIND_BY_YEAR_SALES(HttpStatus.OK, "프로모션 종류에 따른 년도별 그래프 조회 "),
    PROMO_STAT_FIND_BY_COM_SALES(HttpStatus.OK, "프로모션 기간 상품 매출 랭킹 비교"),
    PROMO_STAT_FIND_BY_GOODS_SALES_SUCCESS(HttpStatus.OK, "프로모션 기간 적용 상품 리스트 조회"),

    // 일정
    SCHEDULE_SAVE_SUCCESS(HttpStatus.CREATED, "일정 등록 성공"),
    SCHEDULE_UPDATE_SUCCESS(HttpStatus.OK, "일정 수정 성공"),
    SCHEDULE_DELETE_SUCCESS(HttpStatus.OK, "일정 삭제 성공"),
    SCHEDULE_FIND_LIST_SUCCESS(HttpStatus.OK, "일정 목록 조회 성공"),

    // 이미지 저장
    UPLOAD_IMAGE_SUCCESS(HttpStatus.OK, "이미지 저장 성공"),
    FILE_SAVE_SUCCESS(HttpStatus.OK, "파일 저장 성공"),
    FILE_DELETE_SUCCESS(HttpStatus.OK, "파일 삭제 성공"),
    FILE_LIST_FIND_SUCCESS(HttpStatus.OK, "특정 게시판에 저장된 파일 목록 조회 성공"),

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
    PROMOTIONTYPE_DELETE_SUCCESS(HttpStatus.OK, "프로모션 종류 삭제 성공"),

    // 프로모션 적용 상품
    PROMOTION_GOODS_LIST_SAVE_SUCCESS(HttpStatus.OK, "프로모션 적용 상품 저장 성공"),
    PROMOTION_GOODS_LIST_DELETE_SUCCESS(HttpStatus.OK, "프로모션 적용 상품 삭제 성공"),
    PROMOTION_GOODS_LIST_UPDATE_SUCCESS(HttpStatus.OK, "프로모션 적용 상품 수정 성공"),
    PROMOTION_GOODS_LIST_FIND_SUCCESS(HttpStatus.OK, "프로모션 적용 상품 목록 조회 성공"),

    // 프로모션 알림 저장
    PROMOTION_NOTI_SAVE_SUCCESS(HttpStatus.OK, "프로모션 알림 등록 성공"),
    PROMOTION_NOTI_UPDATE_SUCCESS(HttpStatus.OK, "프로모션 알림 수정 성공"),
    PROMOTION_NOTI_DELETE_SUCCESS(HttpStatus.OK, "프로모션 알림 삭제 성공"),

    // 특정 상품에 대한 프로모션
    GOODS_PROMOTION_COMMON_INFO_LIST_FIND_SUCCESS(HttpStatus.OK, "특정 상품에 대한 프로모션별 정보 조회 성공"),

    // 제품에 대한 매출
    GOODS_SALES_FIND_SUCCESS(HttpStatus.OK, "제품의 전년 대비 같은 월 비교 성공"),
    GOODS_SALES_MONTHLY_LIST_FIND_SUCCESS(HttpStatus.OK, "제품의 해당 연도 모든 매출액 조회 성공"),
    GOODS_SALES_AGE_LIST_FIND_SUCCESS(HttpStatus.OK, "제품의 해당 연도와 지난 연도의 같은 달의 연령별 매출액 비교 조회 성공"),

    // 폴더 (folder)
    FOLDER_SAVE_SUCCESS(HttpStatus.OK, "폴더 생성 성공"),
    FOLDER_UPDATE_SUCCESS(HttpStatus.OK, "폴더 수정 성공"),
    FOLDER_LIST_FIND_SUCCESS(HttpStatus.OK, "폴더 목록 조회 성공"),
    FOLDER_DELETE_SUCCESS(HttpStatus.OK, "폴더 삭제 성공"),

    // 채팅 메세지
    CHAT_SEND_SUCCESS(HttpStatus.CREATED, "채팅 메세지 전송 성공"),
    CHAT_FIND_SUCCESS(HttpStatus.OK, "채팅 메세지 조회 성공"),

    // 채팅방
    CHATROOM_SAVE_SUCCESS(HttpStatus.CREATED, "채팅방 생성 성공"),
    TEAMSPACE_SAVE_ALL_SUCCESS(HttpStatus.CREATED, "부서별 팀스페이스 생성 성공"),
    TEAMSPACE_FIND_DEPTCODE_SUCCESS(HttpStatus.CREATED, "부서별 팀스페이스 생성 성공"),
    TEAMSPACE_CHAT_FIND_DETAIL_SUCCESS(HttpStatus.OK, "팀스페이스 채팅 상세조회 성공"),
    
    // 팀 게시판 (teamspace)
    TEAMBOARD_SAVE_SUCCESS(HttpStatus.CREATED, "팀 게시판 글 등록 성공"),
    TEAMBOARD_UPDATE_SUCCESS(HttpStatus.OK, "팀 게시판 글 수정 성공"),
    TEAMBOARD_DELETE_SUCCESS(HttpStatus.OK, "팀 게시판 글 삭제 성공"),
    TEAMBOARD_REPLY_SAVE_SUCCESS(HttpStatus.CREATED, "팀 게시판 댓글 등록 성공"),
    TEAMBOARD_REPLY_UPDATE_SUCCESS(HttpStatus.OK, "팀 게시판 댓글 수정 성공"),
    TEAMBOARD_REPLY_DELETE_SUCCESS(HttpStatus.OK, "팀 게시판 댓글 삭제 성공"),
    TEAMBOARD_FIND_LIST_SUCCESS(HttpStatus.OK, "팀 게시판 글 목록 조회 성공"),
    TEAMBOARD_FIND_DETAIL_SUCCESS(HttpStatus.OK, "팀 게시판 글 상세 조회 성공"),

    // 제미나이 요약
    GEMINI_REVIEW_FIND_SUCCESS(HttpStatus.OK, "제미나이 리뷰 요약 성공"),

    // 연관분석
    APRIORI_FIND_SUCCESS(HttpStatus.OK, "연관 분석 후 조합 목록 조회 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
