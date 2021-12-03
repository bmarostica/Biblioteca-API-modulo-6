package com.dbc.relatorioconsumer.controller;

import com.dbc.relatorioconsumer.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping("/relatorio")
@Validated
@RequiredArgsConstructor
@Slf4j
public class RelatorioController {
    private final RelatorioService relatorioService;

    @ApiOperation(value = "Lista todos os livros disponíveis para troca")
    @GetMapping
    public List<Document> list() {
        return relatorioService.list();
    }

}
