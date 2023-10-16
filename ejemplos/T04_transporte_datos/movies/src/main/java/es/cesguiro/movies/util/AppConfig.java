package es.cesguiro.movies.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Clase de configuración
@Configuration
public class AppConfig {
    @Value("${application.url}")
    private String URL;

    @Bean
    public String applicationURL() {
        return URL;
    }
}
