package it.epicode.azienda_energia.datalayer.entities.adresses;

import it.epicode.azienda_energia.datalayer.entities.BaseEntity;

import it.epicode.azienda_energia.datalayer.entities.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Address extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq")
    private Long id;
    private String street;
    private String number;
    private String location;
    private int zip;
    @ManyToOne
    private Municipality municipality;

    @OneToMany(mappedBy = "headquarters")
    private List<Customer> customerHeadquarters;

    @OneToMany(mappedBy = "registeredOffice")
    private List<Customer> customerRegisteredOffice;
}
