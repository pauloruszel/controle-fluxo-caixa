package br.com.carrefour.controlefluxocaixa.controller;

import br.com.carrefour.controlefluxocaixa.dto.LancamentoDTO;
import br.com.carrefour.controlefluxocaixa.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @PostMapping
    public ResponseEntity<LancamentoDTO> adicionarLancamento(@RequestBody LancamentoDTO lancamentoDTO) {
        var novoLancamento = lancamentoService.adicionarLancamento(lancamentoDTO);
        return ResponseEntity.status(CREATED).body(novoLancamento);
    }

    @GetMapping("/{id}")
    public LancamentoDTO buscarLancamento(@PathVariable Long id) {
        return lancamentoService.buscarLancamento(id);
    }

    @GetMapping
    public List<LancamentoDTO> listarLancamentos() {
        return lancamentoService.listarLancamentos();
    }

    @GetMapping(params = "data")
    public List<LancamentoDTO> listarLancamentosPorData(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return lancamentoService.listarLancamentos(data);
    }
}