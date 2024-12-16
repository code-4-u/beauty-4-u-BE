package com.beauty4u.backend.inform.command.application.dto;

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
public class InformDTO extends BaseEntity {

    private Long informId;
    private UserInfo userCode;
    private String informTitle;
    private String informContent;
    private Long informViewcount;
}
