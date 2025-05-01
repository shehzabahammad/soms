package com.soms.user_service.mapper;

import com.soms.user_service.dto.CredentialDtoRequest;
import com.soms.user_service.dto.CredentialDtoResponse;
import com.soms.user_service.entity.CredentialEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public abstract class CredentialMapper {

    public abstract CredentialEntity toEntity(CredentialDtoRequest credentialDtoRequest);

    public abstract CredentialDtoResponse toDtoResponse(CredentialEntity credentialEntity);
}
