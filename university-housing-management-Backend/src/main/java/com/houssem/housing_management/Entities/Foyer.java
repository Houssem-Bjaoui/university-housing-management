package com.houssem.housing_management.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Foyer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoyer;

    private String nomFoyer;
    private Long capaciteFoyer;

    // Relation OneToOne inverse (mappedBy n'est pas nécessaire ici car Universite est propriétaire)
    // Mais pour bidirectionnalité, on peut l'ajouter
    @OneToOne(mappedBy = "foyer")
    private Universite universite;

    // Relation OneToMany avec Bloc
    @OneToMany(mappedBy = "foyer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Bloc> blocs = new ArrayList<>();
}