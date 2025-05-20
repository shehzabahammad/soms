package com.soms.user_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "credential")
@Getter
@Setter
public class CredentialEntity extends CommonEntityField {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "email_id", unique = true)
    private String emailId;
    @Column(unique = true)
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private String role;
}
