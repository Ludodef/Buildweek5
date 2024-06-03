package it.epicode.azienda_energia.datalayer.repositories;

import it.epicode.azienda_energia.datalayer.entities.adresses.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProvinceRepository extends
        JpaRepository<Province, Long>,
        PagingAndSortingRepository<Province, Long> {

    Optional<Province> findOneByName(String name);
}
