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
    @Column(length = 50, unique = true)
    private String businessName;
    @Column(length = 11, unique = true)
    private Long vatNumber;
    @Column(length = 50, unique = true)
    private String businessEmail;
    private LocalDate insertionDate;
    private LocalDate lastContact;
    private double annualSales;
    @Column(length = 50, unique = true)
    private String pec;
    @Column(length = 15, unique = true)
    private Long phoneNumber;
    @Column(length = 50, unique = true)
    private String contactEmail;
    @Column(length = 50)
    private String contactFirstName;
    @Column(length = 50)
    private String contactLastName;
    @Column(length = 50)
    private Long contactPhoneNumber;
    private String companyLogo;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address registeredOffice;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address headquarters;
    @Enumerated(EnumType.STRING)
    private CustomerType type;
    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoices;



}
