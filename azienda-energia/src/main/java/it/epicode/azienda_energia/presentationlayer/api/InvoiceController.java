package it.epicode.azienda_energia.presentationlayer.api;

import it.epicode.azienda_energia.businesslayer.services.dto.AddressDTO;
import it.epicode.azienda_energia.businesslayer.services.dto.InvoiceDTO;
import it.epicode.azienda_energia.businesslayer.services.interfaces.AddressService;
import it.epicode.azienda_energia.businesslayer.services.interfaces.CustomerService;
import it.epicode.azienda_energia.businesslayer.services.interfaces.InvoiceService;
import it.epicode.azienda_energia.datalayer.entities.adresses.Address;
import it.epicode.azienda_energia.datalayer.entities.invoices.Invoice;
import it.epicode.azienda_energia.datalayer.repositories.CustomerRepository;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.ApiValidationException;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.NotFoundException;
import it.epicode.azienda_energia.presentationlayer.api.models.AddressModel;
import it.epicode.azienda_energia.presentationlayer.api.models.InvoiceModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoice;


    @GetMapping
    public ResponseEntity<Page<Invoice>> getInvoice (Pageable p) {
        var allInvoice = invoice.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allInvoice.getTotalElements()));
        return new ResponseEntity<>(allInvoice, headers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Invoice> getInvoice (@PathVariable Long id) {
        var e = invoice.getById(id);
        return new ResponseEntity<>(e, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Invoice> addInvoice (
            @RequestBody @Validated InvoiceModel model,
            Pageable p,
            BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }

        var serialNumber = invoice.getAll(p).getTotalElements();

        var a = invoice.save(InvoiceDTO.builder()
                        .withAmount(model.amount())
                        .withSerialNumber(++serialNumber)
                        .withCustomer(model.customerBusinessName())
                        .build());

        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Invoice> updateInvoice (
            @PathVariable Long id,
            @RequestBody String type
    ){
        var a = invoice.update(id, type);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Invoice> deleteInvoice (
            @PathVariable Long id
    ) {
        var i = invoice.delete(id);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
