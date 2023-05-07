package br.com.carrefour.controlefluxocaixa.repository;

import br.com.carrefour.controlefluxocaixa.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
    List<Lancamento> findByData(LocalDate data);
}
