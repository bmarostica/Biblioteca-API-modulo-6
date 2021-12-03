package com.dbc.relatorioconsumer.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RelatorioDTO {
    private LocalDate data;
    private String classe;
    private String conteudo;
}

