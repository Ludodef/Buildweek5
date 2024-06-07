package it.epicode.azienda_energia.businesslayer.services.interfaces;

public interface MailService {

    void sendMail(String emailTo, String Subject, String Body);
}
