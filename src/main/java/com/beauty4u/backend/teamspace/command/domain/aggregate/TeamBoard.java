package com.beauty4u.backend.teamspace.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;

@Getter
@Entity
@Table(name = "team_board")
@SQLDelete(sql = "UPDATE team_board SET publish_status = 'DELETED', deleted_date = NOW() WHERE team_board_id = ?")
public class TeamBoard extends BaseEntity {

    @Id
    @Column(name = "team_board_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_code", nullable = false)
    private UserInfo userCode;

    @Size(max = 255)
    @NotNull
    @Column(name = "team_board_title", nullable = false)
    private String teamBoardTitle;

    @NotNull
    @Lob
    @Column(name = "team_board_content", nullable = false)
    private String teamBoardContent;
}
