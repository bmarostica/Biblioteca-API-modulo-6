package com.dbc.biblioteca.dto;

import com.dbc.biblioteca.entity.Formato;
import com.dbc.biblioteca.entity.Idioma;
import com.dbc.biblioteca.entity.StatusLivro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO extends LivroCreateDTO {
    private Integer idLivro;
    private LocalDate data_registro;


}
