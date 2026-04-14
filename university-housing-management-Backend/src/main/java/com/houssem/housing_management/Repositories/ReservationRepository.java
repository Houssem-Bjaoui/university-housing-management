package com.houssem.housing_management.Repositories;

import com.houssem.housing_management.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    Reservation findByEtudiantCinAndEstValideTrue(Long cin);
    List<Reservation> findByChambreIdChambreAndEstValideTrue(Long idChambre);
}