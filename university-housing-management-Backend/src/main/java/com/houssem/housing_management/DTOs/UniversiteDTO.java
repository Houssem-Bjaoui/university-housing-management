package com.houssem.housing_management.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UniversiteDTO {
    private Long idUniversite;
    private String nomUniversite;
    private String adresse;
    private FoyerDTO foyer;
}