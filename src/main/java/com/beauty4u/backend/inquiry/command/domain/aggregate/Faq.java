package com.beauty4u.backend.inquiry.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
@Table(name = "faq")
public class Faq extends BaseEntity {

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
    @Column(name = "faq_viewcount", nullable = false)
    private Long faqViewcount;
}