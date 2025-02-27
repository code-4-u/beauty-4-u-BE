package com.beauty4u.backend.user.query.service;

import com.beauty4u.backend.user.query.dto.user.UserListDTO;
import com.beauty4u.backend.user.query.dto.user.UserListResDTO;
import com.beauty4u.backend.user.query.mapper.UserQueryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserQueryServiceTest {

    @Mock
    private UserQueryMapper userQueryMapper;

    @InjectMocks
    private UserQueryService userQueryService;

    private UserListDTO user1;
    private UserListDTO user2;

    @BeforeEach
    void setUp() {

        user1 = new UserListDTO();
        user1.setUserCode("USER_001");
        user1.setUserRoleName("ADMIN");
        user1.setDeptName("마케팅팀");
        user1.setCreatedDate(LocalDateTime.of(2024, 1, 1, 9, 0));
        user1.setUserExpiredDate(LocalDateTime.of(2025, 12, 31, 23, 59));

        user2 = new UserListDTO();
        user2.setUserCode("USER_002");
        user2.setUserRoleName("USER");
        user2.setDeptName("영업팀");
        user2.setCreatedDate(LocalDateTime.of(2024, 2, 1, 9, 0));
        user2.setUserExpiredDate(LocalDateTime.of(2025, 12, 31, 23, 59));
    }

    @Test
    @DisplayName("회원 목록 조회 테스트 - 첫 번째 페이지")
    void findUserList_ShouldReturnUserList() {

        // given
        Long page = 1L;
        Long count = 10L;
        long offset = (page - 1) * count;
        String search = "";

        List<UserListDTO> expectedUsers = Arrays.asList(user1, user2);

        when(userQueryMapper.findUserList(offset, count, search))
                .thenReturn(expectedUsers);

        // when
        UserListResDTO actualUsers = userQueryService.findUserList(page, count, search);

        // then
        assertThat(actualUsers.getContent()).isNotNull();
        assertThat(actualUsers.getContent()).hasSize(2);
        assertThat(actualUsers.getContent()).isEqualTo(expectedUsers);

        UserListDTO firstUser = actualUsers.getContent().get(0);
        assertThat(firstUser.getUserCode()).isEqualTo("USER_001");
        assertThat(firstUser.getUserRoleName()).isEqualTo("ADMIN");
        assertThat(firstUser.getDeptName()).isEqualTo("마케팅팀");
        assertThat(firstUser.getCreatedDate()).isEqualTo(LocalDateTime.of(2024, 1, 1, 9, 0));

        UserListDTO secondUser = actualUsers.getContent().get(1);
        assertThat(secondUser.getUserCode()).isEqualTo("USER_002");
        assertThat(secondUser.getUserRoleName()).isEqualTo("USER");
        assertThat(secondUser.getDeptName()).isEqualTo("영업팀");
        assertThat(secondUser.getCreatedDate()).isEqualTo(LocalDateTime.of(2024, 2, 1, 9, 0));
    }

    @Test
    @DisplayName("회원 목록 조회 테스트 - 빈 페이지")
    void findUserList_ShouldReturnEmptyList_WhenNoUsersExist() {

        // given
        Long page = 1L;
        Long count = 10L;
        long offset = (page - 1) * count;
        String search = "";

        when(userQueryMapper.findUserList(offset, count, search))
                .thenReturn(List.of());

        // when
        UserListResDTO actualUsers = userQueryService.findUserList(page, count, search);

        // then
        assertThat(actualUsers).isNotNull();
        assertThat(actualUsers.getContent()).isEmpty();
    }

    @Test
    @DisplayName("회원 목록 조회 테스트 - 두 번째 페이지")
    void findUserList_ShouldReturnCorrectUsers_WhenOnSecondPage() {

        // given
        Long page = 2L;
        Long count = 2L;
        long offset = (page - 1) * count;
        String search = "";

        UserListDTO user3 = new UserListDTO();
        user3.setUserCode("USER_003");
        user3.setUserRoleName("USER");
        user3.setDeptName("개발팀");
        user3.setCreatedDate(LocalDateTime.of(2024, 3, 1, 9, 0));
        user3.setUserExpiredDate(LocalDateTime.of(2025, 12, 31, 23, 59));

        List<UserListDTO> expectedUsers = List.of(user3);

        when(userQueryMapper.findUserList(offset, count, search))
                .thenReturn(expectedUsers);

        // when
        UserListResDTO actualUsers = userQueryService.findUserList(page, count, search);

        // then
        assertThat(actualUsers.getContent()).isNotNull();
        assertThat(actualUsers.getContent()).hasSize(1);

        UserListDTO actualUser = actualUsers.getContent().get(0);
        assertThat(actualUser.getUserCode()).isEqualTo("USER_003");
        assertThat(actualUser.getDeptName()).isEqualTo("개발팀");
    }

    @Test
    @DisplayName("회원 목록 조회 테스트 - 큰 page 번호")
    void findUserList_ShouldHandleLargePageNumbers() {

        // given
        Long page = 100L;
        Long count = 10L;
        long offset = (page - 1) * count;
        String search = "";

        when(userQueryMapper.findUserList(offset, count, search))
                .thenReturn(List.of());

        // when
        UserListResDTO actualUsers = userQueryService.findUserList(page, count, search);

        // then
        assertThat(actualUsers.getContent()).isNotNull();
        assertThat(actualUsers.getContent()).isEmpty();
    }

    @Test
    @DisplayName("회원 목록 조회 테스트 - 큰 count 번호")
    void findUserList_ShouldHandleMaximumPageSize() {

        // given
        Long page = 1L;
        Long count = 100L;
        long offset = (page - 1) * count;
        String search = "";

        List<UserListDTO> expectedUsers = Arrays.asList(user1, user2);

        when(userQueryMapper.findUserList(offset, count, search))
                .thenReturn(expectedUsers);

        // when
        UserListResDTO actualUsers = userQueryService.findUserList(page, count, search);

        // then
        assertThat(actualUsers.getContent()).isNotNull();
        assertThat(actualUsers.getContent()).hasSize(2);
    }
}