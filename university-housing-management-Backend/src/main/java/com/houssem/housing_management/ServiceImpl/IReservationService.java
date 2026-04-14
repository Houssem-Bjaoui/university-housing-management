package com.houssem.housing_management.ServiceImpl;

import com.houssem.housing_management.DTOs.ReservationDTO;
import com.houssem.housing_management.Entities.Reservation;

import java.util.List;

public interface IReservationService {
    ReservationDTO ajouterReservationEtAssignerAChambreEtAEtudiant(Long numChambre, Long cin);
    ReservationDTO annulerReservation(Long cinEtudiant);
    List<ReservationDTO> getAllReservations();
    ReservationDTO getReservationById(String id);
}