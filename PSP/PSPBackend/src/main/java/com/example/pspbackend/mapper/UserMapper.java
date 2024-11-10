package com.example.pspbackend.mapper;

import com.example.pspbackend.dto.RegistrationDTO;
import com.example.pspbackend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User registrationDtoToModel(RegistrationDTO registrationDTO);
}
