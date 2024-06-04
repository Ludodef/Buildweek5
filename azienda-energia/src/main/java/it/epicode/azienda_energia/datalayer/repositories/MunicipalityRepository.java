package it.epicode.azienda_energia.datalayer.repositories;

import it.epicode.azienda_energia.datalayer.entities.adresses.Address;
import it.epicode.azienda_energia.datalayer.entities.adresses.Municipality;
import it.epicode.azienda_energia.datalayer.entities.adresses.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MunicipalityRepository extends
        JpaRepository<Municipality, Long>,
        PagingAndSortingRepository<Municipality, Long> {

    Optional<Municipality> findOneByName(String name);
}
