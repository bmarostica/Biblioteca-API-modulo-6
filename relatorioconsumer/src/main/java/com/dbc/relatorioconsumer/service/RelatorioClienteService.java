package com.dbc.relatorioconsumer.service;

import com.dbc.relatorioconsumer.model.RelatorioCliente;
import com.dbc.relatorioconsumer.repository.RelatorioClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioClienteService {
    private final RelatorioClienteRepository relatorioClienteRepository;
    private final ObjectMapper objectMapper;

    public void create(RelatorioCliente relatorioCliente) {
        relatorioClienteRepository.save(relatorioCliente);
    }

    public  List<Document> list() {
        return relatorioClienteRepository.findAll().stream()
                .map(relatorioCliente -> objectMapper.convertValue(relatorioCliente, Document.class))
                .collect(Collectors.toList());
    }

    public  List<Document> listRelatorioPor2Data(LocalDate from, LocalDate to) {
        return relatorioClienteRepository.listarRelatorioPorData(from,to);
    }

    public  List<Document> listRelatorioPorData(LocalDate data) {
        return relatorioClienteRepository.listarRelatorioPorDataEspecifica(data);
    }
}