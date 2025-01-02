package com.beauty4u.backend.inform.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;

@Getter
@Entity
@Table(name = "inform")
@SQLDelete(sql = "UPDATE inform SET publish_status = 'DELETED', deleted_date = NOW() WHERE inform_id = ?")
public class Inform extends BaseEntity {

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

    @Lob
    @Column(name = "inform_content", nullable = false)
    private String informContent;

    @NotNull
    @Column(name = "inform_viewcount", nullable = false)
    private Long informViewcount = 0L;

    public void modifyUser(UserInfo user) {
        this.userCode = user;
    }

    public void modifyInform(String title, String content) {
        this.informTitle = title;
        this.informContent = content;
    }

    public void modifyInformViewcount(Long informViewcount) {
        this.informViewcount = informViewcount;
    }
}