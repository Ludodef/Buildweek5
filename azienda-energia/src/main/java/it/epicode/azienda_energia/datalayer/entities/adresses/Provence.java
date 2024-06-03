package it.epicode.azienda_energia.datalayer.entities.adresses;

import it.epicode.azienda_energia.datalayer.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "provences")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Provence extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "provence_seq")
    @SequenceGenerator(name = "provence_seq", sequenceName = "provence_seq")
    private Long id;
    private String name;
    private String acronym;
    private String region;
    @OneToMany(mappedBy = "provence")
    private List<Municipality> municipalities;
}
