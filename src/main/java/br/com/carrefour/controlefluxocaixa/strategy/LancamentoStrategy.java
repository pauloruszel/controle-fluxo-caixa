package br.com.carrefour.controlefluxocaixa.strategy;

import br.com.carrefour.controlefluxocaixa.model.Lancamento;

import java.math.BigDecimal;
import java.util.List;

public interface LancamentoStrategy {
    BigDecimal calcular(List<Lancamento> lancamentos);
}
