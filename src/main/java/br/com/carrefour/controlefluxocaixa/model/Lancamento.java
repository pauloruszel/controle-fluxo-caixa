package br.com.carrefour.controlefluxocaixa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "lancamento")
@Table(name = "lancamento")
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O tipo do lançamento é obrigatório")
    @Column(name = "tipo")
    private String tipo;

    @NotNull(message = "O valor do lançamento é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor do lançamento deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "O valor do lançamento deve ter até 10 dígitos inteiros e 2 dígitos decimais")
    @Column(name = "valor")
    private BigDecimal valor;

    @NotBlank(message = "A descrição do lançamento é obrigatória")
    @Column(name = "descricao")
    private String descricao;

    @NotNull(message = "A data do lançamento é obrigatória")
    @Column(name = "data")
    private LocalDate data;
}
