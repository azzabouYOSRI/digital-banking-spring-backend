package org.sid.ebankingbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Column(name = "username")
    private String username;

     @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled = false;

    @OneToMany(mappedBy = "users", orphanRemoval = true)
    private List<Authorities> authoritieses = new ArrayList<>();

}
