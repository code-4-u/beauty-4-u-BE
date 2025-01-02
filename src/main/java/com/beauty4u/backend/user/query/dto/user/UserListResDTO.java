package com.beauty4u.backend.user.query.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserListResDTO {

    List<UserListDTO> content;
    private Long totalElements;
}
