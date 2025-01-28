package org.imdb.databaseexmaple.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ImageProcessor {

    private final RestTemplate restTemplate;

    @Value("${omdb.api.key}")
    private String apiKey;

    @Value("${omdb.api.url}")
    private String apiUrl;

    public String getMovieInfo(String movieTitle) {
        String urlString = apiUrl + "?t=" + movieTitle + "&apikey=" + apiKey;
        return restTemplate.getForObject(urlString, String.class);
    }
}
