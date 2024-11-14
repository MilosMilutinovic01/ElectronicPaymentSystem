package org.bankexample.bankbackend.service;

import org.bankexample.bankbackend.dto.merchant.CreateMerchantDTO;
import org.bankexample.bankbackend.dto.merchant.EditMerchantDTO;

public interface MerchantService {

     void addMerchant(CreateMerchantDTO createMerchantDTO);
     void editMerchant(String merchantId, EditMerchantDTO editMerchantDTO);
     String getMerchantPan(String merchantId);
     void authenticateMerchant(String merchantId, String password);
}
