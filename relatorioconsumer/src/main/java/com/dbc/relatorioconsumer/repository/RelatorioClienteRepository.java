package com.dbc.relatorioconsumer.repository;


import com.dbc.relatorioconsumer.model.RelatorioCliente;
import org.bson.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RelatorioClienteRepository extends MongoRepository<RelatorioCliente, String> {

    @Query("{'data' : { $gte: ?0, $lte: ?1 } }")
    public List<Document> listarRelatorioPorData(LocalDate from, LocalDate to);

    @Query("{'data': ?0}")
    public List<Document> listarRelatorioPorDataEspecifica(LocalDate data);

}
