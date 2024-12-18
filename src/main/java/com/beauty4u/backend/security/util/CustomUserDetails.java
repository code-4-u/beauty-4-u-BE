package com.beauty4u.backend.security.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

    private String jobName;
    private String deptName;
    private String userName;

    public CustomUserDetails(String userCode, String password,
                             String jobName, String deptName, String userName,
                             Collection<? extends GrantedAuthority> authorities) {
        super(userCode, password, authorities);
        this.jobName = jobName;
        this.deptName = deptName;
        this.userName = userName;
    }

    public String getJobName() {
        return jobName;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getName() {
        return userName;
    }
}
