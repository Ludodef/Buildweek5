package it.epicode.azienda_energia.datalayer.entities;


import it.epicode.azienda_energia.datalayer.entities.adresses.Address;
import it.epicode.azienda_energia.datalayer.entities.enums.CustomerType;
import it.epicode.azienda_energia.datalayer.entities.invoices.Invoice;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq")
    private Long id;
    private String businessName;
    private Long vatNumber;
    private String businessEmail;
    private LocalDate insertionDate;
    private LocalDate lastContact;
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
    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoices;



}
