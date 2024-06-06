package it.epicode.azienda_energia.businesslayer.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.azienda_energia.businesslayer.services.dto.CustomerDTO;
import it.epicode.azienda_energia.businesslayer.services.interfaces.CustomerService;
import it.epicode.azienda_energia.datalayer.entities.Customer;
import it.epicode.azienda_energia.datalayer.repositories.CustomerRepository;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.NotFoundException;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.duplicated.*;
import it.epicode.azienda_energia.presentationlayer.utility.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EntityUtils utils;

    @Value("${CLOUDINARY_URL}")
    private String cloudinaryUrl;

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


    @Override
    public Customer saveCompanyLogo(long id, MultipartFile file) throws IOException {
        var customer = this.getById(id);
        Cloudinary cloudinary = new Cloudinary(cloudinaryUrl);
        var image = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        customer.setCompanyLogo(image);
        return customerRepository.save(customer);
    }

    @Override
    public Page<Customer> getCustomersOrderedByBusinessName(Pageable pageable) {
        return customerRepository.findAllByOrderByBusinessName(pageable);
    }

    @Override
    public Page<Customer> getCustomersOrderedByAnnualSales(Pageable pageable) {
        return customerRepository.findAllByOrderByAnnualSales(pageable);
    }

    @Override
    public Page<Customer> getCustomersOrderedByInsertionDate(Pageable pageable) {
        return customerRepository.findAllByOrderByInsertionDate(pageable);
    }

    @Override
    public Page<Customer> getCustomersOrderedByLastContact(Pageable pageable) {
        return customerRepository.findAllByOrderByLastContact(pageable);
    }

    @Override
    public Page<Customer> getCustomersOrderedByProvince(Pageable pageable) {
        return customerRepository.findAllByRegisteredOffice_Municipality_Province_Name(pageable);
    }

    @Override
    public Page<Customer> filterCustomersByAnnualSales(double annualSales, Pageable pageable) {
        return customerRepository.findByAnnualSalesGreaterThanEqual(annualSales, pageable);
    }

    @Override
    public Page<Customer> filterCustomersByInsertionDate(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return customerRepository.findByInsertionDateBetween(startDate, endDate, pageable);
    }

    @Override
    public Page<Customer> filterCustomersByLastContact(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return customerRepository.findByLastContactBetween(startDate, endDate, pageable);
    }

    @Override
    public Page<Customer> filterCustomersByName(String name, Pageable pageable) {
        return customerRepository.findByBusinessNameContainingIgnoreCase(name, pageable);
    }


}
