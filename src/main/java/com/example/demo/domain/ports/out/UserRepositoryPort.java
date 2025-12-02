package com.example.demo.domain.ports.out;

import com.example.demo.domain.models.UserModel;

import java.util.Optional;

public interface UserRepositoryPort {
    UserModel save(UserModel userModel);
    Optional<UserModel> findByUsername(String username);
}
