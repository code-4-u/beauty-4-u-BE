package com.beauty4u.backend.basesystem.command.domain.aggregate;

import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "inform")
public class Inform {
    @Id
    @Column(name = "inform_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_code", nullable = false)
    private UserInfo userCode;

    @Size(max = 255)
    @NotNull
    @Column(name = "inform_title", nullable = false)
    private String informTitle;

    @NotNull
    @Lob
    @Column(name = "inform_content", nullable = false)
    private String informContent;

    @NotNull
    @Lob
    @Column(name = "inform_status", nullable = false)
    private String informStatus;

    @NotNull
    @Column(name = "inform_viewcount", nullable = false)
    private Long informViewcount;

    @NotNull
    @Column(name = "inform_created_date", nullable = false)
    private Instant informCreatedDate;

    @Column(name = "inform_updated_date")
    private Instant informUpdatedDate;

    @Column(name = "inform_deleted_date")
    private Instant informDeletedDate;

}