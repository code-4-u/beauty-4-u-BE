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
@Table(name = "dept")
public class Dept {

    @Id
    @Size(max = 20)
    @Column(name = "dept_code", nullable = false, length = 20)
    private String deptCode;

    @Size(max = 20)
    @NotNull
    @Column(name = "dept_name", nullable = false, length = 20)
    private String deptName;
}