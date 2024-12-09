package com.beauty4u.backend.inquiry.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;

@Getter
@Entity
@Table(name = "faq")
@SQLDelete(sql = "UPDATE faq SET publish_status = 'DELETED', deleted_date = NOW() WHERE faq_id = ?")
public class Faq extends BaseEntity {

    @Id
    @Column(name = "faq_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
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
    private Long faqViewcount = 0L;

    public void modifyUser(UserInfo user) {
        this.userCode = user;
    }

    public void modifyFaq(String title, String content) {
        this.faqTitle = title;
        this.faqContent = content;
    }
}