package com.beauty4u.backend.user.command.application.service;

import com.beauty4u.backend.user.command.application.dto.CreateUserRequest;
import com.beauty4u.backend.user.command.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserDomainService userDomainService;

    public void saveUser(CreateUserRequest newUser) {

        userDomainService.saveUser(newUser);
    }
}