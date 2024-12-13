package com.beauty4u.backend.user.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserListResDTO {

    private String userCode;
    private String userName;
    private String userRoleName;
    private String deptName;
    private String jobName;
    private LocalDateTime createdDate;
    private LocalDateTime userExpiredDate;
    private String userExpiredYn;
}
