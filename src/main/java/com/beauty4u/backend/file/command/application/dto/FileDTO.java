package com.beauty4u.backend.file.command.application.dto;

import com.beauty4u.backend.inform.command.domain.aggregate.Inform;
import com.beauty4u.backend.inquiry.command.domain.aggregate.Inquiry;
import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import com.beauty4u.backend.teamspace.command.domain.aggregate.TeamBoard;
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
    private TeamBoard teamBoard;
    private ChatMessage message;
    private Inform inform;
    private Inquiry inquiry;
}
