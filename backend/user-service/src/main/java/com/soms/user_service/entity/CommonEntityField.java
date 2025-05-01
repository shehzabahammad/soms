package com.soms.user_service.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class CommonEntityField {
    private Date created;
    private Date modified;
    private Boolean deleted;
}
