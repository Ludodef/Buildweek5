package it.epicode.azienda_energia.businesslayer.services.interfaces;

import it.epicode.azienda_energia.datalayer.entities.adresses.Municipality;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MunicipalityService {

    List<Municipality> addMunicipality() throws IOException;

    Optional<Municipality> findByName(String name);


}