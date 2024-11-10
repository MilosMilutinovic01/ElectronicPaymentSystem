package org.example.webshopbackend.mapper;

import org.example.webshopbackend.dto.RegistrationDTO;
import org.example.webshopbackend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User registrationDtoToModel(RegistrationDTO registrationDTO);
}

