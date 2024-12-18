package com.beauty4u.backend.teamspace.command.application.dto;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamBoardDTO extends BaseEntity {

    private Long id;
    private UserInfo userCode;
    private String teamBoardTitle;
    private String teamBoardContent;
}
