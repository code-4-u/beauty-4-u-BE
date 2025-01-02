package com.beauty4u.backend.common.util;

import com.beauty4u.backend.security.util.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class CustomUserUtil {

    public static Optional<CustomUserDetails> getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            return Optional.of((CustomUserDetails) authentication.getPrincipal());
        }
        return Optional.empty();
    }

    public static Collection<? extends GrantedAuthority> getCurrentUserAuthorities() {
        return getCurrentUserDetails()
                .map(UserDetails::getAuthorities)
                .orElse(null);
    }

    public static String getCurrentUserCode() {
        return getCurrentUserDetails()
                .map(UserDetails::getUsername)
                .orElse(null);
    }

    public static String getCurrentDeptCode() {
        return getCurrentUserDetails()
                .map(CustomUserDetails::getDeptCode)
                .orElse(null);
    }
}