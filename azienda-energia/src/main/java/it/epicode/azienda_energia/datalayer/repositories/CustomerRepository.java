package it.epicode.azienda_energia.datalayer.repositories;

import it.epicode.azienda_energia.datalayer.entities.Customer;
import it.epicode.azienda_energia.datalayer.entities.adresses.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

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
}
