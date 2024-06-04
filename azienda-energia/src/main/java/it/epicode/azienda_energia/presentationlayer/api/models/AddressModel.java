package it.epicode.azienda_energia.presentationlayer.api.models;

import it.epicode.azienda_energia.presentationlayer.utility.CAPValid;
import jakarta.validation.constraints.NotBlank;

public record AddressModel (
        @NotBlank(message = "La via non può essere omessa")
        String street,
        @NotBlank(message = "Il civico non può essere omesso")
        String number,
        @NotBlank(message = "La località non può essere omessa")
        String location,
        @CAPValid()
        String zip,
        @NotBlank(message = "Il comune non può essere vuoto")
                //IMPLEMNTARE VALIDAZIONE

        String municipality
) {
}
