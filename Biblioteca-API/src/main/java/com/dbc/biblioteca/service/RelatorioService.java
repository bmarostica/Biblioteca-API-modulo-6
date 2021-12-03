package com.dbc.biblioteca.service;

import com.dbc.biblioteca.dto.EmprestimoDTO;
import com.dbc.biblioteca.dto.RelatorioDTO;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import com.dbc.biblioteca.kafka.Producer;
import com.dbc.biblioteca.repository.EmprestimoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioService {
    private final EmprestimoRepository emprestimoRepository;
    private final Producer producer;
    private final ObjectMapper objectMapper;
    private final ContaClienteService contaClienteService;
    private final FuncionarioService funcionarioService;
    private final LivroService livroService;


    @Scheduled(fixedDelay = 10000)
    public void gerarRelatorioEmprestimoKafka() throws JsonProcessingException {

        producer.sendRelatorioKafka(criarRelatorioEmprestimo());

    }


    public RelatorioDTO criarRelatorioEmprestimo() {
        List<EmprestimoDTO> lista = emprestimoRepository.findByDate(LocalDate.now()).stream()
                .map(emprestimo1 -> {
                    EmprestimoDTO dto = objectMapper.convertValue(emprestimo1, EmprestimoDTO.class);
                    try {
                        dto.setContaClienteDTO(contaClienteService.getById(emprestimo1.getContaClienteEntity().getIdCliente()));
                        dto.setFuncionarioDTO(funcionarioService.getById(emprestimo1.getFuncionarioEntity().getIdFuncionario()));
                        dto.setLivroDTO(livroService.getById(emprestimo1.getLivroEntity().getIdLivro()));
                    } catch (RegraDeNegocioException e) {
                        e.printStackTrace();
                    }
                    return dto;

                })
                .collect(Collectors.toList());

        RelatorioDTO relatorioDTO = new RelatorioDTO();
        relatorioDTO.setData(LocalDate.now());
        relatorioDTO.setClasse("Relatorio de Emprestimos.");
        relatorioDTO.setConteudo("Empréstimos do dia: " + lista);
        return relatorioDTO;
    }
}
