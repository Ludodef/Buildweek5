package it.epicode.azienda_energia.businesslayer.services.interfaces;

import it.epicode.azienda_energia.datalayer.entities.adresses.Province;

import java.io.IOException;
import java.util.List;

public interface ProvinceService {

    List<Province> addProvince() throws IOException;

}
