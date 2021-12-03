package com.dbc.relatorioconsumer.service;

import com.dbc.relatorioconsumer.dto.RelatorioDTO;
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
        System.out.println(relatorioDTO);

        log.info("MENSAGEM LIDA: '{}', CHAVE: '{}', OFFSET: '{}'", relatorioDTO, key, offset);


    }

}
