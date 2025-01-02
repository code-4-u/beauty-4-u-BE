package com.beauty4u.backend.security.config;

import com.beauty4u.backend.security.filter.JwtFilter;
import com.beauty4u.backend.security.handler.JwtAccessDeniedHandler;
import com.beauty4u.backend.security.handler.JwtAuthenticationEntryPoint;
import com.beauty4u.backend.security.util.CustomUserDetailsService;
import com.beauty4u.backend.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String ACCESS_TOKEN_HEADER = "Authorization";
    private static final String REFRESH_TOKEN_HEADER = "Refresh-Token";

    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz ->
                        authz.requestMatchers(
                                        "/",
                                        "/**",
                                        "/login",
                                        "/api/v1/user/login",
                                        "/user",
                                        "/swagger-ui/index.html",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/v3/api-docs",
                                        "/api/**",
                                        // STOMP 관련 추가 경로
                                        "/chat/**",          // WebSocket 엔드포인트
                                        "/chat/info/**",     // SockJS가 사용하는 추가 엔드포인트
                                        "/chat/iframe.html",         // SockJS iframe 경로
                                        "/wss",
                                        "/ws"
                                ).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/user/**", "GET")).hasRole("관리자")
                                // ADMIN 권한이 필요한 URL
                                .requestMatchers(
                                        new AntPathRequestMatcher("/api/v1/inform", "POST"),
                                        new AntPathRequestMatcher("/api/v1/inform/{informId}", "PUT"),
                                        new AntPathRequestMatcher("/api/v1/inform/{informId}", "DELETE")
                                ).hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling(
                exceptionHandling -> {
                    exceptionHandling.accessDeniedHandler(new JwtAccessDeniedHandler());
                    exceptionHandling.authenticationEntryPoint(new JwtAuthenticationEntryPoint());
                }
        );

        return http.build();
    }

    private AuthenticationManager getAuthenticationManager() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(provider);
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("https://bforu.site");
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedOrigin("http://localhost:8080");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader(ACCESS_TOKEN_HEADER);
        config.addExposedHeader(REFRESH_TOKEN_HEADER);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}