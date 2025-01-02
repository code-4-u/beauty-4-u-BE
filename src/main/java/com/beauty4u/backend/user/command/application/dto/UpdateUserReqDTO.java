package com.beauty4u.backend.user.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserReqDTO {

    private String jobCode;
    private String deptCode;
    private Long userRoleId;
    private String email;
    private String phone;
}