//package org.imdb.databaseexmaple.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("http://localhost:5500")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // You can specify allowed HTTP methods
//                .allowedHeaders("*"); // Allows all headers in the requests
//    }
//}