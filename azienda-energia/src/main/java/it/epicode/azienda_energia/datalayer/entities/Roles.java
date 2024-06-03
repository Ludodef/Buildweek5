package it.epicode.azienda_energia.datalayer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Roles extends BaseEntity{

    @Column(length = 15, unique = true)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private final List<User> users = new ArrayList<>();
}
