package com.beauty4u.backend.user.command.domain.repository;

import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, String> {

    Optional<UserInfo> findByUserCode(String userCode);

    Optional<UserInfo> findByUserNameAndPhoneAndEmail(String userName, String phone, String email);

    boolean existsByUserCodeAndUserNameAndEmail(String userCode, String name, String email);

    List<UserInfo> findAllByUserCodeIn(Collection<String> userCodes);
}