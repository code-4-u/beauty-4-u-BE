package com.beauty4u.backend.noti.command.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/noti")
@Tag(name = "Notification", description = "알림 관련 API")
public class NotiCommandController {
}
