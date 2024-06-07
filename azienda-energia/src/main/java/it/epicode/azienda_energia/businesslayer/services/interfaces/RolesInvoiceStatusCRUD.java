package it.epicode.azienda_energia.businesslayer.services.interfaces;

import it.epicode.azienda_energia.businesslayer.services.dto.BaseDTO;
import it.epicode.azienda_energia.datalayer.entities.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RolesInvoiceStatusCRUD<T extends BaseEntity, A extends BaseDTO> {

    Page<T> getAll(Pageable p);

    T save(A e);

    T delete(Long id);
}
