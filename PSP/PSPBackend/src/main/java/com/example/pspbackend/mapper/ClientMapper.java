package com.example.pspbackend.mapper;

import com.example.pspbackend.dto.RegistrationDTO;
import com.example.pspbackend.model.Client;
import com.example.pspbackend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    Client registrationDtoToModel(RegistrationDTO registrationDTO);
}
