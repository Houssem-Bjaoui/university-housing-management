package com.houssem.housing_management.ServiceImpl;

import com.houssem.housing_management.DTOs.FoyerDTO;
import com.houssem.housing_management.Entities.Foyer;

import java.util.List;

public interface IFoyerService {
    FoyerDTO addFoyer(FoyerDTO foyerDTO);
    FoyerDTO updateFoyer(FoyerDTO foyerDTO);
    void deleteFoyer(Long id);
    FoyerDTO getFoyerById(Long id);
    List<FoyerDTO> getAllFoyers();
    FoyerDTO ajouterFoyerEtAffecterAUniversite(FoyerDTO foyerDTO, long idUniversite);
}