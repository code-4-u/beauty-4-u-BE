package com.beauty4u.backend.teamspace.command.application.dto.scrap;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CreateScrapReqDto {

    @NotNull(message = "Folder ID는 필수입니다.")
    private Long folderId;

    @NotBlank(message = "User Code는 필수입니다.")
    private String userCode;

    @NotBlank(message = "Scrap 이름은 필수입니다.")
    @Size(max = 50, message = "Scrap 이름은 최대 50자까지 입력 가능합니다.")
    private String scrapName;

    @Size(max = 255, message = "Scrap 내용은 최대 255자까지 입력 가능합니다.")
    private String scrapContent;

    @NotBlank(message = "파일 URL은 필수입니다.")
    @Size(max = 50, message = "파일 URL은 최대 50자까지 입력 가능합니다.")
    private String fileUrl;

}
