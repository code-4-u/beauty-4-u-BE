package com.beauty4u.backend.inquiry.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;

@Getter
@Entity
@Table(name = "inquiry_reply")
@SQLDelete(sql = "UPDATE inquiry_reply SET deleted_date = NOW() WHERE inquiry_reply_id = ?")
public class InquiryReply extends BaseEntity {

    @Id
    @Column(name = "inquiry_reply_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "inquiry_id", nullable = false)
    private Inquiry inquiry;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_code", nullable = false)
    private UserInfo userCode;

    @NotNull
    @Lob
    @Column(name = "inquiry_reply_content", nullable = false)
    private String inquiryReplyContent;

    public void saveReply(Inquiry inquiry, UserInfo user) {
        this.inquiry = inquiry;
        this.userCode = user;
    }

    public void modifyContent(String content) {
        this.inquiryReplyContent = content;
    }
}