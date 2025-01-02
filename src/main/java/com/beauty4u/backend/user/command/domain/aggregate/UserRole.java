package com.beauty4u.backend.user.command.domain.aggregate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @Column(name = "user_role_id", nullable = false)
    private Long id;

    @Size(max = 20)
    @NotNull
    @Column(name = "user_role_name", nullable = false, length = 20)
    private String userRoleName;
}