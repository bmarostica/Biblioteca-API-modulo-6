package com.dbc.relatorioconsumer.service;

import com.dbc.relatorioconsumer.dto.RelatorioDTO;
import com.dbc.relatorioconsumer.model.Relatorio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {
    private final RelatorioService relatorioService;
    private final ObjectMapper objectMapper;


    @KafkaListener(
            topics = "${kafka.topic.relatorio}",
            groupId = "${kafka.group-id}",
            containerFactory = "listenerContainerFactoryEarliest"
    )
    public void consumeDto(@Payload String mensagem,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        @Header(KafkaHeaders.OFFSET) Long offset) throws JsonProcessingException {
        RelatorioDTO relatorioDTO = objectMapper.readValue(mensagem, RelatorioDTO.class);

        Relatorio relatorio = new Relatorio();
        relatorio.setClasse(relatorioDTO.getClasse());
        relatorio.setData(relatorioDTO.getData());
        relatorio.setConteudo(relatorioDTO.getConteudo());
        relatorioService.create(relatorio);

        log.info("MENSAGEM LIDA: '{}', CHAVE: '{}', OFFSET: '{}'", relatorioDTO, key, offset);


    }

}