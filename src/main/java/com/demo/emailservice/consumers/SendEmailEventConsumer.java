package com.demo.emailservice.consumers;

import com.demo.emailservice.dto.SendEmailDto;
import com.demo.emailservice.utilities.EmailUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class SendEmailEventConsumer {

    private ObjectMapper objectMapper;
    public SendEmailEventConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // listen to the send-email topic
    @KafkaListener(topics = "send-email", groupId = "emailService")
    public void handleSendEmailEvent(String kafkaMessage) throws JsonProcessingException {
        // convert the message to SendEmailDto. From Jason to Java Object (SendMail DTO)
        SendEmailDto event = objectMapper.readValue(kafkaMessage, SendEmailDto.class);

        // send the email.
        String to = event.getTo();
        String subject = event.getSubject();
        String body = event.getBody();
//        String from = event.getFrom();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("devendra.sahu.tech@gmail.com", "kbkyezsxbokmzaau");
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtil.sendEmail(session, to,subject,body);

    }
}
