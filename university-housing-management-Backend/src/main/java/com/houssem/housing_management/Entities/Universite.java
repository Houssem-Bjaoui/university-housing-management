package com.houssem.housing_management.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Universite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUniversite;

    private String nomUniversite;
    private String adresse;

    // Relation OneToOne (Universite est le parent)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Foyer foyer;
    }

