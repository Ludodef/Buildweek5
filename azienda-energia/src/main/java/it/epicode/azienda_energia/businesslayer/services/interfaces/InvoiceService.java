package it.epicode.azienda_energia.businesslayer.services.interfaces;

import it.epicode.azienda_energia.businesslayer.services.dto.InvoiceDTO;
import it.epicode.azienda_energia.datalayer.entities.invoices.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface InvoiceService{
    Page<Invoice> getAll(Pageable p);

    Invoice getById(Long id);

    Invoice save(InvoiceDTO e);

    Invoice update(Long id, String type);

    Invoice delete(Long id);

    Page<Invoice> filterInvoicesByCustomer(String customerName, Pageable pageable);
    Page<Invoice> filterInvoicesByStatus(String status, Pageable pageable);
    Page<Invoice> filterInvoicesByDate(LocalDate date, Pageable pageable);
    Page<Invoice> filterInvoicesByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<Invoice> filterInvoicesByYear(int year, Pageable pageable);
    Page<Invoice> filterInvoicesByAmountRange(double minAmount, double maxAmount, Pageable pageable);
}
