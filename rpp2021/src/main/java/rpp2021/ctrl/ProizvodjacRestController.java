package rpp2021.ctrl;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import rpp2021.model.Proizvodjac;
import rpp2021.repository.ProizvodjacRepository;

@RestController
public class ProizvodjacRestController {
	
	@Autowired
	private ProizvodjacRepository proizvodjacRepository;
	
	@GetMapping("proizvodjac")
	public Collection<Proizvodjac> getAll(){
		return proizvodjacRepository.findAll();
	}
	
	@GetMapping("/proizvodjac/{id}")
	public Proizvodjac getOne(@PathVariable("id") Integer id) {
		Proizvodjac proizvodjac = proizvodjacRepository.getOne(id);
		return proizvodjac;
	}
	
	@GetMapping("/proizvodjac/naziv/{naziv}")
	public Collection<Proizvodjac> findByNaziv(@PathVariable("naziv") String naziv){
		return proizvodjacRepository.findByNazivIgnoreCase(naziv);
	}
	
	@PostMapping("proizvodjac")
	public ResponseEntity<Proizvodjac> addOne(Proizvodjac proizvodjac){
		Proizvodjac savedProizvodjac = proizvodjacRepository.save(proizvodjac);
		URI location = URI.create("/proizvodjac/"+ savedProizvodjac.getId());
		return ResponseEntity.created(location).body(savedProizvodjac);
	}
}
