package com.beauty4u.backend.user.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.user.command.application.dto.CreateUserReqDTO;
import com.beauty4u.backend.user.command.application.dto.UpdateUserReqDTO;
import com.beauty4u.backend.user.command.domain.aggregate.Dept;
import com.beauty4u.backend.user.command.domain.aggregate.Job;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.aggregate.UserRole;
import com.beauty4u.backend.user.command.domain.repository.DeptRepository;
import com.beauty4u.backend.user.command.domain.repository.JobRepository;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import com.beauty4u.backend.user.command.domain.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final DeptRepository deptRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public void saveUser(CreateUserReqDTO newUser) {

        UserInfo user = modelMapper.map(newUser, UserInfo.class);

        Job job = jobRepository.findById(newUser.getJobCode())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_JOB));
        Dept dept = deptRepository.findById(newUser.getDeptCode())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_DEPT));
        UserRole role = userRoleRepository.findById(newUser.getUserRoleId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ROLE));

        user.modifyJob(job);
        user.modifyDept(dept);
        user.modifyRole(role);

        /* 초기 비밀번호는 사원번호와 동일 */
        user.encryptPassword(passwordEncoder.encode(newUser.getUserCode()));

        userRepository.save(user);
    }

    public String findUserCode(String name, String phone, String email) {

        return userRepository.findByUserNameAndPhoneAndEmail(name, phone, email)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER))
                .getUserCode();
    }

    public void verifyUserExists(String userCode, String name, String email) {

        if (!userRepository.existsByUserCodeAndUserNameAndEmail(userCode, name, email)) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }
    }

    public void updatePasswordWithValidation(String userCode, String currentPassword, String newPassword) {

        UserInfo user = userRepository.findByUserCode(userCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        if (!passwordEncoder.matches(currentPassword, user.getUserPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        user.encryptPassword(passwordEncoder.encode(newPassword));
    }

    public void updatePassword(String userCode, String newPassword) {

        UserInfo user = userRepository.findByUserCode(userCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        user.encryptPassword(passwordEncoder.encode(newPassword));
    }

    public void expireUser(String userCode) {

        UserInfo user = userRepository.findByUserCode(userCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        user.expireUser();
    }

    public void unexpireUser(String userCode) {

        UserInfo user = userRepository.findByUserCode(userCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        user.unexpireUser();
    }

    public void updateUser(String userCode, UpdateUserReqDTO updateUserReqDTO) {

        UserInfo user = userRepository.findByUserCode(userCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        Job job = jobRepository.findById(updateUserReqDTO.getJobCode())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_JOB));
        Dept dept = deptRepository.findById(updateUserReqDTO.getDeptCode())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_DEPT));
        UserRole role = userRoleRepository.findById(updateUserReqDTO.getUserRoleId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ROLE));

        user.updateUser(job, dept, role,
                updateUserReqDTO.getEmail(),
                updateUserReqDTO.getPhone());
    }

    // 모든 회원 불러오기
    public List<String> findAllUser() {

        return userRepository.findAll()
                .stream()
                .map(UserInfo::getUserCode)
                .toList();
    }

    // 회원 이름 가져오기
    public String findByUserCode(String loginUserCode) {

        UserInfo userInfo = userRepository.findById(loginUserCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        return userInfo.getUserName();
    }
}