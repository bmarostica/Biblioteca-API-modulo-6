package com.dbc.biblioteca.service;

import com.dbc.biblioteca.dto.EmailDTO;
import com.dbc.biblioteca.entity.ContaClienteEntity;
import com.dbc.biblioteca.entity.TipoCliente;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    public EmailDTO enviarEmailKafkaPontosFidelidade(ContaClienteEntity cliente) {
        EmailDTO emailDTO = new EmailDTO();
        String mensagem = "";
        if (cliente.getTipoCliente() == TipoCliente.COMUM) {
            mensagem = "Parabéns, "+cliente.getNome()+"!<br><br>"+
                    "Você já possui pontos fidelidade suficiente para trocar por duas semanas Premium.<br />" +
                    "Pontos Fidelidade adquiridos: " + cliente.getPontosFidelidade();
        } else if (cliente.getTipoCliente() == TipoCliente.PREMIUM) {
            mensagem = "Parabéns, " +cliente.getNome()+ "!<br><br>" +
                    "Você já possui pontos fidelidade suficiente para trocar por um mês de assinatura grátis.<br />" +
                    "Pontos Fidelidade adquiridos: " + cliente.getPontosFidelidade();
        }
        emailDTO.setDestinatario(cliente.getEmail());
        emailDTO.setAssunto("Parabéns! 1000 pontos!");
        emailDTO.setTexto(mensagem);
        return emailDTO;
    }

    public EmailDTO enviarEmailKafkaPromocional(ContaClienteEntity cliente) {
        EmailDTO emailDTO = new EmailDTO();
        String mensagem = "Não perca essa oportunidade "+cliente.getNome()+"!<br><br>"+
                "Somente este mês você pode assinar o plano PREMIUM anual <br>" +
                "com 50% de desconto! Não fique de fora dessa! <br>";

        emailDTO.setDestinatario(cliente.getEmail());
        emailDTO.setAssunto("Black November na Biblioteca DBC!");
        emailDTO.setTexto(mensagem);
        return emailDTO;
    }
}
