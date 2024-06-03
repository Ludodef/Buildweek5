package it.epicode.azienda_energia.businesslayer.services.interfaces;

import it.epicode.azienda_energia.datalayer.entities.adresses.Municipality;
import it.epicode.azienda_energia.datalayer.entities.adresses.Province;
import it.epicode.azienda_energia.datalayer.repositories.MunicipalityRepository;
import it.epicode.azienda_energia.datalayer.repositories.ProvinceRepository;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.NotFoundException;
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
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Service
@Slf4j
public class MunicipalityServiceImpl implements MunicipalityService{

    @Autowired
    MunicipalityRepository municipalityRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    String dir = Paths.get("").toAbsolutePath().toString();

    @Override
    public List<Municipality> addMunicipality() throws IOException {
        List<Municipality> municipality = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Path.of(dir + "/src/main/resources/municipalities_provinces/comuni-italiani.csv"), StandardCharsets.UTF_8)) {
            municipality = lines
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(line ->
                            {
                            Province province = provinceRepository.findOneByName(line[3])
                            .orElseThrow(() -> new RuntimeException("Province not found: " + line[3]));
                            return Municipality.builder()
                            .withName(line[2])
                            .withProvince(
                                    province
                            )
                            .build();
                            }).toList();
            municipalityRepository.saveAll(municipality);
            log.info("municipality salvate");
        } catch (IOException e) {
            log.error("Errore durante il caricamento delle province dal file CSV", e);
            throw e;
        } catch (NoSuchElementException e) {
            log.error("Errore durante il caricamento delle province dal file CSV", e);
            throw new NotFoundException();
        }
        return municipality;
    }
}
