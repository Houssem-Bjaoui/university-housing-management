package com.houssem.housing_management.ServiceImpl;

import com.houssem.housing_management.DTOs.UniversiteDTO;


import java.util.List;

public interface IUniversiteService {
    UniversiteDTO addOrUpdateUniversite(UniversiteDTO universiteDTO);
    void deleteUniversite(Long id);
    UniversiteDTO getUniversiteById(Long id);
    List<UniversiteDTO> getAllUniversites();

    UniversiteDTO affecterFoyerAUniversite(long idFoyer, String nomUniversite);
    UniversiteDTO desaffecterFoyerAUniversite(long idUniversite);
}