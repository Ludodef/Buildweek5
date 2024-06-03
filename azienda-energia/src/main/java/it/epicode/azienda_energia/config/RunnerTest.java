package it.epicode.azienda_energia.config;

import it.epicode.azienda_energia.businesslayer.services.interfaces.MunicipalityService;
import it.epicode.azienda_energia.businesslayer.services.interfaces.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunnerTest implements CommandLineRunner {

    @Autowired
    ProvinceService provinceImpl;

    @Autowired
    MunicipalityService municipalityImpl;

    @Override
    public void run(String... args) throws Exception {
        provinceImpl.addProvince();
        municipalityImpl.addMunicipality();


    }
}
