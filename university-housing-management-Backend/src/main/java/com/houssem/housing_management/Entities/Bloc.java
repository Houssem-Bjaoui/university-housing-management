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
public class Bloc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloc;

    private String nomBloc;
    private Long capaciteBloc;

    // Relation ManyToOne vers Foyer
    @ManyToOne
    @JoinColumn(name = "foyer_id")
    private Foyer foyer;

    // Relation OneToMany avec Chambre (Bloc est parent)
    @OneToMany(mappedBy = "bloc", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Chambre> chambres = new ArrayList<>();
}
