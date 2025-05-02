package com.soms.user_service.service;

import com.soms.user_service.dto.CredentialDtoRequest;
import com.soms.user_service.dto.CredentialDtoResponse;
import com.soms.user_service.dto.LoginDtoRequest;
import com.soms.user_service.dto.LoginDtoResponse;
import jakarta.transaction.InvalidTransactionException;

public interface CredentialService {

    CredentialDtoResponse createCredential(CredentialDtoRequest credentialDtoRequest);

    LoginDtoResponse login(LoginDtoRequest loginDtoRequest) throws InvalidTransactionException;
}
