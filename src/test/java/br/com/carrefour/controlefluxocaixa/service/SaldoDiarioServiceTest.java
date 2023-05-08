package br.com.carrefour.controlefluxocaixa.service;

import br.com.carrefour.controlefluxocaixa.dto.SaldoDiarioDTO;
import br.com.carrefour.controlefluxocaixa.model.Lancamento;
import br.com.carrefour.controlefluxocaixa.repository.LancamentoRepository;
import br.com.carrefour.controlefluxocaixa.strategy.Credito;
import br.com.carrefour.controlefluxocaixa.strategy.Debito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SaldoDiarioServiceTest {

    @InjectMocks
    private SaldoDiarioService saldoDiarioService;

    @Mock
    private LancamentoRepository lancamentoRepository;

    @Mock
    private Credito lancamentoCredito;

    @Mock
    private Debito lancamentoDebito;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalcularSaldoDiario() {
        List<Lancamento> lancamentos = new ArrayList<>();
        Lancamento lancamento1 = new Lancamento();
        lancamento1.setTipo("credito");
        lancamento1.setValor(new BigDecimal("100.00"));
        lancamentos.add(lancamento1);
        Lancamento lancamento2 = new Lancamento();
        lancamento2.setTipo("debito");
        lancamento2.setValor(new BigDecimal("50.00"));
        lancamentos.add(lancamento2);

        when(lancamentoRepository.findByData(LocalDate.now())).thenReturn(lancamentos);
        when(lancamentoCredito.calcular(lancamentos)).thenReturn(new BigDecimal("100.00"));
        when(lancamentoDebito.calcular(lancamentos)).thenReturn(new BigDecimal("50.00"));

        SaldoDiarioDTO saldoDiarioDTO = saldoDiarioService.calcularSaldoDiario(LocalDate.now());

        assertEquals(LocalDate.now(), saldoDiarioDTO.getData());
        assertEquals(new BigDecimal("50.00"), saldoDiarioDTO.getSaldo());
    }


}
