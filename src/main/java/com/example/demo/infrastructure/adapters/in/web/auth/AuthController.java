package com.example.demo.infrastructure.adapters.in.web.auth;

import com.example.demo.application.usecases.auth.AuthUseCase;
import com.example.demo.infrastructure.adapters.in.web.dtos.requests.UserLoginDtoRequest;
import com.example.demo.infrastructure.adapters.in.web.dtos.requests.UserRegisterDtoRequest;
import com.example.demo.infrastructure.adapters.in.web.dtos.responses.LoginDtoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Registrar un nuevo usuario",
            description = "Crea un usuario con username, password y rol (USER por defecto)",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuario creado correctamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o username ya existe")
            }
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            ref = "#/components/requestBodies/UserRegisterDtoRequestBody"
    )
    public void register(@Valid @RequestBody UserRegisterDtoRequest userRegisterDtoRequest) {
        authUseCase.register(userRegisterDtoRequest);
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login de usuario",
            description = "Autentica un usuario y devuelve un token JWT",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario autenticado correctamente, devuelve JWT"),
                    @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
            }
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            ref = "#/components/requestBodies/UserLoginDtoRequestBody"
    )
    public LoginDtoResponse login(@Valid @RequestBody UserLoginDtoRequest userLoginDtoRequest) {
        return authUseCase.login(userLoginDtoRequest);
    }
}
