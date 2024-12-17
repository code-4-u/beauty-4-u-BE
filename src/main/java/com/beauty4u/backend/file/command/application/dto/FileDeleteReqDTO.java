package com.beauty4u.backend.file.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDeleteReqDTO {

    private Long entityId;
    private Boolean isInform;
}
