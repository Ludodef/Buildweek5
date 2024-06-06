package it.epicode.azienda_energia.datalayer.repositories;

import it.epicode.azienda_energia.datalayer.entities.Customer;
import it.epicode.azienda_energia.datalayer.entities.adresses.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CustomerRepository extends
        JpaRepository<Customer, Long>,
        PagingAndSortingRepository<Customer, Long> {

    Optional<Customer> findByBusinessName(String businessName);
    Optional<Customer> findByVatNumber(Long vatNumber);
    Optional<Customer> findByBusinessEmail(String businessEmail);
    Optional<Customer> findByPec(String pec);
    Optional<Customer> findByPhoneNumber(Long phoneNumber);
    Optional<Customer> findByContactEmail(String contactEmail);


    Page<Customer> findAllByOrderByBusinessName(Pageable pageable);
    Page<Customer> findAllByOrderByAnnualSales(Pageable pageable);
    Page<Customer> findAllByOrderByInsertionDate(Pageable pageable);
    Page<Customer> findAllByOrderByLastContact(Pageable pageable);
    Page<Customer> findAllByRegisteredOffice_Province_Name(Pageable pageable);

    Page<Customer> findByAnnualSalesGreaterThanEqual(double annualSales, Pageable pageable);
    Page<Customer> findByInsertionDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<Customer> findByLastContactBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<Customer> findByBusinessNameContainingIgnoreCase(String name, Pageable pageable);
}
