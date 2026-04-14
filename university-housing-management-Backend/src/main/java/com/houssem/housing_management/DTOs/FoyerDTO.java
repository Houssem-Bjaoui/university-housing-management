package com.houssem.housing_management.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoyerDTO {
    private Long idFoyer;
    private String nomFoyer;
    private Long capaciteFoyer;
    private List<BlocDTO> blocs;
}