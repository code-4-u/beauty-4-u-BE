package com.beauty4u.backend.user.query.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.common.util.MailUtil;
import com.beauty4u.backend.user.query.dto.FindUserCodeReqDTO;
import com.beauty4u.backend.user.query.dto.UserListResDTO;
import com.beauty4u.backend.user.query.mapper.UserQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserQueryMapper userQueryMapper;
    private final MailUtil mailUtil;

    public List<UserListResDTO> findUserList(Long page, Long count) {

        long offset = (page - 1) * count;

        return userQueryMapper.findUserList(offset, count);
    }

    public void findUserCode(FindUserCodeReqDTO findUserCodeReqDTO) {

        String name = findUserCodeReqDTO.getUserName();
        String phone = findUserCodeReqDTO.getPhone();
        String email = findUserCodeReqDTO.getEmail();

        String userCode = userQueryMapper.findUserCode(name, phone, email);

        if (userCode == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

        String title = "[beauty4u] 요청하신 사원번호(아이디) 안내";
        String body = String.format(
                "%s님, 안녕하세요.\n" +
                "요청하신 사원번호(아이디)를 안내해 드립니다.\n\n" +
                "사원번호: %s\n\n" +
                "감사합니다.\n" +
                "beauty4u 드림",
                name, userCode
        );

        try {
            mailUtil.sendEmail(email, title, body);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.EMAIL_SEND_FAIL);
        }
    }
}
