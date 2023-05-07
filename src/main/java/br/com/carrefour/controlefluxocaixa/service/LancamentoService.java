package br.com.carrefour.controlefluxocaixa.service;

import br.com.carrefour.controlefluxocaixa.dto.LancamentoDTO;
import br.com.carrefour.controlefluxocaixa.model.Lancamento;
import br.com.carrefour.controlefluxocaixa.repository.LancamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class LancamentoService {


    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public LancamentoDTO adicionarLancamento(LancamentoDTO lancamentoDTO) {
        var lancamento = modelMapper.map(lancamentoDTO, Lancamento.class);
        var novoLancamento = lancamentoRepository.save(lancamento);
        return modelMapper.map(novoLancamento, LancamentoDTO.class);
    }

    public LancamentoDTO buscarLancamento(Long id) {
        var lancamento = lancamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        return modelMapper.map(lancamento, LancamentoDTO.class);
    }

    public List<LancamentoDTO> listarLancamentos() {
        var lancamentos = lancamentoRepository.findAll();
        return lancamentos.stream()
                .map(lancamento -> modelMapper.map(lancamento, LancamentoDTO.class))
                .collect(Collectors.toList());
    }

    public List<LancamentoDTO> listarLancamentos(LocalDate data) {
        var lancamentos = lancamentoRepository.findByData(data);
        return lancamentos.stream()
                .map(lancamento -> modelMapper.map(lancamento, LancamentoDTO.class))
                .collect(Collectors.toList());
    }
}
