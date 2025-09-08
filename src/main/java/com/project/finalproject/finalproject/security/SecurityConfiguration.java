package com.project.finalproject.finalproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {
                })
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/create", "/edit/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/printModels", "/api/printModels/**",
                                "/api/tags", "/api/tags/**")
                        .permitAll()
                        .requestMatchers("/**").hasAnyAuthority("USER", "ADMIN"))
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailService());

        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
