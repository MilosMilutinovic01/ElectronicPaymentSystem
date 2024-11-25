package org.example.webshopbackend.service;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.webshopbackend.Utils.EnvUtils;
import org.example.webshopbackend.dto.ApiKeyResponseDTO;
import org.example.webshopbackend.dto.BuyPackageResponseDTO;
import org.example.webshopbackend.dto.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ApiKeyService {

    private final Dotenv dotenv;

    public ApiKeyService() {
        // Load the .env file from the root directory
        dotenv = Dotenv.configure()
                .directory("./") // Specify the directory containing the .env file
                .load();
    }

    /**
     * Retrieves the merchant API key from the .env file.
     *
     * @return a ResponseEntity containing the API key or a not-found status.
     */
    public ResponseEntity<ApiKeyResponseDTO> getApiKey() {
        String apiKey = dotenv.get("MERCHANT_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            return new ResponseEntity<>(new ApiKeyResponseDTO(""), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiKeyResponseDTO(apiKey), HttpStatus.OK);
    }

    /**
     * Temporarily sets the merchant API key for the runtime session.
     *
     * @param apiKey the API key to set.
     * @return a ResponseEntity indicating success or failure.
     */
    public ResponseEntity<MessageDTO> setApiKey(String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            return new ResponseEntity<>(new MessageDTO("API key cannot be null or empty."), HttpStatus.BAD_REQUEST);
        }

        try {
            // Update the .env file with the provided API key (update or append)
            EnvUtils.updateApiKeyInEnv(apiKey);

            // Optionally, set the system property for the current runtime session
            System.setProperty("MERCHANT_API_KEY", apiKey);

            return new ResponseEntity<>(new MessageDTO("API key successfully set for runtime and saved to .env file."), HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(new MessageDTO("Error writing to .env file."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}