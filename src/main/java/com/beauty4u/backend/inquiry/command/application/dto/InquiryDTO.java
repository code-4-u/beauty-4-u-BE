package com.beauty4u.backend.inquiry.command.application.dto;

import com.beauty4u.backend.common.aggregate.YnType;
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
public class InquiryDTO extends BaseEntity {

    private Long id;
    private UserInfo userCode;
    private String inquiryTitle;
    private String inquiryContent;
    private Long inquiryViewcount;
    private YnType inquirySecretYn;
    private YnType inquiryReplyYn;
}
