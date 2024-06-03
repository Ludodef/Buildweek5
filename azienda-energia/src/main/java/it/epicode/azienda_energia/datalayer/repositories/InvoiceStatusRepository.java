package it.epicode.azienda_energia.datalayer.repositories;

import it.epicode.azienda_energia.datalayer.entities.invoices.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoiceStatusRepository extends
        JpaRepository<InvoiceStatus, Long>,
        PagingAndSortingRepository<InvoiceStatus, Long> {
}
