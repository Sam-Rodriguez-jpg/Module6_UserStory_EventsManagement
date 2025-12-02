package com.example.demo.infrastructure.adapters.out.jpa.mappers;

import com.example.demo.domain.models.UserModel;
import com.example.demo.infrastructure.adapters.out.jpa.entites.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperModel {
    UserModel toModel(UserEntity userEntity);

    UserEntity toEntity(UserModel userModel);
}
