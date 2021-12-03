package com.dbc.relatorioconsumer.service;

import com.dbc.relatorioconsumer.repository.RelatorioRepository;
import com.dbc.relatorioconsumer.model.Relatorio;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioService {
    private final RelatorioRepository relatorioRepository;
    private final ObjectMapper objectMapper;

    public Document create(Relatorio relatorio) {
        Relatorio relatorioCriado = relatorioRepository.save(relatorio);
        Document relatorioDocument = objectMapper.convertValue(relatorioCriado, Document.class);
        return relatorioDocument;
    }

    public  List<Document> list() {
        return relatorioRepository.findAll().stream()
                .map(relatorio -> objectMapper.convertValue(relatorio, Document.class))
                .collect(Collectors.toList());
    }
}
