package com.dbc.biblioteca.service;

import com.dbc.biblioteca.dto.*;
import com.dbc.biblioteca.kafka.Producer;
import com.dbc.biblioteca.repository.ContaClienteRepository;
import com.dbc.biblioteca.repository.EmprestimoRepository;
import com.dbc.biblioteca.repository.LivroRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioService {
    private final EmprestimoRepository emprestimoRepository;
    private final ContaClienteRepository contaClienteRepository;
    private final LivroRepository livroRepository;
    private final Producer producer;
    private final ObjectMapper objectMapper;

//    @Scheduled(cron = "0 0 20 * * MON-FRI")
    @Scheduled(fixedDelay = 1300000)
    public void gerarRelatorioEmprestimoKafka() throws JsonProcessingException {

        producer.sendRelatorioEmprestimoKafka(criarRelatorioEmprestimo());

    }
//    @Scheduled(cron = "0 10 20 * * MON-FRI")
    @Scheduled(fixedDelay = 1300000)
    public void gerarRelatorioLivroKafka() throws JsonProcessingException {
        producer.sendRelatorioLivroKafka(criarRelatorioLivro());
    }

//    @Scheduled(cron = "0 05 20 * * MON-FRI")
    @Scheduled(fixedDelay = 1300000)
    public void gerarRelatorioClienteKafka() throws JsonProcessingException {
        producer.sendRelatorioClienteKafka(criarRelatorioCliente());
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

    public RelatorioDTO criarRelatorioCliente() {
        List<Document> lista = contaClienteRepository.findByDate(LocalDate.now());
        RelatorioDTO relatorioDTO = new RelatorioDTO();
        relatorioDTO.setClasse("Relatório de Clientes.");
        relatorioDTO.setData(LocalDate.now());
        relatorioDTO.setConteudo(lista);
        return relatorioDTO;
    }

    public RelatorioDTO criarRelatorioLivro() {
        List<Document> lista = livroRepository.findByDate(LocalDate.now());
        RelatorioDTO relatorioDTO = new RelatorioDTO();
        relatorioDTO.setClasse("Relatório de Livros.");
        relatorioDTO.setData(LocalDate.now());
        relatorioDTO.setConteudo(lista);
        return relatorioDTO;
    }
}
