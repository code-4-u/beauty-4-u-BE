package com.beauty4u.backend.inform.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.StatusType;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "inform")
public class Inform {

    @Id
    @Column(name = "inform_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
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
    @Enumerated(EnumType.STRING)
    private StatusType informStatus = StatusType.PUBLISHED;

    @NotNull
    @Column(name = "inform_viewcount", nullable = false)
    private Long informViewcount = 0L;

    @NotNull
    @Column(name = "inform_created_date", nullable = false)
    private LocalDateTime informCreatedDate = LocalDateTime.now();

    @Column(name = "inform_updated_date")
    private LocalDateTime informUpdatedDate;

    @Column(name = "inform_deleted_date")
    private LocalDateTime informDeletedDate;

    public void modifyUser(UserInfo user) {
        this.userCode = user;
    }

    public void modifyInform(String title, String content) {
        this.informTitle = title;
        this.informContent = content;
        this.informUpdatedDate = LocalDateTime.now();
    }
}