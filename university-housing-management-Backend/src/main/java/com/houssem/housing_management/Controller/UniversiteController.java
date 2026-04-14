package com.houssem.housing_management.Controller;

import com.houssem.housing_management.DTOs.UniversiteDTO;
import com.houssem.housing_management.ServiceImpl.IUniversiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/universite")
@RequiredArgsConstructor
public class UniversiteController {

    private final IUniversiteService universiteService;

    @PostMapping("/addOrUpdate")
    public UniversiteDTO addOrUpdate(@RequestBody UniversiteDTO universiteDTO) {
        return universiteService.addOrUpdateUniversite(universiteDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        universiteService.deleteUniversite(id);
    }

    @GetMapping("/{id}")
    public UniversiteDTO getById(@PathVariable Long id) {
        return universiteService.getUniversiteById(id);
    }

    @GetMapping("/all")
    public List<UniversiteDTO> getAll() {
        return universiteService.getAllUniversites();
    }

    @PutMapping("/affecterFoyer/{idFoyer}/{nomUniversite}")
    public UniversiteDTO affecterFoyer(@PathVariable long idFoyer, @PathVariable String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }

    @PutMapping("/desaffecterFoyer/{idUniversite}")
    public UniversiteDTO desaffecterFoyer(@PathVariable long idUniversite) {
        return universiteService.desaffecterFoyerAUniversite(idUniversite);
    }
}