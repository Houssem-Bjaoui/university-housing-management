package com.houssem.housing_management.ServiceImpl;

import com.houssem.housing_management.DTOs.ChambreDTO;

import com.houssem.housing_management.Enum.TypeChambre;

import java.util.List;

public interface IChambreService {
    ChambreDTO addChambre(ChambreDTO chambreDTO);
    ChambreDTO updateChambre(ChambreDTO chambreDTO);
    void deleteChambre(Long id);
    ChambreDTO getChambreById(Long id);
    List<ChambreDTO> getAllChambres();

    // Service 06
    List<ChambreDTO> getChambresParNomBloc(String nomBloc);

    // Service 07
    long nbChambreParTypeEtBloc(TypeChambre type, long idBloc);
}