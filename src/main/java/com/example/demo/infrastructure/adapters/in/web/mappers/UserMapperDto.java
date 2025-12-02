package com.example.demo.infrastructure.adapters.in.web.mappers;

import com.example.demo.domain.models.UserModel;
import com.example.demo.infrastructure.adapters.in.web.dtos.requests.UserRegisterDtoRequest;
import com.example.demo.infrastructure.adapters.in.web.dtos.responses.UserDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapperDto {

    @Mapping(target = "id", ignore = true)
    UserModel toModel(UserRegisterDtoRequest userRegisterDtoRequest);

    UserDtoResponse toResponse(UserModel userModel);
}
