package it.epicode.azienda_energia.businesslayer.services.impl;

import it.epicode.azienda_energia.businesslayer.services.dto.InvoiceDTO;
import it.epicode.azienda_energia.businesslayer.services.interfaces.InvoiceService;
import it.epicode.azienda_energia.datalayer.entities.invoices.Invoice;
import it.epicode.azienda_energia.datalayer.repositories.CustomerRepository;
import it.epicode.azienda_energia.datalayer.repositories.InvoiceRepository;
import it.epicode.azienda_energia.datalayer.repositories.InvoiceStatusRepository;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.NotFoundException;
import it.epicode.azienda_energia.presentationlayer.utility.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceStatusRepository invoiceStatusRepository;

    @Autowired
    EntityUtils utils;


    @Autowired
    CustomerRepository customerRepository;

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
        var invoiceStatus =  invoiceStatusRepository.findOneByType("IN LAVORAZIONE");
        var customer = customerRepository.findByBusinessName(e.getCustomer());

        if (customer.isEmpty())
            throw new NoSuchElementException("Non ho trovato nessun customer con businessName");
        if (invoiceStatus.isEmpty())
            throw new NoSuchElementException("Non esiste un invoiceStatus di questo tipo");

        return invoiceRepository.save(
                Invoice.builder()
                        .withAmount(e.getAmount())
                        .withCustomer(customer.get())
                        .withSerialNumber(e.getSerialNumber())
                        .withStatus(invoiceStatus.get())
                        .build()
        );

    }

    @Override
    public Invoice update(Long id, String type) {
        var invoice = this.getById(id);
log.info(type);
        var status = invoiceStatusRepository.findOneByType(type);
log.info(status.toString());
        if (status.isEmpty())
            throw new NoSuchElementException("Non ho trovato nessuno InvoiceStatus");

        invoice.setStatus(status.get());

        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice delete(Long id) {
        var i = this.getById(id);
        invoiceRepository.delete(i);
        return i;
    }

    @Override
    public Page<Invoice> filterInvoicesByCustomer(String customerName, Pageable pageable) {
        return invoiceRepository.findByCustomer_BusinessName(customerName, pageable);
    }

    @Override
    public Page<Invoice> filterInvoicesByStatus(String status, Pageable pageable) {
        return invoiceRepository.findByStatus_Type(status, pageable);
    }

    @Override
    public Page<Invoice> filterInvoicesByDate(LocalDate date, Pageable pageable) {
        return invoiceRepository.findByDate(date, pageable);
    }

    @Override
    public Page<Invoice> filterInvoicesByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return invoiceRepository.findByDateBetween(startDate, endDate, pageable);
    }

    @Override
    public Page<Invoice> filterInvoicesByYear(int year, Pageable pageable) {
        return invoiceRepository.findByDateYear(year, pageable);
    }

    @Override
    public Page<Invoice> filterInvoicesByAmountRange(double minAmount, double maxAmount, Pageable pageable) {
        return invoiceRepository.findByAmountBetween(minAmount, maxAmount, pageable);
    }
}
