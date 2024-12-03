package com.beauty4u.backend.common.success;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomSuccess<T> {

    private String code;
    private String message;
    private T data;

    public CustomSuccess(String code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }
}
