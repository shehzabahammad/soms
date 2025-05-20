package com.soms.user_service.service.impl;

import com.soms.user_service.dto.UserResponse;
import com.soms.user_service.entity.UserEntity;
import com.soms.user_service.mapper.UserMapper;
import com.soms.user_service.repository.UserRepository;
import com.soms.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void addUser(UserEntity userEntity) {
        this.userRepository.save(userEntity);
    }

    @Override
    public UserResponse getUserByCredentialId(String credentialId) {
        var res = this.userMapper.toDTOResponse(this.userRepository.findByCredentialId(UUID.fromString(credentialId)).orElse(null));
        if (res != null) {
            return res;
        }
        throw new ObjectNotFoundException(UserEntity.class, "User not found");
    }
}
