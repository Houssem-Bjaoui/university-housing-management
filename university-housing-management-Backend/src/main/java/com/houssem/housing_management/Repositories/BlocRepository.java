package com.houssem.housing_management.Repositories;

import com.houssem.housing_management.Entities.Bloc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Long> {
    Bloc findByNomBloc(String nomBloc);
}
