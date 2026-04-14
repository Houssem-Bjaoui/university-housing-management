package com.houssem.housing_management.ServiceImpl;

import com.houssem.housing_management.DTOs.EtudiantDTO;

import java.util.List;

public interface IEtudiantService {
    EtudiantDTO addEtudiant(EtudiantDTO etudiantDTO);
    EtudiantDTO updateEtudiant(EtudiantDTO etudiantDTO);
    void deleteEtudiant(Long id);
    EtudiantDTO getEtudiantById(Long id);
    List<EtudiantDTO> getAllEtudiants();
    EtudiantDTO getEtudiantByCin(Long cin);
}