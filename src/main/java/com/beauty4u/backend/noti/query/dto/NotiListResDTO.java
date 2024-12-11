package com.beauty4u.backend.noti.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotiListResDTO {

    private Long notiId;
    private String notiType;
    private String notiContent;
    private String notiUrl;
    private String notiReadYn;
    private String notiSender;
    private LocalDateTime createdDate;
}
