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
import rpp2021.model.Racun;
import rpp2021.repository.RacunRepository;

@RestController
public class RacunRestController {

	@Autowired
	private RacunRepository racunRepository;
	
	@GetMapping("racun")
	public Collection<Racun> getAll(){
		return racunRepository.findAll();
	}
	
	@GetMapping("racun/{id}")
	public Racun getOne(@PathVariable("id") Integer id) {
		Racun racun = racunRepository.getOne(id);
		return racun;
	}
	
	@GetMapping("racun/nacinPlacanja/{nacinPlacanja}")
	public Collection<Racun> findByNacinPlacanja(@PathVariable("nacinPlacanja") String nacinPlacanja){
		return racunRepository.findByNacinPlacanjaIgnoreCase(nacinPlacanja);
	}
	
	@PostMapping("racun")
	public ResponseEntity<Racun> addOne(Racun racun){
		Racun savedRacun = racunRepository.save(racun);
		URI location = URI.create("/proizvodjac/"+ savedRacun.getId());
		return ResponseEntity.created(location).body(savedRacun);
	}
}
