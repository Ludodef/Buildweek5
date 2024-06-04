package it.epicode.azienda_energia.businesslayer.services.interfaces;

import it.epicode.azienda_energia.businesslayer.services.dto.InvoiceDTO;
import it.epicode.azienda_energia.datalayer.entities.invoices.Invoice;

public interface InvoiceService extends CRUDService<Invoice, InvoiceDTO>{
}
