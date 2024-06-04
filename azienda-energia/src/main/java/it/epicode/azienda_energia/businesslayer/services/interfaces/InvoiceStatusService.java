package it.epicode.azienda_energia.businesslayer.services.interfaces;

import it.epicode.azienda_energia.businesslayer.services.dto.InvoiceDTO;
import it.epicode.azienda_energia.businesslayer.services.dto.InvoiceStatusDTO;
import it.epicode.azienda_energia.datalayer.entities.invoices.Invoice;
import it.epicode.azienda_energia.datalayer.entities.invoices.InvoiceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceStatusService{

    Page<InvoiceStatus> getAll(Pageable p);

    InvoiceStatus getById(Long id);

    InvoiceStatus save(InvoiceStatusDTO e);

    InvoiceStatus delete(Long id);

}
