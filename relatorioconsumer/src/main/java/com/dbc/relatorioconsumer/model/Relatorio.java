package com.dbc.relatorioconsumer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "relatorio")
public class Relatorio {
    @Id
    private String id;
    private LocalDate data;
    private String classe;
    private String conteudo;
}
