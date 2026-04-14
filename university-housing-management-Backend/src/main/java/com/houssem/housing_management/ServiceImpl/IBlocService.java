package com.houssem.housing_management.ServiceImpl;

import com.houssem.housing_management.DTOs.BlocDTO;
import com.houssem.housing_management.Entities.Bloc;

import java.util.List;

public interface IBlocService {
    BlocDTO addBloc(BlocDTO blocDTO);
    BlocDTO updateBloc(BlocDTO blocDTO);
    void deleteBloc(Long id);
    BlocDTO getBlocById(Long id);
    List<BlocDTO> getAllBlocs();
    BlocDTO affecterChambresABloc(List<Long> numChambres, String nomBloc);
    BlocDTO affecterBlocAFoyer(String nomBloc, String nomFoyer);
}