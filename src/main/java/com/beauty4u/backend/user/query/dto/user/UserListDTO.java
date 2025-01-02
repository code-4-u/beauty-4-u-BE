package com.beauty4u.backend.user.query.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserListDTO {

    private String userCode;
    private String userName;
    private String userRoleName;
    private String deptName;
    private String jobName;
    private LocalDateTime createdDate;
    private LocalDateTime userExpiredDate;
    private String userExpiredYn;
}
