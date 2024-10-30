package com.example.swh_back.Login;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webconfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allows CORS requests to all endpoints
                .allowedOrigins("http://localhost:3000")  // Allows requests from this origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allows these HTTP methods
                .allowedHeaders("*");  // Allows all headers
    }
}