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
public class User extends BaseEntity{
    @Column(length = 125, unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String username;
    @Column(length = 125, nullable = false)
    private String email;
    @Column(length = 125, nullable = false, unique = true)
    private String password;
    @Column(length = 25, nullable = false)
    private String firstName;
    @Column(length = 25, nullable = false)
    private String lastName;
    @Column(length = 25, nullable = false)
    private String avatar;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private final List<Roles> roles = new ArrayList<>();
}
