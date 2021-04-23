package rpp2021.ctrl;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import rpp2021.model.StavkaRacuna;
import rpp2021.repository.StavkaRacunaRepository;

@RestController
public class StavkaRacunRestController {

	@Autowired
	private StavkaRacunaRepository stavkaRacunaRepository;
	
	@GetMapping("stavkaRacuna")
		public Collection<StavkaRacuna> getAll(){
			return stavkaRacunaRepository.findAll();
		}
	
	@GetMapping("/stavkaRacuna/{id}")
		public StavkaRacuna getOne(@PathVariable("id") Integer id) {
			StavkaRacuna stavkaRacuna =	stavkaRacunaRepository.getOne(id);
			return stavkaRacuna;
	}
	
	@GetMapping("/stavkaRacuna/kolicina/{kolicina}")
	public Collection<StavkaRacuna> findByKolicina(@PathVariable("kolicina") Integer kolicina){
		return stavkaRacunaRepository.findByKolicina(kolicina);
	}
	
	@PostMapping("/stavkaRacuna")
	public ResponseEntity<StavkaRacuna> addOne(StavkaRacuna stavkaRacuna){
		StavkaRacuna savedStavkaRacuna = stavkaRacunaRepository.save(stavkaRacuna);
		URI location = URI.create("/stavkaRacuna/" + savedStavkaRacuna.getId());
		return ResponseEntity.created(location).body(savedStavkaRacuna);
	}
}
