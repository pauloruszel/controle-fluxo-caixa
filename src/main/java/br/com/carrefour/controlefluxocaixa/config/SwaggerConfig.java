package br.com.carrefour.controlefluxocaixa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Controle de Fluxo de Caixa API")
                        .description("API para controle de fluxo de caixa de um comerciante")
                        .version("1.0.0"));
    }
}
