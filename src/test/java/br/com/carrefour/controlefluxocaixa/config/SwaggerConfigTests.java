package br.com.carrefour.controlefluxocaixa.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SwaggerConfigTests {

    @Autowired
    private OpenAPI openAPI;

    @Test
    void testCustomOpenAPI() {
        assertNotNull(openAPI);
        assertEquals("Controle de Fluxo de Caixa API", openAPI.getInfo().getTitle());
        assertEquals("API para controle de fluxo de caixa de um comerciante", openAPI.getInfo().getDescription());
        assertEquals("1.0.0", openAPI.getInfo().getVersion());
    }

}

