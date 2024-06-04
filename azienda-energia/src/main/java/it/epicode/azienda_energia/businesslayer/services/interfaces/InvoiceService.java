package it.epicode.azienda_energia.businesslayer.services.interfaces;

import it.epicode.azienda_energia.businesslayer.services.dto.InvoiceDTO;
import it.epicode.azienda_energia.datalayer.entities.invoices.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceService{
    Page<Invoice> getAll(Pageable p);

    Invoice getById(Long id);

    Invoice save(InvoiceDTO e);

    Invoice update(Long id, String type);

    Invoice delete(Long id);


}
