package org.bankexample.bankbackend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bankexample.bankbackend.dto.LoginRequestDTO;
import org.bankexample.bankbackend.dto.ResponseDTO;
import org.bankexample.bankbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
    private UserRepository userRepository;

    private String generateToken(Authentication authentication) {
        Instant now = Instant.now();

        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
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

            return new ResponseEntity(new ResponseDTO("User logged in successfully", token), HttpStatus.OK) ;
        }catch (Exception e){
            return new ResponseEntity("Authentication error!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
