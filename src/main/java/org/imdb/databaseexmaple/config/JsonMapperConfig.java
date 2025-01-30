package org.imdb.databaseexmaple.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

public class JsonMapperConfig {

    @Bean
    public ObjectMapper jsonMapper() {
        return new ObjectMapper();
    }
}
