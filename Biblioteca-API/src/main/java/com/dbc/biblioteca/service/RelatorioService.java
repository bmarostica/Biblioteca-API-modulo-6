package com.dbc.biblioteca.service;

import com.dbc.biblioteca.dto.EmprestimoDTO;
import com.dbc.biblioteca.dto.RelatorioDTO;
import com.dbc.biblioteca.entity.EmprestimoEntity;
import com.dbc.biblioteca.kafka.Producer;
import com.dbc.biblioteca.repository.EmprestimoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioService {
    private final EmprestimoRepository emprestimoRepository;
    private final Producer producer;
    private final ObjectMapper objectMapper;

    public RelatorioDTO criarRelatorioEmprestimo() {
        List<EmprestimoDTO> lista = emprestimoRepository.findByDate(LocalDate.now()).stream()
                .map(emprestimo1 -> objectMapper.convertValue(emprestimo1, EmprestimoDTO.class))
                .collect(Collectors.toList());
        RelatorioDTO relatorioDTO = new RelatorioDTO();
        relatorioDTO.setData(LocalDate.now());
        relatorioDTO.setClasse("Relatorio de Emprestimos.");
        relatorioDTO.setConteudo("Empréstimos do dia: " + lista);
        return relatorioDTO;
    }

    @Scheduled(cron = "* * * * * *")
    public void gerarRelatorioEmprestimoKafka() throws JsonProcessingException {

        producer.sendRelatorioKafka(criarRelatorioEmprestimo());

    }
}
