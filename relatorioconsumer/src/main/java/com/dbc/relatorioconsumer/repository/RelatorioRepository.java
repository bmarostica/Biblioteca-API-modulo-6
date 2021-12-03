package com.dbc.relatorioconsumer.repository;


import com.dbc.relatorioconsumer.model.Relatorio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatorioRepository extends MongoRepository<Relatorio, String> {
}
