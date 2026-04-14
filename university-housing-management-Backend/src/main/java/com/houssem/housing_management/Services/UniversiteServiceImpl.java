package com.houssem.housing_management.Services;

import com.houssem.housing_management.DTOs.UniversiteDTO;
import com.houssem.housing_management.Entities.Foyer;
import com.houssem.housing_management.Entities.Universite;
import com.houssem.housing_management.Repositories.FoyerRepository;
import com.houssem.housing_management.Repositories.UniversiteRepository;
import com.houssem.housing_management.ServiceImpl.IUniversiteService;
import com.houssem.housing_management.mapper.EntityDtoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversiteServiceImpl implements IUniversiteService {

    private final UniversiteRepository universiteRepository;
    private final FoyerRepository foyerRepository;

    @Override
    @Transactional
    public UniversiteDTO addOrUpdateUniversite(UniversiteDTO universiteDTO) {
        Universite universite = EntityDtoMapper.toEntity(universiteDTO);
        Universite saved = universiteRepository.save(universite);
        return EntityDtoMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteUniversite(Long id) {
        universiteRepository.deleteById(id);
    }

    @Override
    public UniversiteDTO getUniversiteById(Long id) {
        Universite universite = universiteRepository.findById(id).orElse(null);
        return EntityDtoMapper.toDto(universite);
    }

    @Override
    public List<UniversiteDTO> getAllUniversites() {
        return universiteRepository.findAll().stream()
                .map(EntityDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UniversiteDTO affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RuntimeException("Foyer non trouvé"));
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);
        if (universite == null) throw new RuntimeException("Université non trouvée");
        if (universite.getFoyer() != null) throw new RuntimeException("L'université a déjà un foyer");

        universite.setFoyer(foyer);
        foyer.setUniversite(universite);
        Universite saved = universiteRepository.save(universite);
        return EntityDtoMapper.toDto(saved);
    }

    @Override
    @Transactional
    public UniversiteDTO desaffecterFoyerAUniversite(long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new RuntimeException("Université non trouvée"));
        Foyer foyer = universite.getFoyer();
        if (foyer != null) {
            foyer.setUniversite(null);
            universite.setFoyer(null);
            foyerRepository.save(foyer);
        }
        Universite saved = universiteRepository.save(universite);
        return EntityDtoMapper.toDto(saved);
    }
}
