package com.houssem.housing_management.Controller;

import com.houssem.housing_management.DTOs.FoyerDTO;
import com.houssem.housing_management.Entities.Foyer;
import com.houssem.housing_management.ServiceImpl.IFoyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foyer")
@RequiredArgsConstructor
public class FoyerController {

    private final IFoyerService foyerService;

    @PostMapping("/add")
    public FoyerDTO add(@RequestBody FoyerDTO foyerDTO) {
        return foyerService.addFoyer(foyerDTO);
    }

    @PutMapping("/update")
    public FoyerDTO update(@RequestBody FoyerDTO foyerDTO) {
        return foyerService.updateFoyer(foyerDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        foyerService.deleteFoyer(id);
    }

    @GetMapping("/{id}")
    public FoyerDTO getById(@PathVariable Long id) {
        return foyerService.getFoyerById(id);
    }

    @GetMapping("/all")
    public List<FoyerDTO> getAll() {
        return foyerService.getAllFoyers();
    }

    @PostMapping("/ajouterFoyerEtAffecterAUniversite/{idUniversite}")
    public FoyerDTO ajouterEtAffecter(@RequestBody FoyerDTO foyerDTO, @PathVariable long idUniversite) {
        return foyerService.ajouterFoyerEtAffecterAUniversite(foyerDTO, idUniversite);
    }
}