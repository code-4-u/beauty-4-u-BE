package com.beauty4u.backend.user.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.user.command.application.dto.CreateUserRequest;
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

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final DeptRepository deptRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public void saveUser(CreateUserRequest newUser) {

        UserInfo user = modelMapper.map(newUser, UserInfo.class);

        Job job = jobRepository.findById(newUser.getJobCode())
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_JOB));
        Dept dept = deptRepository.findById(newUser.getDeptCode())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_DEPT));
        UserRole role = userRoleRepository.findById(newUser.getUserRole())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ROLE));

        user.modifyJob(job);
        user.modifyDept(dept);
        user.modifyRole(role);

        user.encryptPassword(passwordEncoder.encode(newUser.getUserPassword()));

        userRepository.save(user);
    }

    public String findUserCode(String name, String phone, String email) {

        return userRepository.findByUserNameAndPhoneAndEmail(name, phone, email)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER))
                .getUserCode();
    }
}