package com.beauty4u.backend.inquiry.command.domain.aggregate;

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
@Table(name = "faq")
public class Faq {
    @Id
    @Column(name = "faq_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_code", nullable = false)
    private UserInfo userCode;

    @Size(max = 255)
    @NotNull
    @Column(name = "faq_title", nullable = false)
    private String faqTitle;

    @NotNull
    @Lob
    @Column(name = "faq_content", nullable = false)
    private String faqContent;

    @NotNull
    @Lob
    @Column(name = "faq_status", nullable = false)
    private String faqStatus;

    @NotNull
    @Column(name = "faq_viewcount", nullable = false)
    private Long faqViewcount;

    @NotNull
    @Column(name = "faq_created_date", nullable = false)
    private Instant faqCreatedDate;

    @Column(name = "faq_updated_date")
    private Instant faqUpdatedDate;

    @Column(name = "faq_deleted_date")
    private Instant faqDeletedDate;

}