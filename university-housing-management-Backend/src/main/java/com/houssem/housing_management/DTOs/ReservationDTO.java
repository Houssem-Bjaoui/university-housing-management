package com.houssem.housing_management.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {
    private String idReservation;
    private LocalDate anneeUniversitaireDebut;
    private LocalDate anneeUniversitaireFin;
    private LocalDate dateReservation;
    private Boolean estValide;
    private Long idChambre;
    private Long idEtudiant;
}