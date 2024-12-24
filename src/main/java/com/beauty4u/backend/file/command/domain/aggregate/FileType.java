package com.beauty4u.backend.file.command.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileType {
    INFORM,
    INQUIRY,
    TEAMBOARD,
    CHAT
}
