package com.example.demo.infrastructure.adapters.out.jpa.adapters;

import com.example.demo.domain.models.UserModel;
import com.example.demo.domain.ports.out.UserRepositoryPort;
import com.example.demo.infrastructure.adapters.out.jpa.entites.UserEntity;
import com.example.demo.infrastructure.adapters.out.jpa.mappers.UserMapperModel;
import com.example.demo.infrastructure.adapters.out.jpa.repositories.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserJpaAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapperModel userMapperModel;

    public UserJpaAdapter(UserJpaRepository userJpaRepository, UserMapperModel userMapperModel) {
        this.userJpaRepository = userJpaRepository;
        this.userMapperModel = userMapperModel;
    }

    @Override
    public UserModel save(UserModel userModel) {
        UserEntity userEntity = userMapperModel.toEntity(userModel);
        UserEntity savedUser = userJpaRepository.save(userEntity);
        return userMapperModel.toModel(savedUser);
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(userMapperModel::toModel);
    }
}
