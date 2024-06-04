package it.epicode.azienda_energia.datalayer.entities.adresses;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String name;
    @ManyToOne()
    @JsonManagedReference
    private Province province;
    @OneToMany(mappedBy = "municipality")
    @JsonBackReference
    private List<Address> addresses;
}
