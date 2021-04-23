package rpp2021.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp2021.model.Racun;

public interface RacunRepository extends JpaRepository <Racun , Integer> {

	Collection<Racun> findByNacinPlacanjaIgnoreCase(String nacinPlacanja);
}
