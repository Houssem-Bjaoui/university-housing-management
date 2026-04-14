package com.houssem.housing_management.DTOs;

import com.houssem.housing_management.Enum.TypeChambre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChambreDTO {
    private Long idChambre;
    private Long numeroChambre;
    private TypeChambre typeC;
    private String nomBloc; // Nom du bloc auquel la chambre est affectée
    // Pas de liste de réservations dans le DTO pour éviter la surcharge
}