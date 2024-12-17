package com.beauty4u.backend.teamspace.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "teamspace")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Teamspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teamspace_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "dept_code", nullable = false)
    private String deptCode;
}