package it.epicode.azienda_energia.datalayer.entities.adresses;

import it.epicode.azienda_energia.datalayer.entities.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "addresses")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Address extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq")
    private Long id;
    private String street;
    private String number;
    private String location;
    private int zip;
    @ManyToOne //
    private Municipality municipality;
}
