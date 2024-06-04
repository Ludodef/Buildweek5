package it.epicode.azienda_energia.presentationlayer.api;

import it.epicode.azienda_energia.businesslayer.services.dto.AddressDTO;
import it.epicode.azienda_energia.businesslayer.services.interfaces.AddressService;
import it.epicode.azienda_energia.datalayer.entities.adresses.Address;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.ApiValidationException;
import it.epicode.azienda_energia.presentationlayer.api.models.AddressModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService address;

    @GetMapping
    public ResponseEntity<Page<Address>> getAddresses (Pageable p) {
        var allAddresses = address.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allAddresses.getTotalElements()));
        return new ResponseEntity<>(allAddresses, headers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Address> getAddress (@PathVariable Long id) {
        var e = address.getById(id);
        return new ResponseEntity<>(e, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Address> addAddress (
            @RequestBody @Validated AddressModel model,
            BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }
        var a = address.save(AddressDTO.builder()
                        .withStreet(model.street())
                        .withNumber(model.number())
                        .withLocation(model.location())
                        .withZip(Integer.parseInt(model.zip()))
                        .withMunicipality(model.municipality())
                .build());

        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Address> updateAddress (
            @PathVariable Long id,
            @RequestBody Address addressModified
    ){
        var a = address.update(id, addressModified);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
}
