package org.simarro.adt6_practica3.utils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {


    @Bean
    OpenAPI customOpenAPI() {

        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Gestión Usuarios API")
                        .description("Descripción gestión de usuarios API")
                        .contact(new Contact()
                                .name("Miroslava Ivanova")
                                .email("mirivageo@alu.edu.gva.es")
                                .url("https://ieslluissimarro.org/"))
                        .version("1.0"));
    }
}
