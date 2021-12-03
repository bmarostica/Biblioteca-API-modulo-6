package com.dbc.relatorioconsumer.controller;

import com.dbc.relatorioconsumer.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/relatorio")
@Validated
@RequiredArgsConstructor
@Slf4j
public class RelatorioController {
    private final RelatorioService relatorioService;

    @ApiOperation(value = "Lista todos os relatorios")
    @GetMapping
    public List<Document> list() {
        log.info("Fazendo busca de Relatório");
        List<Document> list = relatorioService.list();
        log.info("Relatorio Listado com Sucesso!");
        return list;
    }

    @ApiOperation(value = "Lista todos os relatorios por data")
    @GetMapping("/filtra-por-data")
    public List<Document> listPorData(@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                      @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        log.info("Fazendo busca de Relatório");
        List<Document> list = relatorioService.listRelatorioPorData(from,to);
        log.info("Relatorio Listado com Sucesso!");
        return list;
    }

//    @GetMapping("/find-by-data-de-nascimento")
//    public List<PessoaEntity> findByDataNascimentoBetween(@RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
//                                                          @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim){
//        return pessoaRepository.findByDataNascimentoBetween(inicio, fim);
//    }



}
