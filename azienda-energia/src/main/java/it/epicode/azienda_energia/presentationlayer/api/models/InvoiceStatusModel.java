package it.epicode.azienda_energia.presentationlayer.api.models;

import jakarta.validation.constraints.NotBlank;

public record InvoiceStatusModel(
        @NotBlank(message = "Il tipo di stato non può essere omesso")
        String type
) {


}
