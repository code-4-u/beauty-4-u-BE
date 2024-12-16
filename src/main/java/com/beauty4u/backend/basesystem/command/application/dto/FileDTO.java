package com.beauty4u.backend.basesystem.command.application.dto;

import com.beauty4u.backend.inform.command.domain.aggregate.Inform;
import com.beauty4u.backend.inquiry.command.domain.aggregate.Inquiry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    private Long fileId;
    private String fileUrl;
    private Inform inform;
    private Inquiry inquiry;
}
