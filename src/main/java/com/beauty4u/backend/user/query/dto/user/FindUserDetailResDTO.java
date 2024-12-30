package com.beauty4u.backend.user.query.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindUserDetailResDTO {

    private String userCode;
    private String jobName;
    private String deptName;
    private String userRoleName;
    private String userName;
    private String email;
    private String phone;
}
