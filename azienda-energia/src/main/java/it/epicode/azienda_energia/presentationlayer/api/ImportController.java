package it.epicode.azienda_energia.presentationlayer.api;

import it.epicode.azienda_energia.businesslayer.services.interfaces.ProvinceService;
import it.epicode.azienda_energia.datalayer.entities.adresses.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/import")
public class ImportController {

    @Autowired
    ProvinceService provinceService;

    @PostMapping("/province")
    public ResponseEntity<List<Province>> importProvince() throws IOException {
        var p = provinceService.addProvince();
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(p.size()));
        return new ResponseEntity<>(p, headers, HttpStatus.OK);
    }
}
