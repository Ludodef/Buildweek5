package it.epicode.azienda_energia.config;

import it.epicode.azienda_energia.businesslayer.services.dto.InvoiceStatusDTO;
import it.epicode.azienda_energia.businesslayer.services.impl.InvoiceStatusServiceImpl;
import it.epicode.azienda_energia.businesslayer.services.interfaces.MunicipalityService;
import it.epicode.azienda_energia.businesslayer.services.interfaces.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RunnerTest implements CommandLineRunner {

    @Autowired
    ProvinceService provinceImpl;

    @Autowired
    MunicipalityService municipalityImpl;

    @Autowired
    InvoiceStatusServiceImpl invoiceStatusService;

    @Override
    public void run(String... args) throws Exception {
        provinceImpl.addProvince();
        municipalityImpl.addMunicipality();
        invoiceStatusService.save(InvoiceStatusDTO.builder()
                        .withType("IN LAVORAZIONE")
                .build());
        invoiceStatusService.save(InvoiceStatusDTO.builder()
                .withType("INVIATA")
                .build());
        invoiceStatusService.save(InvoiceStatusDTO.builder()
                .withType("RICEVUTA")
                .build());
        invoiceStatusService.save(InvoiceStatusDTO.builder()
                .withType("PAGATA")
                .build());
        invoiceStatusService.save(InvoiceStatusDTO.builder()
                .withType("RESPINTA")
                .build());

    }
}

