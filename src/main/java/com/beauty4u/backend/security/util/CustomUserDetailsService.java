package com.beauty4u.backend.security.util;

import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        UserInfo loginUser = userRepository.findByUserCode(loginId)
                .orElseThrow(() -> new UsernameNotFoundException(loginId));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + loginUser.getUserRole().getUserRoleName()));

        return new CustomUserDetails(
                loginUser.getUserCode(),
                loginUser.getUserPassword(),
                loginUser.getJobCode().getJobName(),
                loginUser.getDeptCode().getDeptName(),
                loginUser.getUserName(),
                grantedAuthorities
        );
    }
}
