package com.houssem.housing_management.Controller;

import com.houssem.housing_management.DTOs.BlocDTO;
import com.houssem.housing_management.ServiceImpl.IBlocService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bloc")
@RequiredArgsConstructor
public class BlocController{

    private final IBlocService blocService;

    @PostMapping("/add")
    public BlocDTO add(@RequestBody BlocDTO blocDTO) {
        return blocService.addBloc(blocDTO);
    }

    @PutMapping("/update")
    public BlocDTO update(@RequestBody BlocDTO blocDTO) {
        return blocService.updateBloc(blocDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        blocService.deleteBloc(id);
    }

    @GetMapping("/{id}")
    public BlocDTO getById(@PathVariable Long id) {
        return blocService.getBlocById(id);
    }

    @GetMapping("/all")
    public List<BlocDTO> getAll() {
        return blocService.getAllBlocs();
    }

    @PutMapping("/affecterChambres/{nomBloc}")
    public BlocDTO affecterChambres(@RequestBody List<Long> numChambres, @PathVariable String nomBloc) {
        return blocService.affecterChambresABloc(numChambres, nomBloc);
    }

    @PutMapping("/affecterBlocAFoyer/{nomBloc}/{nomFoyer}")
    public BlocDTO affecterBlocAFoyer(@PathVariable String nomBloc, @PathVariable String nomFoyer) {
        return blocService.affecterBlocAFoyer(nomBloc, nomFoyer);
    }
}