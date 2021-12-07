package com.dbc.biblioteca.service;

import com.dbc.biblioteca.entity.LivroEntity;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import com.dbc.biblioteca.repository.LivroRepository;
import com.dbc.biblioteca.service.LivroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


public class LivroServiceTest {

    @InjectMocks
    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;
    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deleteLivro() throws RegraDeNegocioException {
        LivroEntity livro = new LivroEntity();

        doReturn(Optional.of(livro)).when(livroRepository).findById(5);
        //when(livroRepository.findById(anyInt())).thenReturn(Optional.of(livro));

        livroService.delete(5);
        verify(livroRepository, Mockito.times(1)).delete(livro);

    }


}