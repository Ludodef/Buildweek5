package it.epicode.azienda_energia.businesslayer.services.dto;

import it.epicode.azienda_energia.datalayer.entities.adresses.Address;
import it.epicode.azienda_energia.datalayer.entities.enums.CustomerType;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class CustomerDTO extends BaseDTO{
    private String businessName;
    private Long vatNumber;
    private String businessEmail;
    private double annualSales;
    private String pec;
    private Long phoneNumber;
    private String contactEmail;
    private String contactFirstName;
    private String contactLastName;
    private Long contactPhoneNumber;
    private String companyLogo;
    private Address registeredOffice;
    private Address headquarters;
    private CustomerType type;
}
