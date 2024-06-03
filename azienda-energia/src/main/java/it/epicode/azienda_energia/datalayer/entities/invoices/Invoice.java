package it.epicode.azienda_energia.datalayer.entities.invoices;

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
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_seq")
    @SequenceGenerator(name = "invoice_seq", sequenceName = "invoice_seq")
    private Long id;
    private LocalDate date;
    private double amount;
    private String serialNumber;

    @ManyToOne
    private Customer customer;

}
