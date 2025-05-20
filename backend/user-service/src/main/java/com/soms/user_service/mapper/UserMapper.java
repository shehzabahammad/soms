package com.soms.user_service.mapper;

import com.soms.user_service.dto.UserResponse;
import com.soms.user_service.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public abstract class UserMapper {

    public abstract UserResponse toDTOResponse(UserEntity userEntity);
}
