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
public class BlocDTO {
    private Long idBloc;
    private String nomBloc;
    private Long capaciteBloc;
    private List<ChambreDTO> chambres;
}