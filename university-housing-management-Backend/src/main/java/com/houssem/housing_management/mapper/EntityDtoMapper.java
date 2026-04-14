package com.houssem.housing_management.mapper;


import com.houssem.housing_management.DTOs.*;
import com.houssem.housing_management.Entities.*;

import java.util.stream.Collectors;

public class EntityDtoMapper {

    // ---------- Universite ----------
    public static UniversiteDTO toDto(Universite universite) {
        if (universite == null) return null;
        return UniversiteDTO.builder()
                .idUniversite(universite.getIdUniversite())
                .nomUniversite(universite.getNomUniversite())
                .adresse(universite.getAdresse())
                .foyer(toDto(universite.getFoyer()))
                .build();
    }

    public static Universite toEntity(UniversiteDTO dto) {
        if (dto == null) return null;
        Universite universite = new Universite();
        universite.setIdUniversite(dto.getIdUniversite());
        universite.setNomUniversite(dto.getNomUniversite());
        universite.setAdresse(dto.getAdresse());
        universite.setFoyer(toEntity(dto.getFoyer()));
        return universite;
    }

    // ---------- Foyer ----------
    public static FoyerDTO toDto(Foyer foyer) {
        if (foyer == null) return null;
        return FoyerDTO.builder()
                .idFoyer(foyer.getIdFoyer())
                .nomFoyer(foyer.getNomFoyer())
                .capaciteFoyer(foyer.getCapaciteFoyer())
                .blocs(foyer.getBlocs() != null ?
                        foyer.getBlocs().stream().map(EntityDtoMapper::toDto).collect(Collectors.toList()) : null)
                .build();
    }

    public static Foyer toEntity(FoyerDTO dto) {
        if (dto == null) return null;
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(dto.getIdFoyer());
        foyer.setNomFoyer(dto.getNomFoyer());
        foyer.setCapaciteFoyer(dto.getCapaciteFoyer());
        if (dto.getBlocs() != null) {
            foyer.setBlocs(dto.getBlocs().stream()
                    .map(EntityDtoMapper::toEntity)
                    .collect(Collectors.toList()));
        }
        return foyer;
    }

    // ---------- Bloc ----------
    public static BlocDTO toDto(Bloc bloc) {
        if (bloc == null) return null;
        return BlocDTO.builder()
                .idBloc(bloc.getIdBloc())
                .nomBloc(bloc.getNomBloc())
                .capaciteBloc(bloc.getCapaciteBloc())
                .chambres(bloc.getChambres() != null ?
                        bloc.getChambres().stream().map(EntityDtoMapper::toDto).collect(Collectors.toList()) : null)
                .build();
    }

    public static Bloc toEntity(BlocDTO dto) {
        if (dto == null) return null;
        Bloc bloc = new Bloc();
        bloc.setIdBloc(dto.getIdBloc());
        bloc.setNomBloc(dto.getNomBloc());
        bloc.setCapaciteBloc(dto.getCapaciteBloc());
        if (dto.getChambres() != null) {
            bloc.setChambres(dto.getChambres().stream()
                    .map(EntityDtoMapper::toEntity)
                    .collect(Collectors.toList()));
        }
        return bloc;
    }

    // ---------- Chambre ----------
    public static ChambreDTO toDto(Chambre chambre) {
        if (chambre == null) return null;
        return ChambreDTO.builder()
                .idChambre(chambre.getIdChambre())
                .numeroChambre(chambre.getNumeroChambre())
                .typeC(chambre.getTypeC())
                .nomBloc(chambre.getBloc() != null ? chambre.getBloc().getNomBloc() : null)
                .build();
    }

    public static Chambre toEntity(ChambreDTO dto) {
        if (dto == null) return null;
        Chambre chambre = new Chambre();
        chambre.setIdChambre(dto.getIdChambre());
        chambre.setNumeroChambre(dto.getNumeroChambre());
        chambre.setTypeC(dto.getTypeC());
        return chambre;
    }

    // ---------- Etudiant ----------
    public static EtudiantDTO toDto(Etudiant etudiant) {
        if (etudiant == null) return null;
        return EtudiantDTO.builder()
                .idEtudiant(etudiant.getIdEtudiant())
                .nomEt(etudiant.getNomEt())
                .prenomEt(etudiant.getPrenomEt())
                .cin(etudiant.getCin())
                .ecole(etudiant.getEcole())
                .dateNaissance(etudiant.getDateNaissance())
                .build();
    }

    public static Etudiant toEntity(EtudiantDTO dto) {
        if (dto == null) return null;
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(dto.getIdEtudiant());
        etudiant.setNomEt(dto.getNomEt());
        etudiant.setPrenomEt(dto.getPrenomEt());
        etudiant.setCin(dto.getCin());
        etudiant.setEcole(dto.getEcole());
        etudiant.setDateNaissance(dto.getDateNaissance());
        return etudiant;
    }

    // ---------- Reservation ----------
    public static ReservationDTO toDto(Reservation reservation) {
        if (reservation == null) return null;
        return ReservationDTO.builder()
                .idReservation(reservation.getIdReservation())
                .anneeUniversitaireDebut(reservation.getAnneeUniversitaireDebut())
                .anneeUniversitaireFin(reservation.getAnneeUniversitaireFin())
                .dateReservation(reservation.getDateReservation())
                .estValide(reservation.getEstValide())
                .idChambre(reservation.getChambre() != null ? reservation.getChambre().getIdChambre() : null)
                .idEtudiant(reservation.getEtudiant() != null ? reservation.getEtudiant().getIdEtudiant() : null)
                .build();
    }

    public static Reservation toEntity(ReservationDTO dto) {
        if (dto == null) return null;
        Reservation reservation = new Reservation();
        reservation.setIdReservation(dto.getIdReservation());
        reservation.setAnneeUniversitaireDebut(dto.getAnneeUniversitaireDebut());
        reservation.setAnneeUniversitaireFin(dto.getAnneeUniversitaireFin());
        reservation.setDateReservation(dto.getDateReservation());
        reservation.setEstValide(dto.getEstValide());
        // Les associations Chambre et Etudiant doivent être settées séparément dans le service
        return reservation;
    }
}