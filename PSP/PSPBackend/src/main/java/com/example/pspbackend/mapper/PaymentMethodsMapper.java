package com.example.pspbackend.mapper;

import com.example.pspbackend.dto.PaymentMethodsDTO;
import com.example.pspbackend.model.PaymentMethods;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface PaymentMethodsMapper {
    PaymentMethodsMapper INSTANCE = Mappers.getMapper(PaymentMethodsMapper.class);
    Set<PaymentMethodsDTO> modelToDtoSet(Set<PaymentMethods> methods);
}
