package com.beauty4u.backend.user.command.domain.aggregate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "job")
public class Job {
    @Id
    @Size(max = 20)
    @Column(name = "job_code", nullable = false, length = 20)
    private String jobCode;

    @Size(max = 20)
    @NotNull
    @Column(name = "job_name", nullable = false, length = 20)
    private String jobName;

}