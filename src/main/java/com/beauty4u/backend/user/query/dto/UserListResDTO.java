package com.beauty4u.backend.user.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserListResDTO {

    private String userCode;
    private String userRoleName;
    private String deptName;
    private LocalDateTime createdDate;
    private LocalDateTime userExpiredDate;
}
