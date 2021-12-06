package com.dbc.relatorioconsumer.service;


import com.dbc.relatorioconsumer.model.RelatorioLivro;
import com.dbc.relatorioconsumer.repository.RelatorioLivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioLivroService {
    private final RelatorioLivroRepository relatorioLivroRepository;
    private final ObjectMapper objectMapper;

    public void create(RelatorioLivro relatorioLivro) {
        relatorioLivroRepository.save(relatorioLivro);
    }

    public  List<Document> list() {
        return relatorioLivroRepository.findAll().stream()
                .map(relatorioLivro -> objectMapper.convertValue(relatorioLivro, Document.class))
                .collect(Collectors.toList());
    }

    public  List<Document> listRelatorioPor2Data(LocalDate from, LocalDate to) {
        return relatorioLivroRepository.listarRelatorioPorData(from,to);
    }

    public  List<Document> listRelatorioPorData(LocalDate data) {
        return relatorioLivroRepository.listarRelatorioPorDataEspecifica(data);
    }
}