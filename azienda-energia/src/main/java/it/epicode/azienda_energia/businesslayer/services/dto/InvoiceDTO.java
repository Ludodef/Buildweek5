package it.epicode.azienda_energia.businesslayer.services.dto;

import it.epicode.azienda_energia.datalayer.entities.Customer;
import it.epicode.azienda_energia.datalayer.entities.invoices.InvoiceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class InvoiceDTO extends BaseDTO {

    private LocalDate date;
    private double amount;
    @Column(unique = true)
    private String serialNumber;

    @ManyToOne
    private InvoiceStatus status;
    @ManyToOne
    private Customer customer;
}
