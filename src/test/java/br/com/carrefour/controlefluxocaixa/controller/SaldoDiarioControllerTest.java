package br.com.carrefour.controlefluxocaixa.controller;

import br.com.carrefour.controlefluxocaixa.dto.SaldoDiarioDTO;
import br.com.carrefour.controlefluxocaixa.service.SaldoDiarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaldoDiarioControllerTest {

    @Mock
    private SaldoDiarioService saldoDiarioService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SaldoDiarioController saldoDiarioController;

    @Test
    void testCalcularSaldoDiario() {
        LocalDate data = LocalDate.of(2022, 1, 1);
        SaldoDiarioDTO saldoDiarioDTO = new SaldoDiarioDTO();
        when(saldoDiarioService.calcularSaldoDiario(data)).thenReturn(saldoDiarioDTO);
        when(modelMapper.map(saldoDiarioDTO, SaldoDiarioDTO.class)).thenReturn(saldoDiarioDTO);

        SaldoDiarioDTO result = saldoDiarioController.calcularSaldoDiario(data);

        assertEquals(saldoDiarioDTO, result);
    }

    @Test
    void testCalcularSaldoDiarioComDataNula() {
        LocalDate data = null;

        SaldoDiarioDTO result = saldoDiarioController.calcularSaldoDiario(data);

        assertNull(result);
    }

    @Test
    void testCalcularSaldoDiarioComDataInvalida() {
        LocalDate data = LocalDate.of(2022, 12, 31);

        SaldoDiarioDTO result = saldoDiarioController.calcularSaldoDiario(data);

        assertNull(result);
    }

    @Test
    void testCalcularSaldoDiarioComSaldoDiarioServiceRetornandoNull() {
        LocalDate data = LocalDate.of(2022, 1, 1);
        when(saldoDiarioService.calcularSaldoDiario(data)).thenReturn(null);

        SaldoDiarioDTO result = saldoDiarioController.calcularSaldoDiario(data);

        assertNull(result);
    }

    @Test
    void testCalcularSaldoDiarioComModelMapperRetornandoNull() {
        LocalDate data = LocalDate.of(2022, 1, 1);
        SaldoDiarioDTO saldoDiarioDTO = new SaldoDiarioDTO();
        when(saldoDiarioService.calcularSaldoDiario(data)).thenReturn(saldoDiarioDTO);
        when(modelMapper.map(saldoDiarioDTO, SaldoDiarioDTO.class)).thenReturn(null);

        SaldoDiarioDTO result = saldoDiarioController.calcularSaldoDiario(data);

        assertNull(result);
    }
}


