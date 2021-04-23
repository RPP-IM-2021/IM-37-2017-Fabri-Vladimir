package rpp2021.ctrl;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import rpp2021.model.Proizvod;
import rpp2021.repository.ProizvodRepository;

@RestController
public class ProizvodRestController {

	@Autowired
	private ProizvodRepository proizvodRepository;
	
	@GetMapping("proizvod")
	public Collection<Proizvod> getAll(){
		return proizvodRepository.findAll();
	}
	
	@GetMapping("proizvod/{id}")
	public Proizvod getOne(@PathVariable("id") Integer id) {
		Proizvod proizvod = proizvodRepository.getOne(id);
		return proizvod;
	}
	
	@GetMapping("proizvod/naziv/{naziv}")
	public Collection<Proizvod> getByNaziv(@PathVariable("naziv") String naziv ){
		return proizvodRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@PostMapping("proizvod")
	public ResponseEntity<Proizvod> addOne(Proizvod proizvod){
		Proizvod savedProizvod = proizvodRepository.save(proizvod);
		URI location = URI.create("/proizvod/" + savedProizvod.getId());
		return ResponseEntity.created(location).body(savedProizvod);
	}
}
