package it.epicode.azienda_energia.datalayer.entities.invoices;

import it.epicode.azienda_energia.datalayer.entities.BaseEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class InvoiceStatus extends BaseEntity {
    private String type;
}
