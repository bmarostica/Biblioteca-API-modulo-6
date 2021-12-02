package com.dbc.biblioteca.repository;

import com.dbc.biblioteca.entity.ContaClienteEntity;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContaClienteRepository extends JpaRepository<ContaClienteEntity, Integer> {

    @Query(value = "select * from cliente where pontos_fidelidade >= 1000", nativeQuery = true)
    public List<ContaClienteEntity> findPontosFidelidade();

}