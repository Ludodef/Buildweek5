package it.epicode.azienda_energia.datalayer.repositories;

import it.epicode.azienda_energia.datalayer.entities.adresses.Address;
import it.epicode.azienda_energia.datalayer.entities.invoices.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;

public interface InvoiceRepository extends
        JpaRepository<Invoice, Long>,
        PagingAndSortingRepository<Invoice, Long> {

    Page<Invoice> findByCustomer_BusinessName(String businessName, Pageable pageable);
    Page<Invoice> findByStatus_Type(String status, Pageable pageable);
    Page<Invoice> findByDate(LocalDate date, Pageable pageable);
    Page<Invoice> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<Invoice> findByDateYear(int year, Pageable pageable);  // Custom method
    Page<Invoice> findByAmountBetween(double minAmount, double maxAmount, Pageable pageable);
}
