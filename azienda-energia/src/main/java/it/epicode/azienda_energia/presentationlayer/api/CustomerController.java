package it.epicode.azienda_energia.presentationlayer.api;

import it.epicode.azienda_energia.businesslayer.services.dto.AddressDTO;
import it.epicode.azienda_energia.businesslayer.services.dto.CustomerDTO;
import it.epicode.azienda_energia.businesslayer.services.interfaces.AddressService;
import it.epicode.azienda_energia.businesslayer.services.interfaces.CustomerService;
import it.epicode.azienda_energia.datalayer.entities.Customer;
import it.epicode.azienda_energia.datalayer.entities.enums.CustomerType;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.ApiValidationException;
import it.epicode.azienda_energia.presentationlayer.api.models.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    CustomerService customer;

    @Autowired
    AddressService address;


    @GetMapping
    public ResponseEntity<Page<Customer>> getAllCustomers (Pageable p) {
        var allCustomers = customer.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allCustomers.getTotalElements()));
        return new ResponseEntity<>(allCustomers, headers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomer (@PathVariable Long id) {
        var e = customer.getById(id);
        return new ResponseEntity<>(e, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer (
            @RequestBody @Validated CustomerModel model,
            BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }

        var headquarters = address.save(AddressDTO.builder()
                        .withStreet(model.headquarters().street())
                        .withZip(Integer.parseInt(model.headquarters().zip()))
                        .withNumber(model.headquarters().number())
                        .withMunicipality(model.headquarters().municipality())
                        .withLocation(model.headquarters().location())
                        .build());

        var a = customer.save(
                CustomerDTO.builder()
                        .withBusinessName(model.businessName())
                        .withVatNumber(Long.valueOf(model.vatNumber()))
                        .withBusinessEmail(model.businessEmail())
                        .withAnnualSales(model.annualSales())
                        .withPec(model.pec())
                        .withPhoneNumber(model.phoneNumber())
                        .withContactEmail(model.contactEmail())
                        .withContactFirstName(model.contactFirstName())
                        .withContactLastName(model.contactLastName())
                        .withContactPhoneNumber(model.contactPhoneNumber())
                        .withCompanyLogo(model.companyLogo())
                        .withHeadquarters(headquarters)
                        .withRegisteredOffice(headquarters)
                        .withType(CustomerType.valueOf(model.type()))
                        .build()
        );

        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> updateCustomer (
            @PathVariable Long id,
            @RequestBody Customer customerModified
    ){
        var a = customer.update(id, customerModified);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Customer> deleteCustomer (
            @PathVariable Long id
    ) {
        var a = customer.delete(id);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @PatchMapping("/{id}/companyLogo")
    public Customer uploadAvatar(@RequestParam("companyLogo") MultipartFile file, @PathVariable Long id) {
        try {
            return customer.saveCompanyLogo(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/ordered")
    public Page<Customer> getCustomersOrdered(
            @RequestParam String orderBy,
            Pageable pageable) {
        return switch (orderBy) {
            case "name" -> customer.getCustomersOrderedByBusinessName(pageable);
            case "annualSales" -> customer.getCustomersOrderedByAnnualSales(pageable);
            case "insertionDate" -> customer.getCustomersOrderedByInsertionDate(pageable);
            case "lastContact" -> customer.getCustomersOrderedByLastContact(pageable);
            case "province" -> customer.getCustomersOrderedByProvince(pageable);
            default -> throw new IllegalArgumentException("Invalid orderBy parameter. Inserisci {name, annualSales //" +
                    "insertionDate, lastContact, province}");
        };
    }

   @GetMapping("/filter")
    public ResponseEntity<Page<Customer>> filterCustomers(
            @RequestParam String filterType,
            @RequestParam(required = false) Double annualSales,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String name,
            Pageable pageable) {

        Page<Customer> customers;
        switch (filterType) {
            case "annualSales":
                if (annualSales == null) {
                    return ResponseEntity.badRequest().build();
                }
                customers = customer.filterCustomersByAnnualSales(annualSales, pageable);
                break;

            case "insertionDate":
                if (startDate == null || endDate == null) {
                return ResponseEntity.badRequest().build();
            }
            customers = customer.filterCustomersByInsertionDate(startDate, endDate, pageable);
            break;

            case "lastContact":
                if (startDate == null || endDate == null) {
                return ResponseEntity.badRequest().build();
            }
            customers = customer.filterCustomersByLastContact(startDate, endDate, pageable);
            break;

            case "name":
                if (name == null) {
                    return ResponseEntity.badRequest().build();
                }
                customers = customer.filterCustomersByName(name, pageable);
                break;

            default:
                throw new RuntimeException("Invalid filterType value");
        }

        return ResponseEntity.ok(customers);
    }
}
