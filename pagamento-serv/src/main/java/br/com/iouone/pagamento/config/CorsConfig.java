package br.com.iouone.pagamento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200",
                                "https://www.iouone.com.br",
                                "https://iouone.com.br",
                                "https://www.iouone-hml.iouone.com.br",
                                "https://iouone-hml.iouone.com.br",
                                "http://www.iouone.com.br",
                                "http://www.iouone-hml.iouone.com.br",
                                "http://iouone.com.br",
                                "http://iouone-hml.iouone.com.br")// Permite todas as rotas
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                        .allowedHeaders("*") // Permite todos os headers
                        .allowCredentials(true);
            }
        };
    }
}
