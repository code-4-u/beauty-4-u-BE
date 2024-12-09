package com.beauty4u.backend.inquiry.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.YnType;
import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;

@Getter
@Entity
@Table(name = "inquiry")
@SQLDelete(sql = "UPDATE inquiry SET inquiry_status = 'DELETED', inquiry_deleted_date = NOW() WHERE inquiry_id = ?")
public class Inquiry extends BaseEntity {

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
    @Column(name = "inquiry_viewcount", nullable = false)
    private Long inquiryViewcount = 0L;

    @NotNull
    @Column(name = "inquiry_secret_yn", nullable = false)
    @Enumerated(EnumType.STRING)
    private YnType inquirySecretYn = YnType.N;

    @NotNull
    @Column(name = "inquiry_reply_yn", nullable = false)
    @Enumerated(EnumType.STRING)
    private YnType inquiryReplyYn = YnType.N;

    public void modifyUser(UserInfo user) {
        this.userCode = user;
    }

    public void modifyInquiry(String title, String content, String secretYn) {
        this.inquiryTitle = title;
        this.inquiryContent = content;
        if (secretYn.equals("Y")) {
            this.inquirySecretYn = YnType.Y;
        } else {
            this.inquirySecretYn = YnType.N;
        }
    }

    public void modifyReply(boolean isProcessed) {
        if (isProcessed) {
            this.inquiryReplyYn = YnType.Y;
        } else {
            this.inquiryReplyYn = YnType.N;
        }
    }
}