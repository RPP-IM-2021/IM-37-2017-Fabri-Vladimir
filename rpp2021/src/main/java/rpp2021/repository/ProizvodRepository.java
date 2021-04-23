package rpp2021.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp2021.model.Proizvod;

public interface ProizvodRepository extends JpaRepository <Proizvod, Integer> {

	Collection<Proizvod> findByNazivContainingIgnoreCase(String naziv);
}
