package br.com.carrefour.controlefluxocaixa.strategy;

import br.com.carrefour.controlefluxocaixa.model.Lancamento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Credito implements LancamentoStrategy {
    @Override
    public BigDecimal calcular(List<Lancamento> lancamentos) {
        return lancamentos.stream()
                .filter(lancamento -> lancamento.getTipo().equalsIgnoreCase("credito"))
                .map(Lancamento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
