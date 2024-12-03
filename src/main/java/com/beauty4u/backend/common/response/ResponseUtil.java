package com.beauty4u.backend.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    // 성공 응답 생성 (데이터와 메시지, 상태코드가 있는 경우)
    public static <T> ResponseEntity<ApiResponse<T>> successResponse(String message, T data, HttpStatus status) {
        ApiResponse<T> response = ApiResponse.ofSuccess(message, data);
        return new ResponseEntity<>(response, status);
    }

    // 성공 응답 생성 (메시지와 상태코드만 있는 경우)
    public static <T> ResponseEntity<ApiResponse<T>> successResponse(String message, HttpStatus status) {
        ApiResponse<T> response = ApiResponse.ofSuccess(message);
        return new ResponseEntity<>(response, status);
    }

    // 실패 응답 생성
    public static <T> ResponseEntity<ApiResponse<T>> failureResponse(String message, String errorCode, HttpStatus status) {
        ApiResponse<T> response = ApiResponse.ofFailure(message, errorCode);
        return new ResponseEntity<>(response, status);
    }

    // 예외 처리하여 실패 응답 생성
    public static <T> ResponseEntity<ApiResponse<T>> exceptionResponse(Exception e, String errorCode, HttpStatus status) {
        ApiResponse<T> response = ApiResponse.ofFailure(e.getMessage(), errorCode);
        return new ResponseEntity<>(response, status);
    }
}