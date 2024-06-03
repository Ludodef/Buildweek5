package it.epicode.azienda_energia.businesslayer.services.interfaces;

import it.epicode.azienda_energia.datalayer.entities.adresses.Municipality;
import it.epicode.azienda_energia.datalayer.entities.adresses.Province;

import java.io.IOException;
import java.util.List;

public interface MunicipalityService {

    List<Municipality> addMunicipality() throws IOException;
}
