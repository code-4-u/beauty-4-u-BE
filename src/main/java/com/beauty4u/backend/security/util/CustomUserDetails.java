package com.beauty4u.backend.security.util;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {

    private String jobName;
    private String deptCode;
    private String deptName;
    private String userName;

    public CustomUserDetails(String userCode, String password,
                             String jobName, String deptCode, String deptName, String userName,
                             Collection<? extends GrantedAuthority> authorities) {
        super(userCode, password, authorities);
        this.jobName = jobName;
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.userName = userName;
    }
}
