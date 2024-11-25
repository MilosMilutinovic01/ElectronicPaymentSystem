package com.example.pspbackend.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.pspbackend.dto.MessageResponseDTO;
import com.example.pspbackend.dto.PaymentMethodsDTO;
import com.example.pspbackend.dto.RegistrationResponseDTO;
import com.example.pspbackend.mapper.PaymentMethodsMapper;
import com.example.pspbackend.model.Client;
import com.example.pspbackend.model.ClientMethods;
import com.example.pspbackend.model.PaymentMethods;
import com.example.pspbackend.model.User;
import com.example.pspbackend.repository.IClientMethodRepository;
import com.example.pspbackend.repository.IClientRepository;
import com.example.pspbackend.repository.IPaymentMethodsRepository;
import com.example.pspbackend.service.interfaces.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ClientService implements IClientService {
    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IPaymentMethodsRepository paymentMethodsRepository;
    @Autowired
    private IClientMethodRepository clientMethodsRepository;
    @Override
    public ResponseEntity addPaymentMethodsToClient(String clientId, Set<PaymentMethodsDTO> methods) {
        try{
            Optional<Client> optionalClient = clientRepository.findById(UUID.fromString(clientId));
            if(optionalClient.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new MessageResponseDTO("Client does not exist!"));
            }

            // delete old choice
            clientMethodsRepository.deleteAllByClient(optionalClient.get());

            // save new one
            List<ClientMethods> clientMethodsList = new ArrayList<>();
            methods.forEach(m->{
                Optional<PaymentMethods> method = paymentMethodsRepository.findByName(m.getName());
                if(method.isPresent()) {
                    clientMethodsList.add(ClientMethods.builder()
                            .client(optionalClient.get())
                            .method(method.get())
                            .build());
                }
            });

            clientMethodsRepository.saveAll(clientMethodsList);

            return ResponseEntity.ok()
                    .body(new MessageResponseDTO("Succesfully added payment methods to client!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(new MessageResponseDTO("Error while adding payment methods to client!"));
        }
    }

    @Override
    public ResponseEntity findMethodsByClientId(String clientId) {
        try {
            Optional<Client> optionalClient = clientRepository.findById(UUID.fromString(clientId));
            return findMethodsByClient(optionalClient);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageResponseDTO("Error while retrieving payment methods!"));
        }
    }

    @Override
    public ResponseEntity findMethodsByMerchantPassword(String merchantPassword) {
        try {
            Optional<Client> optionalClient = clientRepository.findByMerchantPassword(merchantPassword);
            return findMethodsByClient(optionalClient);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageResponseDTO("Error while retrieving payment methods!"));
        }
    }

    private ResponseEntity findMethodsByClient(Optional<Client> optionalClient) {
        if(optionalClient.isEmpty()){
            return ResponseEntity.badRequest()
                    .body(new MessageResponseDTO("User does not exist!"));
        }

        List<ClientMethods> clientMethods = clientMethodsRepository.findByClient(optionalClient.get());
        Set<PaymentMethods> paymentMethods = new HashSet<>();
        clientMethods.forEach(cm ->{
            paymentMethods.add(cm.getMethod());
        });

        return ResponseEntity.ok()
                .body(PaymentMethodsMapper.INSTANCE.modelToDtoSet(paymentMethods));
    }

}
