package br.com.carrefour.controlefluxocaixa.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaldoDiarioDTO {
    private LocalDate data;
    private BigDecimal saldoConsolidado;
    private BigDecimal saldoNegativo;
    private BigDecimal saldoTotal;
}
