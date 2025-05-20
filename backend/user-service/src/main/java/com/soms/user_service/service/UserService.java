package com.soms.user_service.service;

import com.soms.user_service.dto.UserResponse;
import com.soms.user_service.entity.UserEntity;

public interface UserService {

    void addUser(UserEntity userEntity);

    UserResponse getUserByCredentialId(String credentialId);
}
