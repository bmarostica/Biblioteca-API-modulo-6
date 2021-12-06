package com.dbc.relatorioconsumer.service;

import com.dbc.relatorioconsumer.model.RelatorioEmprestimo;
import com.dbc.relatorioconsumer.repository.RelatorioEmprestimoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioEmprestimoService {
    private final RelatorioEmprestimoRepository relatorioEmprestimoRepository;
    private final ObjectMapper objectMapper;

    public Document create(RelatorioEmprestimo relatorioEmprestimo) {
        RelatorioEmprestimo relatorioEmprestimoCriado = relatorioEmprestimoRepository.save(relatorioEmprestimo);
        Document relatorioDocument = objectMapper.convertValue(relatorioEmprestimoCriado, Document.class);
        return relatorioDocument;
    }

    public  List<Document> list() {
        return relatorioEmprestimoRepository.findAll().stream()
                .map(relatorioEmprestimo -> objectMapper.convertValue(relatorioEmprestimo, Document.class))
                .collect(Collectors.toList());
    }

    public  List<Document> listRelatorioPor2Data(LocalDate from, LocalDate to) {
        return relatorioEmprestimoRepository.listarRelatorioPorData(from,to);
    }

    public  List<Document> listRelatorioPorData(LocalDate data) {
        return relatorioEmprestimoRepository.listarRelatorioPorDataEspecifica(data);
    }
}
