package com.dbc.emailconsumer.service;

import com.dbc.emailconsumer.dto.EmailDTO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    private final Configuration configuration;

    @Value("${spring.mail.username}")
    private String remetente;

    public void enviarEmail(EmailDTO emailDTO) throws MessagingException{
        try{
            MimeMessage mimeMessage = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(remetente);
            helper.setTo(emailDTO.getDestinatario());
            helper.setSubject(emailDTO.getAssunto());

            Template template = configuration.getTemplate("templateBiblioteca.ftl");
            Map<String, Object> dados = new HashMap<>();

            dados.put("mensagem", emailDTO.getTexto());

            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

            helper.setText(html, true);

            emailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }







}
