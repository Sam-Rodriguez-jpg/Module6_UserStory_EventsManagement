package com.example.demo.application.usecases.auth;

import com.example.demo.domain.models.UserModel;
import com.example.demo.domain.models.enums.RoleUserEnum;
import com.example.demo.domain.ports.out.UserRepositoryPort;
import com.example.demo.infrastructure.adapters.in.web.dtos.requests.UserLoginDtoRequest;
import com.example.demo.infrastructure.adapters.in.web.dtos.requests.UserRegisterDtoRequest;
import com.example.demo.infrastructure.adapters.in.web.dtos.responses.LoginDtoResponse;
import com.example.demo.infrastructure.config.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    // Registro: Valida si existe username, codifica password y guarda
    public void register(UserRegisterDtoRequest userRegisterDtoRequest) {
        if (userRepositoryPort.findByUsername(userRegisterDtoRequest.username()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        String roleString = userRegisterDtoRequest.role();
        RoleUserEnum role = (roleString == null || roleString.isBlank()) ? RoleUserEnum.USER : RoleUserEnum.valueOf(roleString.toUpperCase());
        UserModel userModel = new UserModel(
                null,
                userRegisterDtoRequest.username(),
                passwordEncoder.encode(userRegisterDtoRequest.password()),
                role
        );

        userRepositoryPort.save(userModel);
    }

    // Login: Autentica con AuthenticationManager y devuelve JWT
    public LoginDtoResponse login(UserLoginDtoRequest userLoginDtoRequest) {

        // Crear token de autenticación con username + password recibidos
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userLoginDtoRequest.username(),
                userLoginDtoRequest.password()
        );

        // Spring Security valida usando tu CustomUserDetailsService + PasswordEncoder
        authenticationManager.authenticate(authentication);

        // Buscar usuario en dominio
        UserModel userModel = userRepositoryPort.findByUsername(userLoginDtoRequest.username())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Generar JWT con utilitario
        String token = jwtUtils.generateToken(userModel.username(), userModel.role().name());

        return new LoginDtoResponse(token);
    }
}
