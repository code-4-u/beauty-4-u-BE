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
@Table(name = "inquiry")
public class Inquiry {
    @Id
    @Column(name = "inquiry_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_code", nullable = false)
    private UserInfo userCode;

    @Size(max = 255)
    @NotNull
    @Column(name = "inquiry_title", nullable = false)
    private String inquiryTitle;

    @NotNull
    @Lob
    @Column(name = "inquiry_content", nullable = false)
    private String inquiryContent;

    @NotNull
    @Lob
    @Column(name = "inquiry_status", nullable = false)
    private String inquiryStatus;

    @NotNull
    @Column(name = "inquiry_viewcount", nullable = false)
    private Long inquiryViewcount;

    @NotNull
    @Column(name = "inquiry_created_date", nullable = false)
    private Instant inquiryCreatedDate;

    @Column(name = "inquiry_updated_date")
    private Instant inquiryUpdatedDate;

    @Column(name = "inquiry_deleted_date")
    private Instant inquiryDeletedDate;

    @NotNull
    @Column(name = "inquiry_secret_yn", nullable = false)
    private Character inquirySecretYn;

    @NotNull
    @Column(name = "inquiry_reply_yn", nullable = false)
    private Character inquiryReplyYn;

}