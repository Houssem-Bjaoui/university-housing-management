package com.houssem.housing_management.Services;

import com.houssem.housing_management.DTOs.ChambreDTO;
import com.houssem.housing_management.Entities.Bloc;
import com.houssem.housing_management.Entities.Chambre;
import com.houssem.housing_management.Enum.TypeChambre;
import com.houssem.housing_management.Repositories.BlocRepository;
import com.houssem.housing_management.Repositories.ChambreRepository;
import com.houssem.housing_management.ServiceImpl.IChambreService;
import com.houssem.housing_management.mapper.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChambreServiceImpl implements IChambreService {

    private final ChambreRepository chambreRepository;
    private final BlocRepository blocRepository;

    @Override
    public ChambreDTO addChambre(ChambreDTO chambreDTO) {
        Chambre chambre = EntityDtoMapper.toEntity(chambreDTO);
        // Si un bloc est spécifié via un autre mécanisme, il faut l'associer ici.
        Chambre saved = chambreRepository.save(chambre);
        return EntityDtoMapper.toDto(saved);
    }

    @Override
    public ChambreDTO updateChambre(ChambreDTO chambreDTO) {
        Chambre chambre = EntityDtoMapper.toEntity(chambreDTO);
        Chambre saved = chambreRepository.save(chambre);
        return EntityDtoMapper.toDto(saved);
    }

    @Override
    public void deleteChambre(Long id) {
        chambreRepository.deleteById(id);
    }

    @Override
    public ChambreDTO getChambreById(Long id) {
        Chambre chambre = chambreRepository.findById(id).orElse(null);
        return EntityDtoMapper.toDto(chambre);
    }

    @Override
    public List<ChambreDTO> getAllChambres() {
        return chambreRepository.findAll().stream()
                .map(EntityDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChambreDTO> getChambresParNomBloc(String nomBloc) {
        Bloc bloc = blocRepository.findByNomBloc(nomBloc);
        if (bloc == null) {
            throw new RuntimeException("Bloc non trouvé avec le nom : " + nomBloc);
        }
        return chambreRepository.findByBlocNomBloc(nomBloc).stream()
                .map(EntityDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public long nbChambreParTypeEtBloc(TypeChambre type, long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);
        if (bloc == null) {
            throw new RuntimeException("Bloc non trouvé avec l'ID : " + idBloc);
        }
        return chambreRepository.countByTypeCAndBlocIdBloc(type, idBloc);
    }
}