package it.epicode.azienda_energia.businesslayer.services.dto;

import it.epicode.azienda_energia.datalayer.entities.Roles;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class LoginResponseDTO {
    Long id;
    String firstName;
    String lastName;
    String username;
    String email;
    private final List<Roles> roles;
    String token;

    @Builder(setterPrefix = "with")
    public LoginResponseDTO(List<Roles> roles, Long id, String firstName, String lastName, String username, String email, String token) {
        this.roles = roles;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.token = token;
    }
}


