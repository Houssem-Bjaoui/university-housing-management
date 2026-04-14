package com.houssem.housing_management.Entities;

import com.houssem.housing_management.Enum.TypeChambre;
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
public class Chambre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChambre;

    private Long numeroChambre;

    @Enumerated(EnumType.STRING)
    private TypeChambre typeC;

    // Relation ManyToOne vers Bloc
    @ManyToOne
    @JoinColumn(name = "bloc_id")
    private Bloc bloc;

    // Relation OneToMany avec Reservation
    @OneToMany(mappedBy = "chambre", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Reservation> reservations = new ArrayList<>();
}