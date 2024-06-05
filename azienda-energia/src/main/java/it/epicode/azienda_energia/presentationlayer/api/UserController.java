package it.epicode.azienda_energia.presentationlayer.api;


import it.epicode.azienda_energia.businesslayer.services.dto.LoginResponseDTO;
import it.epicode.azienda_energia.businesslayer.services.dto.RegisterUserDTO;
import it.epicode.azienda_energia.businesslayer.services.dto.RegisteredUserDTO;
import it.epicode.azienda_energia.businesslayer.services.interfaces.UserService;
import it.epicode.azienda_energia.datalayer.entities.Customer;
import it.epicode.azienda_energia.datalayer.entities.User;
import it.epicode.azienda_energia.datalayer.entities.invoices.Invoice;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.ApiValidationException;
import it.epicode.azienda_energia.presentationlayer.api.models.LoginModel;
import it.epicode.azienda_energia.presentationlayer.api.models.RegisterUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService user;

    @GetMapping
    public ResponseEntity<Page<User>> getEvents (Pageable p) {
        var allUsers = user.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allUsers.getTotalElements()));
        return new ResponseEntity<>(allUsers, headers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RegisteredUserDTO> getEvent (@PathVariable Long id) {
        var u = user.getById(id);
        return new ResponseEntity<>(u, HttpStatus.FOUND);
    }
    @PostMapping
    public ResponseEntity<RegisteredUserDTO> register(@RequestBody @Validated RegisterUserModel model, BindingResult validator){
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }

        var registeredUser = user.register(
                RegisterUserDTO.builder()
                        .withFirstName(model.firstName())
                        .withLastName(model.lastName())
                        .withUsername(model.username())
                        .withEmail(model.email())
                        .withPassword(model.password())
                .build());

        return  new ResponseEntity<> (registeredUser, HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated LoginModel model, BindingResult validator) {
        if (validator.hasErrors()) {
            throw  new ApiValidationException(validator.getAllErrors());
        }
        return new ResponseEntity<>(user.login(model.username(), model.password()).orElseThrow(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<RegisteredUserDTO> updateInvoice (
            @PathVariable Long id,
            @RequestParam("type") User newuser
    ){
        var u = user.update(id, newuser);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RegisteredUserDTO> deleteEvent (
            @PathVariable Long id
    ) {
        var e = user.delete(id);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @PatchMapping("/{id}/avatar")
    public User uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable Long id) {
        try {
            return user.saveAvatar(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
