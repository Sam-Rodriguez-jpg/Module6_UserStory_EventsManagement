package com.example.demo.infrastructure.config.security;

import com.example.demo.domain.models.UserModel;
import com.example.demo.domain.ports.out.UserRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepositoryPort userRepositoryPort;

    public CustomUserDetailsService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepositoryPort.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Normalizar rol
        String rawRole = userModel.role() == null ? "USER" : userModel.role().name();
        if (rawRole.startsWith("ROLE_")) {
            rawRole = rawRole.substring(5);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(userModel.username())
                .password(userModel.password()) // hashed
                .roles(rawRole) // Spring agregará ROLE_ automáticamente
                .build();
    }
}
