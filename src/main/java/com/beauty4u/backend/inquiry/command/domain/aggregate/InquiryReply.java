package com.beauty4u.backend.inquiry.command.domain.aggregate;

import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "inquiry_reply")
public class InquiryReply {
    @Id
    @Column(name = "inquiry_reply_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inquiry_id", nullable = false)
    private Inquiry inquiry;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_code", nullable = false)
    private UserInfo userCode;

    @NotNull
    @Lob
    @Column(name = "inquiry_reply_content", nullable = false)
    private String inquiryReplyContent;

    @NotNull
    @Column(name = "inquiry_reply_created_date", nullable = false)
    private Instant inquiryReplyCreatedDate;

    @Column(name = "inquiry_reply_updated_date")
    private Instant inquiryReplyUpdatedDate;

    @Column(name = "inquiry_reply_deleted_date")
    private Instant inquiryReplyDeletedDate;

}