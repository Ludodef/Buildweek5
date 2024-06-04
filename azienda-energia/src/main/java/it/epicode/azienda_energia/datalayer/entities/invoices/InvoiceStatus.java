package it.epicode.azienda_energia.datalayer.entities.invoices;

import it.epicode.azienda_energia.datalayer.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
public class InvoiceStatus extends BaseEntity {
    @Column(length = 50, unique = true)
    private String type;

    @OneToMany(mappedBy = "status")
    private List<Invoice> invoices;
}
