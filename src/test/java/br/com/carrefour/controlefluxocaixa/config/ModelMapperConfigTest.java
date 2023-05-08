package br.com.carrefour.controlefluxocaixa.config;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ModelMapperConfigTest {

    @Autowired
    private ModelMapperConfig modelMapperConfig;

    @Test
    void testModelMapper() {
        ModelMapper modelMapper = modelMapperConfig.modelMapper();
        assertNotNull(modelMapper);
    }

    @Test
    void testModelMapperSingleton() {
        ModelMapper modelMapper1 = modelMapperConfig.modelMapper();
        ModelMapper modelMapper2 = modelMapperConfig.modelMapper();
        assertTrue(modelMapper1 == modelMapper2);
    }
}
