package com.beauty4u.backend.user.command.application.controller;

import com.beauty4u.backend.common.success.CustomSuccessHandler;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.user.command.application.dto.CreateUserRequest;
import com.beauty4u.backend.user.command.application.service.UserCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserCommandController {

    private final UserCommandService userCommandService;
    private final CustomSuccessHandler customSuccessHandler;

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody @Valid CreateUserRequest createUserRequest) {

        userCommandService.saveUser(createUserRequest);

        return customSuccessHandler.createSuccessResponse(SuccessCode.USER_REGISTER_SUCCESS);
    }
}