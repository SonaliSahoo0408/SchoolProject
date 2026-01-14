package com.nimblix.SchoolPEPProject.Security;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "E-Fuel API Documentation",
                version = "1.0",
                description = "API documentation for E-Fuel backend"
        )
)
public class SwaggerConfig {
}
