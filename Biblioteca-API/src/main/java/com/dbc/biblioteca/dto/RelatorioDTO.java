package com.dbc.biblioteca.dto;

import lombok.Data;
import org.bson.Document;

import java.time.LocalDate;
import java.util.List;

@Data
public class RelatorioDTO {
    private LocalDate data;
    private String classe;
    private List<Document> conteudo;
}

