package com.soms.user_service.service.impl;

import com.soms.user_service.dto.CredentialDtoRequest;
import com.soms.user_service.dto.CredentialDtoResponse;
import com.soms.user_service.entity.CredentialEntity;
import com.soms.user_service.entity.UserEntity;
import com.soms.user_service.mapper.CredentialMapper;
import com.soms.user_service.repository.CredentialRepository;
import com.soms.user_service.repository.UserRepository;
import com.soms.user_service.service.CredentialService;
import com.soms.user_service.util.Constants;
import com.soms.user_service.util.RepositoryUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {
    private final CredentialRepository credentialRepository;
    private final UserRepository userRepository;
    private final CredentialMapper credentialMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public CredentialDtoResponse createCredential(CredentialDtoRequest credentialDtoRequest) {
        try {
            CredentialEntity credentialEntity = this.credentialMapper.toEntity(credentialDtoRequest);
            credentialEntity.setRole(Constants.ROLE_USER);
            credentialEntity.setEnabled(true);
            credentialEntity.setPassword(passwordEncoder.encode(credentialEntity.getPassword()));
            RepositoryUtil.addCommonFields(credentialEntity);
            credentialEntity = this.credentialRepository.save(credentialEntity);

            UserEntity userEntity = new UserEntity();
            userEntity.setCredential(credentialEntity);
            userEntity.setRole(Constants.ROLE_USER);
            userEntity.setFirstName(credentialEntity.getFirstName());
            userEntity.setLastName(credentialEntity.getLastName());
            userEntity.setEmailId(credentialEntity.getEmailId());
            userRepository.save(userEntity);

            return this.credentialMapper.toDtoResponse(credentialEntity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create credential and user", e);
        }
    }
}
