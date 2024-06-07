package it.epicode.azienda_energia.businesslayer.services.impl;

import it.epicode.azienda_energia.businesslayer.services.interfaces.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(String emailTo, String Subject, String Body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailTo);
        message.setSubject(Subject);
        message.setText(Body);

        javaMailSender.send(message);
    }
}
