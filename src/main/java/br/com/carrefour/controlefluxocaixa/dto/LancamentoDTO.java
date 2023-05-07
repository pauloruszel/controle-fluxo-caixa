package br.com.carrefour.controlefluxocaixa.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LancamentoDTO {
    private String tipo;
    private String descricao;
    private BigDecimal valor;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;
}
