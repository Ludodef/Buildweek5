package it.epicode.azienda_energia.businesslayer.services.impl;


import it.epicode.azienda_energia.businesslayer.services.dto.RolesDTO;
import it.epicode.azienda_energia.businesslayer.services.interfaces.RolesService;
import it.epicode.azienda_energia.datalayer.entities.Roles;
import it.epicode.azienda_energia.datalayer.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    RolesRepository roles;

    @Override
    public Roles save(RolesDTO role) {

        return roles.save(Roles.builder()
                .withRoleType(role.getRoleType()).build());
    }

    @Override
    public Page<Roles> getAll(Pageable p) {
        return roles.findAll(p);
    }


    @Override
    public Roles delete(Long id) {
        return null;
    }
}
