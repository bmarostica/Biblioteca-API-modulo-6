package com.dbc.biblioteca.dto;

import com.dbc.biblioteca.entity.Formato;
import com.dbc.biblioteca.entity.Idioma;
import com.dbc.biblioteca.entity.StatusLivro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO extends LivroCreateDTO {
    private Integer idLivro;

    private String titulo;
    private String autor;
    private String editora;
    private Integer numeroDePaginas;
    private Formato formato;
    private Idioma idioma;
    private StatusLivro statusLivro;
}
