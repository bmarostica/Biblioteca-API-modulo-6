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


        //assert
        livroService.delete(5);
        verify(livroRepository, Mockito.times(1)).delete(livro);

    }

    @Test
    public void createLivro() throws RegraDeNegocioException {
        LivroEntity livroEntity = new LivroEntity();
        livroEntity.setData_registro(LocalDate.now());
        LivroDTO livroDTO = new LivroDTO();
        LivroCreateDTO livroCreateDTO = new LivroCreateDTO();



        when(objectMapper.convertValue(livroCreateDTO, LivroEntity.class)).thenReturn(livroEntity);
//        doReturn(livroEntity).when(livroRepository).save(livroEntity);
        when(livroRepository.save(livroEntity)).thenReturn(livroEntity);
        when(objectMapper.convertValue(livroEntity, LivroDTO.class)).thenReturn(livroDTO);
        livroService.create(livroCreateDTO);


        verify(livroRepository, Mockito.times(1)).save(livroEntity);
    }


}