package rpp2021.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp2021.model.StavkaRacuna;

public interface StavkaRacunaRepository extends JpaRepository <StavkaRacuna , Integer> {

	Collection<StavkaRacuna> findByKolicina(Integer kolicina);
}
