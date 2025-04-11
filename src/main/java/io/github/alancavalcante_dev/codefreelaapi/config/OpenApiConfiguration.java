package io.github.alancavalcante_dev.codefreelaapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Code Freela",
                version = "v1",
                contact = @Contact(
                        name = "Alan Pereira",
                        email = "alan.cavalcante.dev@gmail.com",
                        url = "codefreela.com"
                )
        ),
        security = @SecurityRequirement(name = "bearerAuth")
)
public class OpenApiConfiguration {
}
