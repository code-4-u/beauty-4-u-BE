package com.beauty4u.backend.inquiry.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQnaReqDTO {

    private String inquiryTitle;
    private String inquiryContent;
    private String inquirySecretYn;
}
