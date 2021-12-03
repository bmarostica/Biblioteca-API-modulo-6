package com.dbc.biblioteca.service;

import com.dbc.biblioteca.dto.*;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import com.dbc.biblioteca.kafka.Producer;
import com.dbc.biblioteca.repository.EmprestimoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
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
    private final ContaClienteService contaClienteService;
    private final FuncionarioService funcionarioService;
    private final LivroService livroService;


    @Scheduled(fixedDelay = 300000)
    public void gerarRelatorioEmprestimoKafka() throws JsonProcessingException {

        producer.sendRelatorioKafka(criarRelatorioEmprestimo());

    }


    public RelatorioDTO criarRelatorioEmprestimo() {
        List<Document> lista = emprestimoRepository.findByDate(LocalDate.now()).stream()
                .map(emprestimo1 -> {
                    Document doc = objectMapper.convertValue(emprestimo1, Document.class);

                    doc.append("Cliente", objectMapper.convertValue(emprestimo1.getContaClienteEntity(), ContaClienteDTO.class));
                    doc.append("Funcionario", objectMapper.convertValue(emprestimo1.getFuncionarioEntity(), FuncionarioDTO.class));
                    doc.append("Livro", objectMapper.convertValue(emprestimo1.getLivroEntity(), LivroDTO.class));

                    return doc;
                })
                .collect(Collectors.toList());

        RelatorioDTO relatorioDTO = new RelatorioDTO();
        relatorioDTO.setData(LocalDate.now());
        relatorioDTO.setClasse("Relatorio de Emprestimos.");
        relatorioDTO.setConteudo(lista);
        return relatorioDTO;
    }
}
