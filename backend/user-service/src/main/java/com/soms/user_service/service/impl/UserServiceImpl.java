package com.soms.user_service.service.impl;

import com.soms.user_service.entity.UserEntity;
import com.soms.user_service.repository.UserRepository;
import com.soms.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void addUser(UserEntity userEntity) {
        this.userRepository.save(userEntity);
    }
}
