package it.epicode.azienda_energia.datalayer.entities.adresses;

import it.epicode.azienda_energia.datalayer.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "municipalities")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Municipality extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "municipality_seq")
    @SequenceGenerator(name = "municipality_seq", sequenceName = "municipality_seq")
    private Long id;
    private String name;
    @ManyToOne
    private Provence provence;
    @OneToMany(mappedBy = "municipality")
    private List<Address> addresses;
}
