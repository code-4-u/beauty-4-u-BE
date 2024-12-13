package com.beauty4u.backend.user.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.YnType;
import com.beauty4u.backend.common.aggregate.entity.CreatedTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user_info")
public class UserInfo extends CreatedTimeEntity {

    @Id
    @Size(max = 20)
    @Column(name = "user_code", nullable = false, length = 20)
    private String userCode;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "job_code", nullable = false)
    private Job jobCode;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dept_code", nullable = false)
    private Dept deptCode;

    @NotNull
    @ManyToOne
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

    @Column(name = "user_expired_date")
    private LocalDateTime userExpiredDate;

    @NotNull
    @Column(name = "user_expired_yn", nullable = false)
    @Enumerated(EnumType.STRING)
    private YnType userExpiredYn = YnType.N;

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
        this.userExpiredYn = YnType.Y;
    }

    public void unexpireUser() {
        this.userExpiredDate = null;
        this.userExpiredYn = YnType.N;
    }

    public void updateUser(Job job, Dept dept, UserRole role,
                           String email, String phone) {
        this.jobCode = job;
        this.deptCode = dept;
        this.userRole = role;
        this.email = email;
        this.phone = phone;
    }
}