package com.dbc.biblioteca.kafka;

import com.dbc.biblioteca.dto.EmailDTO;
import com.dbc.biblioteca.dto.RelatorioDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class Producer {
    private final KafkaTemplate<String, String> stringKafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value(value = "${kafka.topic.email}")
    private String topicoEmail;

    @Value(value = "${kafka.topic.relatorio}")
    private String topicoRelatorio;

    private void send(String mensagem, String topico, Integer partition) {
        Message<String> message = MessageBuilder.withPayload(mensagem)
                .setHeader(KafkaHeaders.TOPIC, topico)
                .setHeader(KafkaHeaders.MESSAGE_KEY, UUID.randomUUID().toString())
                .setHeader(KafkaHeaders.PARTITION_ID, partition)
                .build();

        ListenableFuture<SendResult<String, String>> send = stringKafkaTemplate.send(message);

        send.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error(" Erro ao enviar mensagem ao kafka, texto: {}", mensagem);
            }
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info(" Mensagem enviada com sucesso para o kafka com o texto: {}", mensagem);
            }
        });
    }


    public void sendEmailKafka(EmailDTO emailDTO) throws JsonProcessingException {
        String payload = objectMapper.writeValueAsString(emailDTO);
        send(payload, topicoEmail, 0);
    }

    public void sendEmailPromocionalKafka(EmailDTO emailDTO) throws JsonProcessingException {
        String payload = objectMapper.writeValueAsString(emailDTO);
        send(payload, topicoEmail, 1);
    }


    public void sendRelatorioEmprestimoKafka(RelatorioDTO relatorioDTO) throws JsonProcessingException {
        String payload = objectMapper.writeValueAsString(relatorioDTO);
        send(payload, topicoRelatorio, 0);
    }

    public void sendRelatorioClienteKafka(RelatorioDTO relatorioDTO) throws JsonProcessingException {
        String payload = objectMapper.writeValueAsString(relatorioDTO);
        send(payload, topicoRelatorio, 1);
    }

    public void sendRelatorioLivroKafka(RelatorioDTO relatorioDTO) throws JsonProcessingException {
        String payload = objectMapper.writeValueAsString(relatorioDTO);
        send(payload, topicoRelatorio, 2);
    }
}