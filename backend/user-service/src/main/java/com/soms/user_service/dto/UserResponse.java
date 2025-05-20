package com.soms.user_service.dto;

public record UserResponse(String id,
                           String firstName, String lastName, String emailId,
                           String phoneNumber, String houseNumberName, String streetName,
                           String city, String postalCode, String country) {
}
