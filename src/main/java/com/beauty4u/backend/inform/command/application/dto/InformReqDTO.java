package com.beauty4u.backend.inform.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InformReqDTO {

    @NotNull(message = "제목은 필수 입력값입니다.")
    private String informTitle;

    @NotNull(message = "내용은 필수 입력값입니다.")
    private String informContent;
}
