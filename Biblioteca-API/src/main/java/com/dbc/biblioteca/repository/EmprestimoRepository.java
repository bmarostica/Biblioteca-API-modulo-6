package com.dbc.biblioteca.repository;

import com.dbc.biblioteca.entity.ContaClienteEntity;
import com.dbc.biblioteca.entity.EmprestimoEntity;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import org.bson.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public interface EmprestimoRepository extends JpaRepository<EmprestimoEntity,Integer> {
    List<EmprestimoEntity> findByStatusTrue();
    List<EmprestimoEntity> findByStatusFalse();

    @Query(value = "select e from emprestimo e " +
            "join fetch e.contaClienteEntity " +
            "join fetch e.funcionarioEntity " +
            "join fetch e.livroEntity " +
            "where " +
            "e.dataEmprestimo = :data")
    List<EmprestimoEntity> findByDate(LocalDate data);
}
