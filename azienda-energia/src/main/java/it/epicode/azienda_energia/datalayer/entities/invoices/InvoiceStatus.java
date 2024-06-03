package it.epicode.azienda_energia.datalayer.entities.invoices;

import it.epicode.azienda_energia.datalayer.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class InvoiceStatus extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoiceStatus_seq")
    @SequenceGenerator(name = "invoiceStatus_seq", sequenceName = "invoiceStatus_seq")
    private Long id;
    @Column(length = 50, unique = true)
    private String type;
}
