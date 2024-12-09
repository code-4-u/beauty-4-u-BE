package com.beauty4u.backend.teamspace.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TeamSpaceResponse {

    private Long teamspaceId;
    private String deptCode;
}
