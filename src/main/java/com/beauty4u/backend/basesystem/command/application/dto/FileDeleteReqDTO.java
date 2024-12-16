package com.beauty4u.backend.basesystem.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDeleteReqDTO {

    private Long entityId;
    private Boolean isInform;
}
