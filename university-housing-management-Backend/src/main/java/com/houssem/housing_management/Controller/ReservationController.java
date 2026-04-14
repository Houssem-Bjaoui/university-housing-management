package com.houssem.housing_management.Controller;

import com.houssem.housing_management.DTOs.ReservationDTO;

import com.houssem.housing_management.ServiceImpl.IReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;

    @PostMapping("/ajouter")
    public ReservationDTO ajouter(@RequestParam Long numChambre, @RequestParam Long cin) {
        return reservationService.ajouterReservationEtAssignerAChambreEtAEtudiant(numChambre, cin);
    }

    @DeleteMapping("/annuler")
    public String annuler(@RequestParam Long cinEtudiant) {
        ReservationDTO dto = reservationService.annulerReservation(cinEtudiant);
        return "La réservation " + dto.getIdReservation() + " est annulée avec succès";
    }

    @GetMapping("/all")
    public List<ReservationDTO> getAll() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ReservationDTO getById(@PathVariable String id) {
        return reservationService.getReservationById(id);
    }
}