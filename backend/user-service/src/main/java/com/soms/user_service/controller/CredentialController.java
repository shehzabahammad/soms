package com.soms.user_service.controller;

import com.soms.user_service.dto.CredentialDtoRequest;
import com.soms.user_service.dto.CredentialDtoResponse;
import com.soms.user_service.dto.LoginDtoRequest;
import com.soms.user_service.dto.LoginDtoResponse;
import com.soms.user_service.service.CredentialService;
import jakarta.transaction.InvalidTransactionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credential/api/v1")
public class CredentialController {

    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/register")
    public ResponseEntity<CredentialDtoResponse> registerCredential(@RequestBody CredentialDtoRequest credentialDtoRequest) {
        var resp = this.credentialService.createCredential(credentialDtoRequest);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDtoResponse> login(@RequestBody LoginDtoRequest loginDtoRequest) throws InvalidTransactionException {
        var resp = this.credentialService.login(loginDtoRequest);
        return ResponseEntity.ok(resp);
    }


}
