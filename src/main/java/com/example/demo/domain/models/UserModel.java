package com.example.demo.domain.models;

import com.example.demo.domain.models.enums.RoleUserEnum;

public record UserModel(
   Long id,
   String username,
   String password,
   RoleUserEnum role
) {}
