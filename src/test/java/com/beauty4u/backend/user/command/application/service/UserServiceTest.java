package com.beauty4u.backend.user.command.application.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.user.command.application.dto.CreateUserReqDTO;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private CreateUserReqDTO user;

    @BeforeEach
    void setUp() {

        user = new CreateUserReqDTO();
        user.setUserCode("USER_001");
        user.setJobCode("J3");
        user.setDeptCode("D3");
        user.setUserRoleId(3L);
        user.setUserName("홍길동");
        user.setEmail("test1@test.com");
        user.setPhone("010-1234-1111");
    }

    @Test
    @DisplayName("회원 등록 테스트")
    void saveUserTest() {

        // given
        // when
        userService.saveUser(user);

        // then
        UserInfo savedUser = userRepository.findById(user.getUserCode())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        assertThat(savedUser.getJobCode().getJobCode()).isEqualTo("J3");
        assertThat(savedUser.getDeptCode().getDeptCode()).isEqualTo("D3");
        assertThat(savedUser.getUserRole().getId()).isEqualTo(3L);
        assertThat(savedUser.getUserName()).isEqualTo("홍길동");
        assertThat(savedUser.getEmail()).isEqualTo("test1@test.com");
        assertThat(savedUser.getPhone()).isEqualTo("010-1234-1111");
    }
}