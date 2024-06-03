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

    private String street;
    private String number;
    private String location;
    private int zip;
    @ManyToOne
    private Municipality municipality;

    @OneToOne(mappedBy = "headquarters")
    private Customer customerHeadquarters;

    @OneToMany(mappedBy = "registeredOffice")
    private List<Customer> customerRegisteredOffice;
}
