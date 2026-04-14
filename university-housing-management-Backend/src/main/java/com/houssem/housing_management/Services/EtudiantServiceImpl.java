package com.houssem.housing_management.Services;

import com.houssem.housing_management.DTOs.EtudiantDTO;
import com.houssem.housing_management.Entities.Etudiant;
import com.houssem.housing_management.Repositories.EtudiantRepository;
import com.houssem.housing_management.ServiceImpl.IEtudiantService;
import com.houssem.housing_management.mapper.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {

    private final EtudiantRepository etudiantRepository;

    @Override
    public EtudiantDTO addEtudiant(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = EntityDtoMapper.toEntity(etudiantDTO);
        Etudiant saved = etudiantRepository.save(etudiant);
        return EntityDtoMapper.toDto(saved);
    }

    @Override
    public EtudiantDTO updateEtudiant(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = EntityDtoMapper.toEntity(etudiantDTO);
        Etudiant saved = etudiantRepository.save(etudiant);
        return EntityDtoMapper.toDto(saved);
    }

    @Override
    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    @Override
    public EtudiantDTO getEtudiantById(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        return EntityDtoMapper.toDto(etudiant);
    }

    @Override
    public List<EtudiantDTO> getAllEtudiants() {
        return etudiantRepository.findAll().stream()
                .map(EntityDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EtudiantDTO getEtudiantByCin(Long cin) {
        Etudiant etudiant = etudiantRepository.findByCin(cin);
        return EntityDtoMapper.toDto(etudiant);
    }
}