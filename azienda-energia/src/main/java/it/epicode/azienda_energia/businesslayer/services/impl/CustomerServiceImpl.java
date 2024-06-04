package it.epicode.azienda_energia.businesslayer.services.impl;

import it.epicode.azienda_energia.businesslayer.services.dto.CustomerDTO;
import it.epicode.azienda_energia.businesslayer.services.interfaces.CustomerService;
import it.epicode.azienda_energia.datalayer.entities.Customer;
import it.epicode.azienda_energia.datalayer.repositories.CustomerRepository;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.NotFoundException;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.duplicated.*;
import it.epicode.azienda_energia.presentationlayer.utility.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EntityUtils utils;

    @Override
    public Page<Customer> getAll(Pageable p) {
        return customerRepository.findAll(p);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @Override
    public Customer save(CustomerDTO e) {
        var businessNameDuplicate = customerRepository.findByBusinessName(e.getBusinessName());
        var vatNumberDuplicate = customerRepository.findByVatNumber(e.getVatNumber());
        var businessEmailDuplicate = customerRepository.findByBusinessEmail(e.getBusinessEmail());
        var pecDuplicate = customerRepository.findByPec(e.getPec());
        var phoneNumberDuplicate = customerRepository.findByPhoneNumber(e.getPhoneNumber());
        var contactEmailDuplicate = customerRepository.findByContactEmail(e.getContactEmail());

        if (businessNameDuplicate.isPresent())
            throw new DuplicateBusinessNameException(e.getBusinessName());

        if (vatNumberDuplicate.isPresent())
            throw new DuplicateVatNumberException(String.valueOf(e.getVatNumber()));

        if (businessEmailDuplicate.isPresent())
            throw new DuplicateBusinessEmailException(e.getBusinessEmail());

        if (pecDuplicate.isPresent())
            throw new DuplicatePecException(e.getPec());

        if (phoneNumberDuplicate.isPresent())
            throw new DuplicatePhoneNumberException(String.valueOf(e.getPhoneNumber()));

        if (contactEmailDuplicate.isPresent())
            throw new DuplicateContactEmailException(e.getContactEmail());

        if(e.getRegisteredOffice() == null) {
            e.setRegisteredOffice(e.getHeadquarters());
        }

        return customerRepository.save(
                Customer.builder()
                        .withBusinessName(e.getBusinessName())
                        .withVatNumber(e.getVatNumber())
                        .withBusinessEmail(e.getBusinessEmail())
                        .withAnnualSales(e.getAnnualSales())
                        .withPec(e.getPec())
                        .withPhoneNumber(e.getPhoneNumber())
                        .withContactEmail(e.getContactEmail())
                        .withContactFirstName(e.getContactFirstName())
                        .withContactLastName(e.getContactLastName())
                        .withContactPhoneNumber(e.getContactPhoneNumber())
                        .withCompanyLogo(e.getCompanyLogo())
                        .withRegisteredOffice(e.getRegisteredOffice())
                        .withHeadquarters(e.getHeadquarters())
                        .withType(e.getType())
                        .build()
        );
    }

    @Override
    public Customer update(Long id, Customer e) {
        var customer = this.getById(id);
        utils.copy(e, customer);
        return customerRepository.save(customer);
    }

    @Override
    public Customer delete(Long id) {
        var customer = this.getById(id);
        customerRepository.delete(customer);
        return customer;
    }
}
