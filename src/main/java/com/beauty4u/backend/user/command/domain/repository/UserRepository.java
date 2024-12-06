package com.beauty4u.backend.user.command.domain.repository;

import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, String> {

    Optional<UserInfo> findByUserCode(String userCode);

    Optional<UserInfo> findByUserNameAndPhoneAndEmail(String userName, String phone, String email);
}