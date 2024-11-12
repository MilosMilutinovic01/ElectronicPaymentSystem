package org.bankexample.bankbackend.mapper;

import org.bankexample.bankbackend.dto.CreatePaymentRequestDTO;
import org.bankexample.bankbackend.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);
    Payment requestDtoToModel(CreatePaymentRequestDTO paymentRequestDTO);
}
