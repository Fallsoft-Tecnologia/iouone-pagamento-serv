package br.com.iouone.pagamento.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class FeignConfig {

    @Value("${pagarme.api.username}")
    private String username;

    @Value("${pagarme.api.password}")
    private String password;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            template.header("Authorization", "Basic " + encodedAuth);
        };
    }
}
