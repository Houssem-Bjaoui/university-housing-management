package com.houssem.housing_management.Services;

import com.houssem.housing_management.DTOs.ReservationDTO;
import com.houssem.housing_management.Entities.Bloc;
import com.houssem.housing_management.Entities.Chambre;
import com.houssem.housing_management.Entities.Etudiant;
import com.houssem.housing_management.Entities.Reservation;
import com.houssem.housing_management.Repositories.ChambreRepository;
import com.houssem.housing_management.Repositories.EtudiantRepository;
import com.houssem.housing_management.Repositories.ReservationRepository;
import com.houssem.housing_management.ServiceImpl.IReservationService;
import com.houssem.housing_management.mapper.EntityDtoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final ChambreRepository chambreRepository;
    private final EtudiantRepository etudiantRepository;

    @Override
    @Transactional
    public ReservationDTO ajouterReservationEtAssignerAChambreEtAEtudiant(Long numChambre, Long cin) {
        Chambre chambre = chambreRepository.findByNumeroChambre(numChambre);
        Etudiant etudiant = etudiantRepository.findByCin(cin);
        if (chambre == null || etudiant == null) {
            throw new RuntimeException("Chambre ou étudiant introuvable");
        }

        // Vérifier la capacité
        int capaciteMax = switch (chambre.getTypeC()) {
            case SIMPLE -> 1;
            case DOUBLE -> 2;
            case TRIPLE -> 3;
        };
        List<Reservation> valides = reservationRepository.findByChambreIdChambreAndEstValideTrue(chambre.getIdChambre());
        if (valides.size() >= capaciteMax) {
            throw new RuntimeException("Capacité maximale atteinte pour cette chambre");
        }

        // Année universitaire dynamique
        LocalDate now = LocalDate.now();
        int year = now.getYear() % 100;
        LocalDate debut, fin;
        String anneeStr;
        if (now.getMonthValue() <= 7) {
            debut = LocalDate.of(Integer.parseInt("20" + (year - 1)), 9, 15);
            fin = LocalDate.of(Integer.parseInt("20" + year), 6, 30);
            anneeStr = "20" + (year - 1) + "/20" + year;
        } else {
            debut = LocalDate.of(Integer.parseInt("20" + year), 9, 15);
            fin = LocalDate.of(Integer.parseInt("20" + (year + 1)), 6, 30);
            anneeStr = "20" + year + "/20" + (year + 1);
        }

        Bloc bloc = chambre.getBloc();
        String idReservation = anneeStr + "-" + bloc.getNomBloc() + "-" + chambre.getNumeroChambre() + "-" + cin;

        Reservation reservation = Reservation.builder()
                .idReservation(idReservation)
                .anneeUniversitaireDebut(debut)
                .anneeUniversitaireFin(fin)
                .dateReservation(LocalDate.now())
                .estValide(true)
                .chambre(chambre)
                .etudiant(etudiant)
                .build();

        Reservation saved = reservationRepository.save(reservation);
        return EntityDtoMapper.toDto(saved);
    }

    @Override
    @Transactional
    public ReservationDTO annulerReservation(Long cinEtudiant) {
        Reservation reservation = reservationRepository.findByEtudiantCinAndEstValideTrue(cinEtudiant);
        if (reservation == null) {
            throw new RuntimeException("Aucune réservation active trouvée pour cet étudiant");
        }
        reservationRepository.delete(reservation);
        return EntityDtoMapper.toDto(reservation);
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(EntityDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO getReservationById(String id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        return EntityDtoMapper.toDto(reservation);
    }
}