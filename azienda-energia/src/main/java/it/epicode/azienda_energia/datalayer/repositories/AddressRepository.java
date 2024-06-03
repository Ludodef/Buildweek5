package it.epicode.azienda_energia.datalayer.repositories;

import it.epicode.azienda_energia.datalayer.entities.Customer;
import it.epicode.azienda_energia.datalayer.entities.adresses.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressRepository extends
        JpaRepository<Address, Long>,
        PagingAndSortingRepository<Address, Long> {
}
