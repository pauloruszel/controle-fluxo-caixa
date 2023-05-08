package br.com.carrefour.controlefluxocaixa.controller;

import br.com.carrefour.controlefluxocaixa.dto.LancamentoDTO;
import br.com.carrefour.controlefluxocaixa.service.LancamentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LancamentoControllerTest {

    @Mock
    private LancamentoService lancamentoService;

    @InjectMocks
    private LancamentoController lancamentoController;

    @Test
    public void testAdicionarLancamento() {
        LancamentoDTO lancamentoDTO = new LancamentoDTO();
        LancamentoDTO novoLancamento = new LancamentoDTO();
        when(lancamentoService.adicionarLancamento(any(LancamentoDTO.class))).thenReturn(novoLancamento);
        ResponseEntity<LancamentoDTO> response = lancamentoController.adicionarLancamento(lancamentoDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(novoLancamento, response.getBody());
    }

    @Test
    public void testBuscarLancamento() {
        Long id = 1L;
        LancamentoDTO lancamentoDTO = new LancamentoDTO();
        when(lancamentoService.buscarLancamento(id)).thenReturn(lancamentoDTO);
        LancamentoDTO result = lancamentoController.buscarLancamento(id);
        assertEquals(lancamentoDTO, result);
    }

    @Test
    public void testListarLancamentos() {
        List<LancamentoDTO> lancamentos = List.of(new LancamentoDTO(), new LancamentoDTO());
        when(lancamentoService.listarLancamentos()).thenReturn(lancamentos);
        List<LancamentoDTO> result = lancamentoController.listarLancamentos();
        assertEquals(lancamentos, result);
    }

    @Test
    public void testListarLancamentosPorData() {
        LocalDate data = LocalDate.now();
        List<LancamentoDTO> lancamentos = List.of(new LancamentoDTO(), new LancamentoDTO());
        when(lancamentoService.listarLancamentos(data)).thenReturn(lancamentos);
        List<LancamentoDTO> result = lancamentoController.listarLancamentosPorData(data);
        assertEquals(lancamentos, result);
    }
}