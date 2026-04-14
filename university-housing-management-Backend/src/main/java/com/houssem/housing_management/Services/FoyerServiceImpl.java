package com.houssem.housing_management.Services;

import com.houssem.housing_management.DTOs.FoyerDTO;
import com.houssem.housing_management.Entities.Foyer;
import com.houssem.housing_management.Entities.Universite;
import com.houssem.housing_management.Repositories.FoyerRepository;
import com.houssem.housing_management.Repositories.UniversiteRepository;
import com.houssem.housing_management.ServiceImpl.IFoyerService;
import com.houssem.housing_management.mapper.EntityDtoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoyerServiceImpl implements IFoyerService {

    private final FoyerRepository foyerRepository;
    private final UniversiteRepository universiteRepository;

    @Override
    @Transactional
    public FoyerDTO addFoyer(FoyerDTO foyerDTO) {
        Foyer foyer = EntityDtoMapper.toEntity(foyerDTO);
        Foyer saved = foyerRepository.save(foyer);
        return EntityDtoMapper.toDto(saved);
    }

    @Override
    @Transactional
    public FoyerDTO updateFoyer(FoyerDTO foyerDTO) {
        Foyer foyer = EntityDtoMapper.toEntity(foyerDTO);
        Foyer saved = foyerRepository.save(foyer);
        return EntityDtoMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteFoyer(Long id) {
        foyerRepository.deleteById(id);
    }

    @Override
    public FoyerDTO getFoyerById(Long id) {
        Foyer foyer = foyerRepository.findById(id).orElse(null);
        return EntityDtoMapper.toDto(foyer);
    }

    @Override
    public List<FoyerDTO> getAllFoyers() {
        return foyerRepository.findAll().stream()
                .map(EntityDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FoyerDTO ajouterFoyerEtAffecterAUniversite(FoyerDTO foyerDTO, long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new RuntimeException("Université non trouvée"));
        if (universite.getFoyer() != null) {
            throw new RuntimeException("L'université a déjà un foyer");
        }
        Foyer foyer = EntityDtoMapper.toEntity(foyerDTO);
        foyer.setUniversite(universite);
        universite.setFoyer(foyer);
        Foyer saved = foyerRepository.save(foyer);
        universiteRepository.save(universite);
        return EntityDtoMapper.toDto(saved);
    }
}