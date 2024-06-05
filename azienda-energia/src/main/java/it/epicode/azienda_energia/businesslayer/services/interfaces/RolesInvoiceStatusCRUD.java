package it.epicode.azienda_energia.businesslayer.services.interfaces;

import it.epicode.azienda_energia.businesslayer.services.dto.BaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RolesInvoiceStatusCRUD<T, A extends BaseDTO> {

    Page<T> getAll(Pageable p);

    T save(A e);

    T delete(Long id);
}
