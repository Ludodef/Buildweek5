package it.epicode.azienda_energia.presentationlayer.api.exceptions.duplicated;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class DuplicatePhoneNumberException extends DuplicateKeyException{

    @Serial
    private static final long serialVersionUID = 1L;


    public DuplicatePhoneNumberException(String key) {
        super(key, HttpStatus.BAD_REQUEST, "PhoneNumber duplicato");
    }
}
