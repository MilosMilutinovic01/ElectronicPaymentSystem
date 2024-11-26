package org.example.webshopbackend.Utils;

import java.io.*;
import java.util.*;

public class EnvUtils {

    /**
     * Updates or adds the MERCHANT_API_KEY in the .env file.
     *
     * @param apiKey the API key to set.
     * @throws IOException if there's an issue writing to the .env file.
     */
    public static void updateApiKeyInEnv(String apiKey) throws IOException {
        File envFile = new File(".env");

        // Read all lines from the .env file into a list
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(envFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        // Look for the line that defines MERCHANT_API_KEY and replace it
        boolean keyFound = false;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith("MERCHANT_API_KEY=")) {
                lines.set(i, "MERCHANT_API_KEY=" + apiKey);
                keyFound = true;
                break;
            }
        }

        // If the key is not found, add it to the end of the file
        if (!keyFound) {
            lines.add("MERCHANT_API_KEY=" + apiKey);
        }

        // Write the updated list of lines back to the .env file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(envFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
