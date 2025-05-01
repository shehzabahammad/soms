package com.soms.user_service.service;

import com.soms.user_service.dto.CredentialDtoRequest;
import com.soms.user_service.dto.CredentialDtoResponse;

public interface CredentialService {

    CredentialDtoResponse createCredential(CredentialDtoRequest credentialDtoRequest);
}
