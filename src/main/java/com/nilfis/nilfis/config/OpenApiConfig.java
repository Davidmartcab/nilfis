package com.nilfis.nilfis.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Nilfis API",
                version = "1.0.0",
                description = "This document provides an overview of the endpoints available in the NILFIS API."
        )
)
public class OpenApiConfig {
}
