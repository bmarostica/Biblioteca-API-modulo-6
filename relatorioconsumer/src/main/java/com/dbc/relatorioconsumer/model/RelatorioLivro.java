package com.dbc.relatorioconsumer.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Document(collection = "relatorioLivro")
public class RelatorioLivro {

    @Id
    private String id;
    private LocalDate data;
    private String classe;
    private List<org.bson.Document> conteudo;
}
