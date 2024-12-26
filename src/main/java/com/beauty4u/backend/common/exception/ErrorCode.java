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
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "현재 비밀번호가 일치하지 않습니다."),
    NOT_FOUND_USER_LIST(HttpStatus.BAD_REQUEST, "회원 목록 조회를 실패했습니다."),

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
    NOT_SAME_USER(HttpStatus.BAD_REQUEST, "수정자와 등록자가 다름"),
    NOT_FOUND_INQUIRY_LIST(HttpStatus.BAD_REQUEST, "QnA 질문 목록 조회 실패"),
    NOT_DELETE_INQUIRY_REPLY(HttpStatus.BAD_REQUEST, "QnA 답변 삭제 실패"),

    // 알림 (noti)
    NOT_SAVED_NOTI(HttpStatus.CONFLICT, "알림 등록 실패"),
    NOT_REQUEST_NOTI(HttpStatus.CONFLICT, "알림 전송 실패"),
    NOT_FOUND_NOTI(HttpStatus.NOT_FOUND, "알림 조회 실패"),

    // 이미지 업로드
    EMPTY_FILE_EXCEPTION(HttpStatus.BAD_REQUEST, "이미지가 비어있습니다."),
    IO_EXCEPTION_ON_IMAGE_UPLOAD(HttpStatus.BAD_REQUEST, "이미지를 저장하는데 문제가 생겼습니다."),
    NO_FILE_EXTENSION(HttpStatus.BAD_REQUEST, "파일의 확장자가 존재하지 않습니다."),
    INVALID_FILE_EXTENSION(HttpStatus.BAD_REQUEST, "해당 확장자를 지원하지 않습니다."),
    PUT_OBJECT_EXCEPTION(HttpStatus.BAD_REQUEST, "S3에 업로드 실패했습니다."),
    IO_EXCEPTION_ON_IMAGE_DELETE(HttpStatus.BAD_REQUEST, "삭제에 실패했습니다."),

    // 일정 (schedule)
    NOT_FOUND_SCHEDULE(HttpStatus.NOT_FOUND, "일정 조회 실패"),

    // 프로모션
    PROMOTION_FIND_DETAIL_FAIL(HttpStatus.NOT_FOUND, "프로모션 상세 조회 실패"),
    PROMOTION_NOT_FOUND(HttpStatus.NOT_FOUND, "프로모션 조회 실패"),
    PROMOTION_NOT_SAVE(HttpStatus.CONFLICT, "프로모션 등록 실패"),
    PROMOTION_NOT_DELETE(HttpStatus.NOT_FOUND, "프로모션 삭제 실패"),
    PROMOTION_NOT_UPDATE(HttpStatus.CONFLICT, "프로모션 수정 실패"),
    NOT_FOUND_PROMOTION_LIST(HttpStatus.NOT_FOUND, "프로모션 목록 조회 실패"),

    // 프로모션 종류
    NOT_FOUND_PROMOTIONTYPE(HttpStatus.NOT_FOUND, "프로모션 조회 실패"),

    // 팀 스페이스
    NOT_FOUND_TEAMSPACE(HttpStatus.NOT_FOUND, "팀 스페이스 조회 실패"),

    // 상품
    NOT_FOUND_GOODS(HttpStatus.NOT_FOUND, "상품 조회 실패"),

    // 폴더
    NOT_FOUND_TOP_FOLDER(HttpStatus.NOT_FOUND, "상위 폴더 조회 실패"),
    FOLDER_SAVE_FAIL(HttpStatus.CONFLICT, "폴더 저장 실패"),
    FOLDER_UPDATE_FAIL(HttpStatus.CONFLICT, "폴더 수정 실패"),
    FOLDER_LIST_NOT_FOUND(HttpStatus.NOT_FOUND, "폴더 목록 조회 실패"),
    FOLDER_DELETE_FAIL(HttpStatus.CONFLICT, "폴더 삭제 실패"),
    FOLDER_SAME_NAME(HttpStatus.CONFLICT, "같은 위치내 같은 이름으로 인한 실패"),
    TOP_FOLDER_IS_DELETED(HttpStatus.BAD_REQUEST, "상위 폴더는 삭제된 상태입니다."),
    NOT_FOUND_FOLDER(HttpStatus.NOT_FOUND, "해당 폴더 조회 실패"),

    // 프로모션 적용 상품
    PROMOTION_GOODS_SAVE_FAIL(HttpStatus.CONFLICT, "프로모션 적용 상품 저장 실패"),
    PROMOTION_GOODS_LIST_DELETE_FAIL(HttpStatus.CONFLICT, "프로모션 적용 상품 삭제 실패"),
    PROMOTION_GOODS_UPDATE_FAIL(HttpStatus.BAD_REQUEST, "프로모션 적용 상품 수정 실패"),
    PROMOTION_GOODS_LIST_NOT_FOUND(HttpStatus.NOT_FOUND, "프로모션 적용 상품 목록 조회 실패"),
    NOT_FOUND_PROMOTION_GOODS(HttpStatus.NOT_FOUND, "프로모션 적용 상품 조회 실패"),

    // 특정 상품의 매출액
    GOODS_SALES_COMPARE_FIND_FAIL(HttpStatus.BAD_REQUEST, "제품의 전년 대비 같은 월 비교 실패"),
    GOODS_SALES_MONTHLY_LIST_FIND_FAIL(HttpStatus.BAD_REQUEST, "제품의 해당 연도 모든 매출액 조회 실패"),

    // 채팅 메세지
    NOT_FOUND_MESSAGE(HttpStatus.NOT_FOUND, "메세지 조회 실패"),

    // 팀 게시판 (teamspace)
    NOT_SAVED_TEAMBOARD(HttpStatus.CONFLICT, "팀 게시판 글 등록 실패"),
    NOT_FOUND_TEAMBOARD(HttpStatus.NOT_FOUND, "팀 게시판 글 조회 실패"),
    NOT_SAVED_TEAMBOARD_REPLY(HttpStatus.CONFLICT, "팀 게시판 댓글 등록 실패"),
    NOT_FOUND_TEAMBOARD_REPLY(HttpStatus.NOT_FOUND, "팀 게시판 댓글 조회 실패"),
    INVALID_TEAMBOARD_REPLY_UPDATE(HttpStatus.BAD_REQUEST, "잘못된 요청으로 인한 팀 게시판 댓글 수정 실패"),
    INVALID_TEAMBOARD_REPLY_DELETE(HttpStatus.BAD_REQUEST, "잘못된 요청으로 인한 팀 게시판 댓글 삭제 실패"),

    // 특정 상품에 대한 프로모션
    GOODS_PROMOTION_COMMON_INFO_LIST_FIND_FAIL(HttpStatus.NOT_FOUND, "특정 상품에 대한 프로모션별 정보 조회 실패"),

    // 상품 조회
    NOT_FOUND_GOODS_LIST(HttpStatus.NOT_FOUND, "필터링 조건에 따른 상품 조회 실패"),

    // 연관 분석
    APRIORI_FAIL(HttpStatus.BAD_REQUEST, "연관 분석 실패"),
    NOT_FOUND_ASSOCIATION_RECOMMENDATION(HttpStatus.NOT_FOUND, "연관 분석 결과 조회 실패"),

    // 분석
    NOT_FOUND_ANALYSIS(HttpStatus.NOT_FOUND, "분석 테이블 조회 실패");

    private final HttpStatus httpStatus;
    private final String message;
}
