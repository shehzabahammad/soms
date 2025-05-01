package com.soms.user_service.dto;

public record CredentialRequest(String userName, String password, String emailId, String firstName, String lastName) {
}
