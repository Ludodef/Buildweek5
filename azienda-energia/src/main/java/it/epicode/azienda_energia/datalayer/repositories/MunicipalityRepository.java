package it.epicode.azienda_energia.datalayer.repositories;

import it.epicode.azienda_energia.datalayer.entities.adresses.Address;
import it.epicode.azienda_energia.datalayer.entities.adresses.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MunicipalityRepository extends
        JpaRepository<Municipality, Long>,
        PagingAndSortingRepository<Municipality, Long> {
}
