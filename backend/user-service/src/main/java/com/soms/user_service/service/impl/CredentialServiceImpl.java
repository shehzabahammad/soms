package com.soms.user_service.service.impl;

import com.soms.user_service.dto.CredentialDtoRequest;
import com.soms.user_service.dto.CredentialDtoResponse;
import com.soms.user_service.entity.CredentialEntity;
import com.soms.user_service.mapper.CredentialMapper;
import com.soms.user_service.repository.CredentialRepository;
import com.soms.user_service.service.CredentialService;
import com.soms.user_service.util.RepositoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialServiceImpl implements CredentialService {
    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private CredentialMapper credentialMapper;

    @Override
    public CredentialDtoResponse createCredential(CredentialDtoRequest credentialDtoRequest) {
        CredentialEntity credentialEntity = this.credentialMapper.toEntity(credentialDtoRequest);
        RepositoryUtil.addCommonFields(credentialEntity);
        credentialEntity = this.credentialRepository.save(credentialEntity);
        return this.credentialMapper.toDtoResponse(credentialEntity);
    }
}
