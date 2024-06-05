package it.epicode.azienda_energia.datalayer.entities.adresses;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Address extends BaseEntity {

    private String street;
    private String number;
    private String location;
    private Integer zip;

    @ManyToOne
    private Municipality municipality;

}
