package it.epicode.azienda_energia.datalayer.repositories;

import it.epicode.azienda_energia.datalayer.entities.adresses.Address;
import it.epicode.azienda_energia.datalayer.entities.invoices.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoiceRepository extends
        JpaRepository<Invoice, Long>,
        PagingAndSortingRepository<Invoice, Long> {
}
