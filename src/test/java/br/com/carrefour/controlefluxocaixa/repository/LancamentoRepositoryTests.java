package br.com.carrefour.controlefluxocaixa.repository;

import br.com.carrefour.controlefluxocaixa.model.Lancamento;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LancamentoRepositoryTests {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSalvarLancamento() {
        Lancamento lancamento = new Lancamento();
        lancamento.setTipo("Entrada");
        lancamento.setValor(BigDecimal.valueOf(100.00));
        lancamento.setDescricao("Salário");
        lancamento.setData(LocalDate.now());

        lancamentoRepository.save(lancamento);

        assertNotNull(lancamento.getId());
    }

    @Test
    public void testBuscarLancamentosPorData() {
        LocalDate data = LocalDate.now();

        Lancamento lancamento1 = new Lancamento();
        lancamento1.setTipo("Entrada");
        lancamento1.setValor(BigDecimal.valueOf(100.00));
        lancamento1.setDescricao("Salário");
        lancamento1.setData(data);

        Lancamento lancamento2 = new Lancamento();
        lancamento2.setTipo("Saída");
        lancamento2.setValor(BigDecimal.valueOf(50.00));
        lancamento2.setDescricao("Conta de luz");
        lancamento2.setData(data);

        lancamentoRepository.save(lancamento1);
        lancamentoRepository.save(lancamento2);

        List<Lancamento> lancamentos = lancamentoRepository.findByData(data);

        assertEquals(2, lancamentos.size());
    }

    @Test
    public void testSalvarLancamentoSemTipo() {
        Lancamento lancamento = new Lancamento();
        lancamento.setValor(BigDecimal.valueOf(100.00));
        lancamento.setDescricao("Salário");
        lancamento.setData(LocalDate.now());

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            lancamentoRepository.save(lancamento);
        });

        assertEquals("O tipo do lançamento é obrigatório", exception.getConstraintViolations().iterator().next().getMessage());
    }


    @Test
    public void testSalvarLancamentoSemValor() {
        Lancamento lancamento = new Lancamento();
        lancamento.setTipo("Entrada");
        lancamento.setDescricao("Salário");
        lancamento.setData(LocalDate.now());

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            lancamentoRepository.save(lancamento);
        });

        assertEquals("O valor do lançamento é obrigatório", exception.getConstraintViolations().iterator().next().getMessage());
    }

    @Test
    public void testSalvarLancamentoSemDescricao() {
        Lancamento lancamento = new Lancamento();
        lancamento.setTipo("Entrada");
        lancamento.setValor(BigDecimal.valueOf(100.00));
        lancamento.setData(LocalDate.now());

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            lancamentoRepository.save(lancamento);
        });

        assertEquals("A descrição do lançamento é obrigatória", exception.getConstraintViolations().iterator().next().getMessage());
    }

    @Test
    void testSalvarLancamentoSemData() {
        Lancamento lancamento = new Lancamento();
        lancamento.setTipo("SAÍDA");
        lancamento.setValor(new BigDecimal("10.00"));
        lancamento.setDescricao("Teste");
        lancamento.setData(null);

        assertThrows(ConstraintViolationException.class, () -> lancamentoRepository.save(lancamento));
    }

    @Test
    void testFindByData() {
        Lancamento lancamento1 = new Lancamento();
        lancamento1.setTipo("ENTRADA");
        lancamento1.setValor(new BigDecimal("10.00"));
        lancamento1.setDescricao("Teste 1");
        lancamento1.setData(LocalDate.of(2022, 5, 7));
        entityManager.persist(lancamento1);

        Lancamento lancamento2 = new Lancamento();
        lancamento2.setTipo("SAÍDA");
        lancamento2.setValor(new BigDecimal("20.00"));
        lancamento2.setDescricao("Teste 2");
        lancamento2.setData(LocalDate.of(2022, 5, 7));
        entityManager.persist(lancamento2);

        Lancamento lancamento3 = new Lancamento();
        lancamento3.setTipo("ENTRADA");
        lancamento3.setValor(new BigDecimal("30.00"));
        lancamento3.setDescricao("Teste 3");
        lancamento3.setData(LocalDate.of(2022, 5, 8));
        entityManager.persist(lancamento3);

        entityManager.flush();

        LocalDate data = LocalDate.of(2022, 5, 7);
        assertEquals(2, lancamentoRepository.findByData(data).size());
    }
}