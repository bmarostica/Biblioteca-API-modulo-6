package com.dbc.biblioteca.service;

import com.dbc.biblioteca.dto.RelatorioDTO;
import com.dbc.biblioteca.entity.EmprestimoEntity;
import com.dbc.biblioteca.kafka.Producer;
import com.dbc.biblioteca.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RelatorioService {
    private final EmprestimoRepository emprestimoRepository;
    private final Producer producer;

    public RelatorioDTO criarRelatorioEmprestimo(EmprestimoEntity emprestimo) {
        RelatorioDTO relatorioDTO = new RelatorioDTO();
        relatorioDTO.setData(LocalDate.now());
        relatorioDTO.setClasse("Relatorio de Emprestimos.");
        return relatorioDTO;
    }
}
