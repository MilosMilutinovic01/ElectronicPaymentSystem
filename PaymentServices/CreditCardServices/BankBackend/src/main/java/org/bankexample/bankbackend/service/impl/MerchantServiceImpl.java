package org.bankexample.bankbackend.service.impl;

import org.bankexample.bankbackend.dto.CreateMerchantDTO;
import org.bankexample.bankbackend.dto.EditMerchantDTO;
import org.bankexample.bankbackend.exception.MerchantAlreadyExistsException;
import org.bankexample.bankbackend.exception.MerchantDoesNotExistException;
import org.bankexample.bankbackend.mapper.MerchantMapper;
import org.bankexample.bankbackend.model.Merchant;
import org.bankexample.bankbackend.repository.MerchantRepository;
import org.bankexample.bankbackend.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public void addMerchant(CreateMerchantDTO createMerchantDTO) {
        if (merchantRepository.existsByMerchantId(createMerchantDTO.getMerchantId())) {
            throw new MerchantAlreadyExistsException(createMerchantDTO.getMerchantId());
        }
        Merchant merchant = MerchantMapper.INSTANCE.createDtoToModel(createMerchantDTO);
        merchantRepository.save(merchant);
    }

    @Override
    public void editMerchant(String merchantId, EditMerchantDTO editMerchantDTO) {
        Merchant merchant = merchantRepository.findByMerchantId(merchantId)
                .orElseThrow(() -> new MerchantDoesNotExistException(merchantId));
        merchant.setMerchantName(editMerchantDTO.getMerchantName());
        merchant.setPassword(editMerchantDTO.getPassword());
        merchantRepository.save(merchant);
    }
}
