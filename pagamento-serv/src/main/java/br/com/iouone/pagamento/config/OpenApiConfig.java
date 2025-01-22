package br.com.iouone.pagamento.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "IOUONE API", version = "v2"),
        security = {@SecurityRequirement(name = SecurityConstants.BEARER_AUTH)},
        servers = {@Server(url = "${springdoc.swagger-ui.server-url}", description = "Dynamic Server URL")}
)
@SecuritySchemes({
        @SecurityScheme(
                name = SecurityConstants.BEARER_AUTH,
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT"
        )
})
public class OpenApiConfig {
}

