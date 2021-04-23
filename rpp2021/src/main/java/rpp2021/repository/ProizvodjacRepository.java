package rpp2021.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp2021.model.Proizvodjac;

public interface ProizvodjacRepository extends JpaRepository <Proizvodjac, Integer>{

	Collection<Proizvodjac> findByNazivIgnoreCase(String naziv);
}
