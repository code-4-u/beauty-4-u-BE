package com.beauty4u.backend.inquiry.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.StatusType;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "inquiry")
public class Inquiry {

    @Id
    @Column(name = "inquiry_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
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
    @Enumerated(EnumType.STRING)
    private StatusType inquiryStatus = StatusType.PUBLISHED;

    @NotNull
    @Column(name = "inquiry_viewcount", nullable = false)
    private Long inquiryViewcount = 0L;

    @NotNull
    @Column(name = "inquiry_created_date", nullable = false)
    private LocalDateTime inquiryCreatedDate = LocalDateTime.now();

    @Column(name = "inquiry_updated_date")
    private LocalDateTime inquiryUpdatedDate;

    @Column(name = "inquiry_deleted_date")
    private LocalDateTime inquiryDeletedDate;

    @NotNull
    @Column(name = "inquiry_secret_yn", nullable = false)
    private Character inquirySecretYn;

    @NotNull
    @Column(name = "inquiry_reply_yn", nullable = false)
    private Character inquiryReplyYn = 'N';

    public void modifyUser(UserInfo user) {
        this.userCode = user;
    }

    public void modifyInquiry(String title, String content, Character secretYn) {
        this.inquiryTitle = title;
        this.inquiryContent = content;
        this.inquirySecretYn = secretYn;
        this.inquiryUpdatedDate = LocalDateTime.now();
    }
}