package com.dbc.relatorioconsumer.service;

import com.dbc.relatorioconsumer.dto.RelatorioDTO;
import com.dbc.relatorioconsumer.model.RelatorioCliente;
import com.dbc.relatorioconsumer.model.RelatorioEmprestimo;
import com.dbc.relatorioconsumer.model.RelatorioLivro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {
    private final RelatorioEmprestimoService relatorioEmprestimoService;
    private final RelatorioClienteService relatorioClienteService;
    private final RelatorioLivroService relatorioLivroService;
    private final ObjectMapper objectMapper;


    @KafkaListener(
            topics = "${kafka.topic.relatorio}",
            groupId = "${kafka.group-id}",
            topicPartitions = {@TopicPartition(topic = "${kafka.topic.relatorio}", partitions = {"0", "1", "2"})},
            containerFactory = "listenerContainerFactoryEarliest"
    )
    public void consumeRelatorio(@Payload String mensagem,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                        @Header(KafkaHeaders.OFFSET) Long offset)
            throws JsonProcessingException {

        RelatorioDTO relatorioDTO = objectMapper.readValue(mensagem, RelatorioDTO.class);

        if(partition == 0) {
            RelatorioEmprestimo relatorioEmprestimo = new RelatorioEmprestimo();
            relatorioEmprestimo.setClasse(relatorioDTO.getClasse());
            relatorioEmprestimo.setData(relatorioDTO.getData());
            relatorioEmprestimo.setConteudo(relatorioDTO.getConteudo());
            relatorioEmprestimoService.create(relatorioEmprestimo);
        }else if(partition == 1) {
            RelatorioCliente relatorioCliente = new RelatorioCliente();
            relatorioCliente.setClasse(relatorioDTO.getClasse());
            relatorioCliente.setData(relatorioDTO.getData());
            relatorioCliente.setConteudo(relatorioDTO.getConteudo());
            relatorioClienteService.create(relatorioCliente);
        }else if(partition == 2) {
            RelatorioLivro relatorioLivro = new RelatorioLivro();
            relatorioLivro.setClasse(relatorioDTO.getClasse());
            relatorioLivro.setData(relatorioDTO.getData());
            relatorioLivro.setConteudo(relatorioDTO.getConteudo());
            relatorioLivroService.create(relatorioLivro);
        }


        log.info("MENSAGEM LIDA: '{}', CHAVE: '{}', OFFSET: '{}', PARTITION: '{}'", relatorioDTO, key, offset,partition);
    }

}
