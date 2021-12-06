package com.dbc.biblioteca;

import com.dbc.biblioteca.entity.ContaClienteEntity;
import com.dbc.biblioteca.entity.LivroEntity;
import com.dbc.biblioteca.entity.StatusCliente;
import com.dbc.biblioteca.entity.StatusLivro;
import com.dbc.biblioteca.service.ContaClienteService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContaClienteServiceTeste {

    @Test
    public void testarSeClienteTemMilOuMaisPontosFidelidade() {
        ContaClienteEntity contaCliente = new ContaClienteEntity();
        contaCliente.setPontosFidelidade(1000);

        Integer pontos = contaCliente.getPontosFidelidade();

        Assertions.assertEquals(1000, pontos, 0);
    }

    @Test
    public void testarSeStatusDoLivroEstaDisponivel() {
        LivroEntity livro = new LivroEntity();
        livro.setStatusLivro(StatusLivro.DISPONIVEL);

        StatusLivro statusLivro = livro.getStatusLivro();

        Assertions.assertEquals(StatusLivro.DISPONIVEL, statusLivro);
    }

    @Test
    public void testarSeStatusDoClienteEstaAtivo() {
        ContaClienteEntity contaCliente = new ContaClienteEntity();
        contaCliente.setStatus(StatusCliente.ATIVO);

        StatusCliente statusCliente = contaCliente.getStatus();

        Assertions.assertNotEquals(StatusCliente.BLOQUEADO, statusCliente);
    }
}
