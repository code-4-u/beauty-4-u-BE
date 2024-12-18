package com.beauty4u.backend.teamspace.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;

@Getter
@Entity
@Table(name = "team_board_reply")
@SQLDelete(sql = "UPDATE team_board_reply SET publish_status = 'DELETED', deleted_date = NOW() WHERE team_board_reply_id = ?")
public class TeamBoardReply extends BaseEntity {

    @Id
    @Column(name = "team_board_reply_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "team_board_id", nullable = false)
    private TeamBoard teamBoard;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_code", nullable = false)
    private UserInfo userCode;

    @NotNull
    @Lob
    @Column(name = "team_board_reply_content", nullable = false)
    private String teamBoardReplyContent;
}

