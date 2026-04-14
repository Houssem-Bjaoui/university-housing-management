package com.houssem.housing_management.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation implements Serializable {
    @Id
    private String idReservation; // Format "2023/2024-Bloc A-1-123456789"

    private LocalDate anneeUniversitaireDebut;
    private LocalDate anneeUniversitaireFin;
    private LocalDate dateReservation;
    private Boolean estValide;

    // Relation ManyToOne vers Chambre
    @ManyToOne
    @JoinColumn(name = "chambre_id")
    private Chambre chambre;

    // Relation ManyToOne vers Etudiant (Reservation est parent)
    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;
}