package br.com.carrefour.controlefluxocaixa.service;

import br.com.carrefour.controlefluxocaixa.dto.SaldoDiarioDTO;
import br.com.carrefour.controlefluxocaixa.model.Lancamento;
import br.com.carrefour.controlefluxocaixa.repository.LancamentoRepository;
import br.com.carrefour.controlefluxocaixa.strategy.LancamentoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaldoDiarioService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    @Qualifier("credito")
    private LancamentoStrategy lancamentoCredito;

    @Autowired
    @Qualifier("debito")
    private LancamentoStrategy lancamentoDebito;

    public SaldoDiarioDTO calcularSaldoDiario(LocalDate data) {
        List<Lancamento> lancamentos = lancamentoRepository.findByData(data);

        var creditos = lancamentoCredito.calcular(lancamentos);
        var debitos = lancamentoDebito.calcular(lancamentos);

        var saldo = creditos.subtract(debitos);

        return SaldoDiarioDTO.builder()
                .data(data)
                .saldo(saldo)
                .build();
    }
}
