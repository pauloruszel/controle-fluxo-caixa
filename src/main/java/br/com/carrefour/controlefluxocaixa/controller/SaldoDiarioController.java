package br.com.carrefour.controlefluxocaixa.controller;

import br.com.carrefour.controlefluxocaixa.dto.SaldoDiarioDTO;
import br.com.carrefour.controlefluxocaixa.service.SaldoDiarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/saldo-consolidado")
public class SaldoDiarioController {

    @Autowired
    private SaldoDiarioService saldoDiarioService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{data}")
    public SaldoDiarioDTO calcularSaldoDiario(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        var saldoDiarioDTO = saldoDiarioService.calcularSaldoDiario(data);
        return modelMapper.map(saldoDiarioDTO, SaldoDiarioDTO.class);
    }
}
