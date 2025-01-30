package org.imdb.databaseexmaple.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageProcessor {

    private final RestTemplate restTemplate;

    @Value("${omdb.api.key}")
    private String apiKey;

    @Value("${omdb.api.url}")
    private String apiUrl;

    public String getMoviePicture(String movieTitle) {
        try {
            // Construct the API URL
            String apiUrlWithParams = String.format("%s?apikey=%s&t=%s", apiUrl, apiKey, movieTitle);

            // Call the API and get the response as a Map
            Map<String, Object> response = restTemplate.getForObject(apiUrlWithParams, Map.class);

            String posterUrl = "";
            // Check if the response contains the "Poster" field
            if (response != null && "True".equals(response.get("Response"))) {
                posterUrl = (String) response.get("Poster");
                if (posterUrl != null && !posterUrl.isEmpty()) {
                    // Download the image and convert it to Base64
                    return posterUrl;
                }
            }

        } catch (RestClientException e) {
            // Handle the exception (e.g., log it)
            e.printStackTrace();
        }
        return null; // Or handle the case where no image is found
    }

    private String convertImageToBase64(String imageUrl) {
        try {
            // Create a URL object
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();

            // Read the image from the connection
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;

            // Read the image bytes
            while ((bytesRead = input.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            // Convert the byte array to a Base64 encoded string
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            // Handle exceptions (e.g., log them)
            e.printStackTrace();
        }
        return null; // Or handle the error case
    }
}