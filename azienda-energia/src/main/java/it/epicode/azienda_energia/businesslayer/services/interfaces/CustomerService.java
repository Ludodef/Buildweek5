package it.epicode.azienda_energia.businesslayer.services.interfaces;

import it.epicode.azienda_energia.businesslayer.services.dto.CustomerDTO;
import it.epicode.azienda_energia.datalayer.entities.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CustomerService extends CRUDService<Customer, CustomerDTO>{

    Customer saveCompanyLogo(long id, MultipartFile file) throws IOException;
}
