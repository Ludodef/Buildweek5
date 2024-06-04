package it.epicode.azienda_energia.datalayer.entities.invoices;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.azienda_energia.datalayer.entities.BaseEntity;
import it.epicode.azienda_energia.datalayer.entities.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "invoices")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Invoice extends BaseEntity {
    private LocalDate date;
    private double amount;

    @Column(unique = true)

    private String serialNumber;
    @ManyToOne
    private InvoiceStatus status;
    @ManyToOne
    private Customer customer;

}
