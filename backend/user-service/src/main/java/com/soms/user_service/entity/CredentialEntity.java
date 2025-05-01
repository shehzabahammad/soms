package com.soms.user_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "credential")
@Getter
@Setter
public class CredentialEntity extends CommonEntityField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "email_id")
    private String emailId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private String role;
}
