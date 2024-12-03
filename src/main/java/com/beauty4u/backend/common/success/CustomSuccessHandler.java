package com.beauty4u.backend.common.success;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler {

    public <T> ResponseEntity<CustomSuccess<T>> createSuccessResponse(SuccessCode successCode, T data) {
        return ResponseEntity
                .status(successCode.getHttpStatus())
                .body(new CustomSuccess<>(
                        successCode.name(),
                        successCode.getMessage(),
                        data
                ));
    }

    public ResponseEntity<CustomSuccess<Void>> createSuccessResponse(SuccessCode successCode) {
        return ResponseEntity
                .status(successCode.getHttpStatus())
                .body(new CustomSuccess<>(
                        successCode.name(),
                        successCode.getMessage()
                ));
    }
}
