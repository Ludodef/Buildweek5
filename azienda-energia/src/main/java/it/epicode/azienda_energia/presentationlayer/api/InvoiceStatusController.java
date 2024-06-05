package it.epicode.azienda_energia.presentationlayer.api;

import it.epicode.azienda_energia.businesslayer.services.dto.AddressDTO;
import it.epicode.azienda_energia.businesslayer.services.dto.InvoiceStatusDTO;
import it.epicode.azienda_energia.businesslayer.services.interfaces.AddressService;
import it.epicode.azienda_energia.businesslayer.services.interfaces.InvoiceStatusService;
import it.epicode.azienda_energia.datalayer.entities.adresses.Address;
import it.epicode.azienda_energia.datalayer.entities.invoices.InvoiceStatus;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.ApiValidationException;
import it.epicode.azienda_energia.presentationlayer.api.models.InvoiceStatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice-status")
public class InvoiceStatusController {
    @Autowired
    InvoiceStatusService invoiceStatus;

    @GetMapping
    public ResponseEntity<Page<InvoiceStatus>> getAllInvoicesStatus (Pageable p) {
        var allInvoicesStatus = invoiceStatus.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allInvoicesStatus.getTotalElements()));
        return new ResponseEntity<>(allInvoicesStatus, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InvoiceStatus> addInvoiceStatus (
            @RequestBody @Validated InvoiceStatusModel model,
            BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }
        var a = invoiceStatus.save(InvoiceStatusDTO.builder()
                .withType(model.type())
                .build());

        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

//    @PutMapping("{id}")
//    public ResponseEntity<InvoiceStatus> updateInvoiceStatus (
//            @PathVariable Long id,
//            @RequestBody InvoiceStatus invoiceStatusModified
//    ){
//        var a = invoiceStatus.update(id, invoiceStatusModified);
//        return new ResponseEntity<>(a, HttpStatus.OK);
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<InvoiceStatus> deleteInvoiceStatus (
            @PathVariable Long id
    ) {
        var a = invoiceStatus.delete(id);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
}
