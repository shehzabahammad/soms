package com.soms.user_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users_data")
@Getter
@Setter
public class UserEntity extends CommonEntityField {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    @JoinColumn(name = "credential_id", referencedColumnName = "id", nullable = false)
    private CredentialEntity credential;
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNumber;
    private String houseNumberName;
    private String streetName;
    private String city;
    private String postalCode;
    private String country;
    private String role;
}
