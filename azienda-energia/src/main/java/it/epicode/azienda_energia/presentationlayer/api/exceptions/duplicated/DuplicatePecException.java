package it.epicode.azienda_energia.presentationlayer.api.exceptions.duplicated;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class DuplicatePecException extends DuplicateKeyException{

    @Serial
    private static final long serialVersionUID = 1L;


    public DuplicatePecException(String key) {
        super(key, HttpStatus.BAD_REQUEST, "Pec duplicata");
    }
}
