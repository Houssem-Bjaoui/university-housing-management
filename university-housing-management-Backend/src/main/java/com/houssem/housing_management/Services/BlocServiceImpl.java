package com.houssem.housing_management.Services;

import com.houssem.housing_management.DTOs.BlocDTO;
import com.houssem.housing_management.Entities.Bloc;
import com.houssem.housing_management.Entities.Chambre;
import com.houssem.housing_management.Entities.Foyer;
import com.houssem.housing_management.Repositories.BlocRepository;
import com.houssem.housing_management.Repositories.ChambreRepository;
import com.houssem.housing_management.Repositories.FoyerRepository;
import com.houssem.housing_management.ServiceImpl.IBlocService;
import com.houssem.housing_management.mapper.EntityDtoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlocServiceImpl implements IBlocService {

    private final BlocRepository blocRepository;
    private final ChambreRepository chambreRepository;
    private final FoyerRepository foyerRepository;

    @Override
    @Transactional
    public BlocDTO addBloc(BlocDTO blocDTO) {
        Bloc bloc = EntityDtoMapper.toEntity(blocDTO);
        Bloc saved = blocRepository.save(bloc);
        return EntityDtoMapper.toDto(saved);
    }

    @Override
    @Transactional
    public BlocDTO updateBloc(BlocDTO blocDTO) {
        Bloc bloc = EntityDtoMapper.toEntity(blocDTO);
        Bloc saved = blocRepository.save(bloc);
        return EntityDtoMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteBloc(Long id) {
        blocRepository.deleteById(id);
    }

    @Override
    public BlocDTO getBlocById(Long id) {
        Bloc bloc = blocRepository.findById(id).orElse(null);
        return EntityDtoMapper.toDto(bloc);
    }

    @Override
    public List<BlocDTO> getAllBlocs() {
        return blocRepository.findAll().stream()
                .map(EntityDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BlocDTO affecterChambresABloc(List<Long> numChambres, String nomBloc) {
        Bloc bloc = blocRepository.findByNomBloc(nomBloc);
        if (bloc == null) throw new RuntimeException("Bloc non trouvé");
        List<Chambre> chambres = new ArrayList<>();
        for (Long num : numChambres) {
            Chambre chambre = chambreRepository.findByNumeroChambre(num);
            if (chambre == null) throw new RuntimeException("Chambre " + num + " non trouvée");
            chambre.setBloc(bloc);
            chambres.add(chambre);
        }
        chambreRepository.saveAll(chambres);
        bloc.getChambres().addAll(chambres);
        Bloc saved = blocRepository.save(bloc);
        return EntityDtoMapper.toDto(saved);
    }

    @Override
    @Transactional
    public BlocDTO affecterBlocAFoyer(String nomBloc, String nomFoyer) {
        Bloc bloc = blocRepository.findByNomBloc(nomBloc);
        Foyer foyer = foyerRepository.findByNomFoyer(nomFoyer);
        if (bloc == null || foyer == null) throw new RuntimeException("Bloc ou Foyer non trouvé");
        bloc.setFoyer(foyer);
        Bloc saved = blocRepository.save(bloc);
        return EntityDtoMapper.toDto(saved);
    }
}