package com.qwist.orders.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Swagger info data
 */
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Paweł Gwóźdź",
                        email = "pawel1012001@gmail.com"
                ),
                description = "OpenApi documentation for Qwist task",
                title = "Orders app",
                version = "1.0"
        )
)
public class OpenApiConfig {
}
