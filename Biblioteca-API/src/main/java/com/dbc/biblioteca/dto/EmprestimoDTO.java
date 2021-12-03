package com.dbc.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoDTO {
    private Integer idEmprestimo;
    private LivroDTO livroDTO;
    private FuncionarioDTO funcionarioDTO;
    private ContaClienteDTO contaClienteDTO;
    private LocalDate dataEmprestimo;

}
