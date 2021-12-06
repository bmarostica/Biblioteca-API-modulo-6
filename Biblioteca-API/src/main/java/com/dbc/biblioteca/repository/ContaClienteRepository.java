package com.dbc.biblioteca.repository;

import com.dbc.biblioteca.entity.ContaClienteEntity;
import org.bson.Document;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContaClienteRepository extends JpaRepository<ContaClienteEntity, Integer> {

    @Query(value = "select * from cliente where pontos_fidelidade >= 1000", nativeQuery = true)
    public List<ContaClienteEntity> findPontosFidelidade();

    @Query(value = "select * " +
            "from cliente " +
            "where data_registro = :data"
            , nativeQuery = true)
    List<Document> findByDate(LocalDate data);

    @Query("select c from cliente c where tipoCliente = '0'")
    List<ContaClienteEntity> findByTipoCliente();

}