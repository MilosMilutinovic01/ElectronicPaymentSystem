package org.bankexample.bankbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.bankexample.bankbackend.dto.merchant.CreateMerchantDTO;
import org.bankexample.bankbackend.dto.merchant.EditMerchantDTO;
import org.bankexample.bankbackend.exception.MerchantAlreadyExistsException;
import org.bankexample.bankbackend.exception.MerchantAuthFailedException;
import org.bankexample.bankbackend.exception.MerchantDoesNotExistException;
import org.bankexample.bankbackend.mapper.MerchantMapper;
import org.bankexample.bankbackend.model.Merchant;
import org.bankexample.bankbackend.repository.MerchantRepository;
import org.bankexample.bankbackend.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
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

    @Override
    public String getMerchantAccountNumber(String merchantId) {
        Merchant merchant = merchantRepository.findByMerchantId(merchantId)
                .orElseThrow(() -> new MerchantDoesNotExistException(merchantId));
        return merchant.getBankAccountNumber();
    }

    @Override
    public void authenticateMerchant(String merchantId, String password) {
        Merchant merchant = merchantRepository.findByMerchantId(merchantId)
                .orElseThrow(() -> new MerchantDoesNotExistException(merchantId));
        if (!encoder.matches(password, merchant.getPassword())) {
            throw new MerchantAuthFailedException();
        }

    }
}
