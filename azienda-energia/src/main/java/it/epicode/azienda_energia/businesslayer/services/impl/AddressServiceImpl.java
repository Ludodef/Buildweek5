package it.epicode.azienda_energia.businesslayer.services.impl;

import it.epicode.azienda_energia.businesslayer.services.dto.AddressDTO;
import it.epicode.azienda_energia.businesslayer.services.interfaces.AddressService;
import it.epicode.azienda_energia.datalayer.entities.adresses.Address;
import it.epicode.azienda_energia.datalayer.repositories.AddressRepository;
import it.epicode.azienda_energia.datalayer.repositories.MunicipalityRepository;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.NotFoundException;
import it.epicode.azienda_energia.presentationlayer.utility.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository address;

    @Autowired
    MunicipalityRepository municipalityRepository;

    @Autowired
    EntityUtils utils;


    @Override
    public Page<Address> getAll(Pageable p) {
        return address.findAll(p);
    }

    @Override
    public Address getById(Long id) {
        return address.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @Override
    public Address save(AddressDTO e) {
        var municipality = municipalityRepository.findOneByName(e.getMunicipality());

        if (municipality.isEmpty())
            throw new NoSuchElementException();

        return address.save(Address.builder()
                .withStreet(e.getStreet())
                .withNumber(e.getNumber())
                .withLocation(e.getLocation())
                .withZip(e.getZip())
                .withMunicipality(municipality.get())
                .build());
    }

    @Override
    public Address update(Long id, Address e) {
        var a = this.getById(id);
        utils.copy(e, a);
        return address.save(a);
    }

    @Override
    public Address delete(Long id) {
        try {
            var a = this.getById(id);
            address.delete(a);
            return a;
        } catch (Exception e) {
            log.error(String.format("Error deleting address with id = %s", id), e);
            throw new RuntimeException();
        }
    }
}
