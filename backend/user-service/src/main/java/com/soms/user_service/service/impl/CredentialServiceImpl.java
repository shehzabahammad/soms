package com.soms.user_service.service.impl;

import com.soms.user_service.dto.CredentialDtoRequest;
import com.soms.user_service.dto.CredentialDtoResponse;
import com.soms.user_service.dto.LoginDtoRequest;
import com.soms.user_service.dto.LoginDtoResponse;
import com.soms.user_service.entity.CredentialEntity;
import com.soms.user_service.entity.UserEntity;
import com.soms.user_service.mapper.CredentialMapper;
import com.soms.user_service.repository.CredentialRepository;
import com.soms.user_service.repository.UserRepository;
import com.soms.user_service.service.CredentialService;
import com.soms.user_service.util.Constants;
import com.soms.user_service.util.JwtUtils;
import com.soms.user_service.util.RepositoryUtil;
import jakarta.transaction.InvalidTransactionException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {
    private final CredentialRepository credentialRepository;
    private final UserRepository userRepository;
    private final CredentialMapper credentialMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

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

    @Override
    public LoginDtoResponse login(LoginDtoRequest loginDtoRequest) throws InvalidTransactionException {
        var cred = this.credentialRepository.findByUserName(loginDtoRequest.userName());
        if (!ObjectUtils.isEmpty(cred) && BCrypt.checkpw(loginDtoRequest.password(), cred.getPassword())) {
            var token = this.jwtUtils.generateToken(cred);
            return new LoginDtoResponse(String.valueOf(cred.getId()), token);
        }
        throw new InvalidTransactionException("Invalid credentials");
    }
}
