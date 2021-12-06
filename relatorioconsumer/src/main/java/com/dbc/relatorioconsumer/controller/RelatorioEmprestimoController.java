package com.dbc.relatorioconsumer.controller;

import com.dbc.relatorioconsumer.service.RelatorioEmprestimoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/relatorioEmprestimo")
@RequiredArgsConstructor
@Slf4j
public class RelatorioEmprestimoController {
    private final RelatorioEmprestimoService relatorioEmprestimoService;

    @ApiOperation(value = "Lista todos os relatorios de emprestimos.")
    @GetMapping
    public List<Document> list() {
        log.info("Fazendo busca de Relatório");
        List<Document> list = relatorioEmprestimoService.list();
        log.info("Relatorio Listado com Sucesso!");
        return list;
    }

    @ApiOperation(value = "Lista todos os relatorios de emprestimo entre duas datas.")
    @GetMapping("/filtra-por-data")
    public List<Document> listPorData(@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                      @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        log.info("Fazendo busca de Relatório");
        List<Document> list = relatorioEmprestimoService.listRelatorioPor2Data(from,to);
        log.info("Relatorio Listado com Sucesso!");
        return list;
    }

    @ApiOperation(value = "Lista o relatório da data específica")
    @GetMapping("/filtra-por-uma-data")
    public List<Document> listPorDataEspecifica(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        log.info("Fazendo busca de Relatório");
        List<Document> list = relatorioEmprestimoService.listRelatorioPorData(data);
        log.info("Relatorio Listado com Sucesso!");
        return list;
    }
}
