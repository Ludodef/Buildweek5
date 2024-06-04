package it.epicode.azienda_energia.businesslayer.services.impl;

import it.epicode.azienda_energia.businesslayer.services.dto.InvoiceStatusDTO;
import it.epicode.azienda_energia.businesslayer.services.interfaces.InvoiceStatusService;
import it.epicode.azienda_energia.datalayer.entities.invoices.InvoiceStatus;
import it.epicode.azienda_energia.datalayer.repositories.InvoiceStatusRepository;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.NotFoundException;
import it.epicode.azienda_energia.presentationlayer.utility.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InvoiceStatusServiceImpl implements InvoiceStatusService {

    @Autowired
    InvoiceStatusRepository invoiceStatusRepository;

    @Autowired
    EntityUtils utils;

    @Override
    public Page<InvoiceStatus> getAll(Pageable p) {
        return invoiceStatusRepository.findAll(p);
    }

    @Override
    public InvoiceStatus getById(Long id) {
        return invoiceStatusRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @Override
    public InvoiceStatus save(InvoiceStatusDTO e) {
        return invoiceStatusRepository.save(InvoiceStatus.builder()
                        .withType(e.getType())
                        .build());
    }

    @Override
    public InvoiceStatus delete(Long id) {
        var i = this.getById(id);
        invoiceStatusRepository.delete(i);
        return i;
    }

}
