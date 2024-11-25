package com.example.pspbackend.service;

import com.example.pspbackend.auth.AuthUser;
import com.example.pspbackend.dto.*;
import com.example.pspbackend.mapper.ClientMapper;
import com.example.pspbackend.mapper.UserMapper;
import com.example.pspbackend.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.example.pspbackend.repository.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private IUserRepository IUserRepository;
    @Autowired
    private IClientRepository IClientRepository;
    @Autowired
    private IPaymentMethodsRepository paymentMethodsRepository;
    @Autowired
    private IClientMethodRepository clientMethodsRepository;

    private String generateToken(Authentication authentication) {
        Instant now = Instant.now();

        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        log.info("Principal class: {}", authentication.getPrincipal().getClass());
        String userId = ((User) authentication.getPrincipal()).getId().toString();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .claim("userId", userId)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public ResponseEntity login(LoginRequestDTO userLogin){
        try{
            Authentication authentication =
                    authenticationManager
                            .authenticate(new UsernamePasswordAuthenticationToken(
                                    userLogin.getUsername(),
                                    userLogin.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("Token requested for user :{}", authentication.getAuthorities());
            String token = generateToken(authentication);


            Optional<User> foundUser = IUserRepository.findByUsername(userLogin.getUsername());
            if(foundUser.isEmpty()) {
                return new ResponseEntity(new MessageResponseDTO("Login failed! Username does not exist."), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity(new ResponseDTO("User logged in successfully",  token), HttpStatus.OK) ;
        }catch (Exception e){
            return new ResponseEntity("Error while authentication!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity registration(RegistrationDTO registration){
        try {
            RegistrationUserDTO registrationUserDTO = new RegistrationUserDTO(
                    registration.getName(),
                    registration.getUsername(),
                    registration.getPassword()
            );
            Client client = ClientMapper.INSTANCE.registrationDtoToModel(registrationUserDTO);
            Optional<User> foundUser = IUserRepository.findByUsername(client.getUsername());
            if(foundUser.isPresent()) {
                return new ResponseEntity(new RegistrationResponseDTO("Registration failed! Username already exists.", null, null), HttpStatus.BAD_REQUEST);
            }
            client.setPassword(encoder.encode(client.getPassword()));
            client.setRole(Role.CLIENT);

            client = IClientRepository.save(client);

            // add payment methods
            List<ClientMethods> clientMethodsList = new ArrayList<>();
            Client finalClient = client;
            registration.getPaymentMethods().forEach(m->{
                Optional<PaymentMethods> method = paymentMethodsRepository.findByName(m.getName());
                if(method.isPresent()) {
                    clientMethodsList.add(ClientMethods.builder()
                            .client(finalClient)
                            .method(method.get())
                            .build());
                }
            });

            clientMethodsRepository.saveAll(clientMethodsList);

            // generate merchant password
            String merchantPassword = generateMerchantPassword();
            client.setMerchantPassword(encoder.encode(merchantPassword));
            client = IClientRepository.save(client);

            // request to bank
            if(registration.getPaymentMethods().stream()
                    .filter(method -> method.getName().equals("Card")).count() != 0) {
                createMerchantInBank(client.getName(), client.getId().toString(), client.getMerchantPassword(), registration.getBankAccount());
            }
            return new ResponseEntity(new RegistrationResponseDTO("User registered successfully", client.getId().toString(), merchantPassword), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new RegistrationResponseDTO("User registration failed", null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String generateMerchantPassword() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom RANDOM = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(index));
        }


        return stringBuilder.toString();
    }

    private void createMerchantInBank(String merchantName,
                                       String merchantId,
                                       String password,
                                       String bankAccountNumber) {

        CreateMerchantDTO merchantDTO = CreateMerchantDTO.builder()
                .merchantName(merchantName)
                .merchantId(merchantId)
                .password(password)
                .bankAccountNumber(bankAccountNumber)
                .build();

        WebClient.create()
                .post()
                .uri("http://localhost:8080/api/merchant")
                .bodyValue(merchantDTO)
                .retrieve()
                .toBodilessEntity()
                .subscribe(response -> log.info("Service responded: " + response),
                        error -> log.error("Error occurred while creating merchant", error));

    }
}
