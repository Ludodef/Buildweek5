package it.epicode.azienda_energia.businesslayer.services.interfaces;

import it.epicode.azienda_energia.datalayer.entities.adresses.Province;
import it.epicode.azienda_energia.datalayer.repositories.ProvinceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public class ProvinceServiceImpl implements ProvinceService{


    //aggiungere i metodi crud



    @Autowired
    ProvinceRepository provinceRepository;

    String dir = Paths.get("").toAbsolutePath().toString();

    @Override
    public List<Province> addProvince() throws IOException {
        List<Province> province = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Path.of(dir + "/src/main/resources/municipalities_provinces/province-italiane.csv"), StandardCharsets.ISO_8859_1)) {
            province = lines
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(line -> Province.builder()
                            .withAcronym(line[0])
                            .withName(line[1])
                            .withRegion(line[2])
                            .build())
                    .toList();
            provinceRepository.saveAll(province);
            log.info("Province salvate");
        } catch (IOException e) {
            log.error("Errore durante il caricamento delle province dal file CSV", e);
            throw e;
        }
        return province;
    }
}
