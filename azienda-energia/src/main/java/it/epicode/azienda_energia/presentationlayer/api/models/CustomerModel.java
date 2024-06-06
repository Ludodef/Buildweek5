package it.epicode.azienda_energia.presentationlayer.api.models;

import it.epicode.azienda_energia.presentationlayer.utility.EqualsTo;
import it.epicode.azienda_energia.presentationlayer.utility.VatNumberValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CustomerModel (
        @NotBlank(message = "Il businessName non può essere omesso")
        String businessName,
        @VatNumberValid
        String vatNumber,
        @NotBlank(message = "La businessEmail non può essere omessa")
        String businessEmail,
        @Positive(message = "L'annual sales deve essere un numero positivo")
        double annualSales,
        @Email
        String pec,
        @NotNull(message = "Il phoneNumber non può essere omesso")
        Long phoneNumber,
        @Email
        String contactEmail,
        @NotBlank(message = "Il contactFirstName non può essere omessa")
        String contactFirstName,
        @NotBlank(message = "Il contactLastName non può essere omessa")
        String contactLastName,
        @NotNull(message = "Il contactPhoneNumber non può essere omesso")
        Long contactPhoneNumber,
        String companyLogo,
        @NotNull(message = "L' headquarters non può essere omesso")
        AddressModel headquarters,
        @EqualsTo(values = {"PA","SAS", "SPA", "SRL"})
        String type
) {
}
