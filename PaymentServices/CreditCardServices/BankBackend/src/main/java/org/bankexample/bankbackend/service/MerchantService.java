package org.bankexample.bankbackend.service;

import org.bankexample.bankbackend.dto.CreateMerchantDTO;
import org.bankexample.bankbackend.dto.EditMerchantDTO;

public interface MerchantService {

     void addMerchant(CreateMerchantDTO createMerchantDTO);
     void editMerchant(String merchantId, EditMerchantDTO editMerchantDTO);
     String getMerchantPan(String merchantId);
}
