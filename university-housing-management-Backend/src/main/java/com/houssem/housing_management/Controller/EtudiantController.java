package com.houssem.housing_management.Controller;

import com.houssem.housing_management.DTOs.EtudiantDTO;
import com.houssem.housing_management.ServiceImpl.IEtudiantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiant")
@RequiredArgsConstructor
public class EtudiantController {

    private final IEtudiantService etudiantService;

    @PostMapping("/add")
    public EtudiantDTO add(@RequestBody EtudiantDTO etudiantDTO) {
        return etudiantService.addEtudiant(etudiantDTO);
    }

    @PutMapping("/update")
    public EtudiantDTO update(@RequestBody EtudiantDTO etudiantDTO) {
        return etudiantService.updateEtudiant(etudiantDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
    }

    @GetMapping("/{id}")
    public EtudiantDTO getById(@PathVariable Long id) {
        return etudiantService.getEtudiantById(id);
    }

    @GetMapping("/all")
    public List<EtudiantDTO> getAll() {
        return etudiantService.getAllEtudiants();
    }

    @GetMapping("/byCin/{cin}")
    public EtudiantDTO getByCin(@PathVariable Long cin) {
        return etudiantService.getEtudiantByCin(cin);
    }
}