package it.epicode.azienda_energia.businesslayer.services.interfaces;

import it.epicode.azienda_energia.businesslayer.services.dto.CustomerDTO;
import it.epicode.azienda_energia.datalayer.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

public interface CustomerService extends CRUDService<Customer, CustomerDTO>{

    Customer saveCompanyLogo(long id, MultipartFile file) throws IOException;

    Page<Customer> getCustomersOrderedByBusinessName(Pageable pageable);
    Page<Customer> getCustomersOrderedByAnnualSales(Pageable pageable);
    Page<Customer> getCustomersOrderedByInsertionDate(Pageable pageable);
    Page<Customer> getCustomersOrderedByLastContact(Pageable pageable);
    Page<Customer> getCustomersOrderedByProvince(Pageable pageable);

    Page<Customer> filterCustomersByAnnualSales(double annualSales, Pageable pageable);
    Page<Customer> filterCustomersByInsertionDate(LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<Customer> filterCustomersByLastContact(LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<Customer> filterCustomersByName(String name, Pageable pageable);
}
