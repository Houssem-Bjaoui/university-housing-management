package com.houssem.housing_management.Controller;

import com.houssem.housing_management.DTOs.ChambreDTO;
import com.houssem.housing_management.Entities.Chambre;
import com.houssem.housing_management.Enum.TypeChambre;
import com.houssem.housing_management.ServiceImpl.IChambreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chambre")
@RequiredArgsConstructor
public class chambreController {

    private final IChambreService chambreService;

    @PostMapping("/add")
    public ChambreDTO addChambre(@RequestBody ChambreDTO chambreDTO) {
        return chambreService.addChambre(chambreDTO);
    }

    @PutMapping("/update")
    public ChambreDTO updateChambre(@RequestBody ChambreDTO chambreDTO) {
        return chambreService.updateChambre(chambreDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteChambre(@PathVariable Long id) {
        chambreService.deleteChambre(id);
    }

    @GetMapping("/{id}")
    public ChambreDTO getChambre(@PathVariable Long id) {
        return chambreService.getChambreById(id);
    }

    @GetMapping("/all")
    public List<ChambreDTO> getAllChambres() {
        return chambreService.getAllChambres();
    }

    @GetMapping("/byBloc/{nomBloc}")
    public List<ChambreDTO> getChambresParNomBloc(@PathVariable String nomBloc) {
        return chambreService.getChambresParNomBloc(nomBloc);
    }

    @GetMapping("/countByTypeAndBloc")
    public long nbChambreParTypeEtBloc(@RequestParam TypeChambre type,
                                       @RequestParam long idBloc) {
        return chambreService.nbChambreParTypeEtBloc(type, idBloc);
    }
}