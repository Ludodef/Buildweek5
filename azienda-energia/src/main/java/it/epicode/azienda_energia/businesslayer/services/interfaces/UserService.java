package it.epicode.azienda_energia.businesslayer.services.interfaces;

import it.epicode.azienda_energia.businesslayer.services.dto.LoginResponseDTO;
import it.epicode.azienda_energia.businesslayer.services.dto.RegisterUserDTO;
import it.epicode.azienda_energia.businesslayer.services.dto.RegisteredUserDTO;
import it.epicode.azienda_energia.datalayer.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface UserService {

    RegisteredUserDTO register(RegisterUserDTO user);

    Optional<LoginResponseDTO> login(String username, String password);

    RegisteredUserDTO getById(long id);

    Page<User> getAll(Pageable p);

    RegisteredUserDTO update(long id, String username);

    RegisteredUserDTO delete(Long id);

    RegisteredUserDTO addRole(Long id, String role);
    RegisteredUserDTO removeRole(Long id, String role);

    User saveAvatar(long id, MultipartFile file) throws IOException;

}
