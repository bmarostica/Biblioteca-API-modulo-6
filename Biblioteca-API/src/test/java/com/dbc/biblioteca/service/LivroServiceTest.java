package com.dbc.biblioteca.service;

import com.dbc.biblioteca.dto.LivroCreateDTO;
import com.dbc.biblioteca.dto.LivroDTO;
import com.dbc.biblioteca.entity.LivroEntity;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import com.dbc.biblioteca.repository.LivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Optional;

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

        //assert
    }

    @Test
    public void createLivro() throws RegraDeNegocioException {
        LivroEntity livro = new LivroEntity();
        LivroDTO livroDTO = new LivroDTO();
        LivroCreateDTO livroCreateDTO = new LivroCreateDTO();


        livroService.create(livroCreateDTO);
        when(objectMapper.convertValue(livroCreateDTO, LivroEntity.class)).thenReturn(livro);
        when(livro.setData_registro()).then(livro.setData_registro(LocalDate.now()));

        doReturn(Optional.of(livro)).when(livroRepository).save(livro);
        //when(livroRepository.save(livro).thenReturn(Optional.of(livro));

        when(objectMapper.convertValue(livro, LivroDTO.class)).thenReturn(livroDTO);


        verify(livroRepository, Mockito.times(1)).save(livro);
    }


}