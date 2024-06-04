package it.epicode.azienda_energia.businesslayer.services.impl;

import it.epicode.azienda_energia.businesslayer.services.dto.InvoiceDTO;
import it.epicode.azienda_energia.businesslayer.services.interfaces.InvoiceService;
import it.epicode.azienda_energia.datalayer.entities.invoices.Invoice;
import it.epicode.azienda_energia.datalayer.repositories.InvoiceRepository;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    public Page<Invoice> getAll(Pageable p) {
        return invoiceRepository.findAll(p);
    }

    @Override
    public Invoice getById(Long id) {
        return invoiceRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @Override
    public Invoice save(InvoiceDTO e) {
        return null;
    }

    @Override
    public Invoice update(Long id, Invoice e) {
        return null;
    }

    @Override
    public Invoice delete(Long id) {
        return null;
    }
}
