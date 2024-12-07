package com.beauty4u.backend.user.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @Size(max = 20)
    @Column(name = "user_code", nullable = false, length = 20)
    private String userCode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_code", nullable = false)
    private Job jobCode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dept_code", nullable = false)
    private Dept deptCode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRole userRole;

    @Size(max = 20)
    @NotNull
    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 20)
    @NotNull
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Size(max = 100)
    @NotNull
    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @NotNull
    @Column(name = "user_created_date", nullable = false)
    private LocalDateTime userCreatedDate;

    @Column(name = "user_expired_date")
    private LocalDateTime userExpiredDate;

    @NotNull
    @Column(name = "user_expired_yn", nullable = false)
    private Character userExpiredYn;

    public void encryptPassword(String encodedPwd) {
        this.userPassword = encodedPwd;
    }

    public void modifyJob(Job job) {
        this.jobCode = job;
    }

    public void modifyDept(Dept dept) {
        this.deptCode = dept;
    }

    public void modifyRole(UserRole role) {
        this.userRole = role;
    }

    public void expireUser() {
        this.userExpiredDate = LocalDateTime.now();
        this.userExpiredYn = 'Y';
    }

    public void unexpireUser() {
        this.userExpiredDate = null;
        this.userExpiredYn = 'N';
    }
}