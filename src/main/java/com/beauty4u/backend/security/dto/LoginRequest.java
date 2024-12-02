package com.beauty4u.backend.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String userCode;
    private String userPassword;
}