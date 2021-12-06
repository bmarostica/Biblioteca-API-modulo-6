package com.dbc.emailconsumer.service;

import com.dbc.emailconsumer.dto.EmailDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {
    private final KafkaTemplate<String, String> stringKafkaTemplate;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;


    @KafkaListener(
            topics = "${kafka.topic.email}",
            groupId = "${kafka.group-id}",
            topicPartitions = {@TopicPartition(topic="${kafka.topic.email}", partitions = {"0", "1"})},
            containerFactory = "listenerContainerFactoryEarliest"
    )
    public void consumeEmailPontosFidelidade(@Payload String mensagem,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                        @Header(KafkaHeaders.OFFSET) Long offset) throws IOException, TemplateException, MessagingException {
        EmailDTO emailDTO = objectMapper.readValue(mensagem, EmailDTO.class);
        emailService.enviarEmail(emailDTO);
        log.info("MENSAGEM LIDA: '{}',PARTITION: '{}' CHAVE: '{}', OFFSET: '{}'", emailDTO, partition, key, offset);
    }
}
