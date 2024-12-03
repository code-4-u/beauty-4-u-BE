package com.beauty4u.backend.common.success;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler {

    public <T> ResponseEntity<ApiResponse<T>> createSuccessResponse(SuccessCode successCode, T data) {
        return ResponseUtil.successResponse(
                successCode.getMessage(),
                data,
                successCode.getHttpStatus()
        );
    }

    public ResponseEntity<ApiResponse<Void>> createSuccessResponse(SuccessCode successCode) {
        return ResponseUtil.successResponse(
                successCode.getMessage(),
                null,
                successCode.getHttpStatus()
        );
    }
}