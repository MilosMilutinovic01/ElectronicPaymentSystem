package org.bankexample.bankbackend.mapper;

import org.bankexample.bankbackend.dto.CreateMerchantDTO;
import org.bankexample.bankbackend.model.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MerchantMapper {
    MerchantMapper INSTANCE = Mappers.getMapper(MerchantMapper.class);
    Merchant createDtoToModel(CreateMerchantDTO createMerchantDTO);
}
