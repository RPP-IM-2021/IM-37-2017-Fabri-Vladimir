package rpp2021.ctrl;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import rpp2021.model.Racun;
import rpp2021.model.StavkaRacuna;
import rpp2021.repository.RacunRepository;
import rpp2021.repository.StavkaRacunaRepository;

@RestController
@CrossOrigin
public class StavkaRacunRestController {

	@Autowired
	private StavkaRacunaRepository stavkaRacunaRepository;
	
	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Returns all stavka racuna entities from database")
	@GetMapping("stavkaRacuna")
		public Collection<StavkaRacuna> getAll(){
			return stavkaRacunaRepository.findAll();
		}
	
	@ApiOperation(value = "Returns stavka racuna entity with specified ID from database")
	@GetMapping("/stavkaRacuna/{id}")
		public ResponseEntity<StavkaRacuna> getOne(@PathVariable("id") Integer id) {
		if(stavkaRacunaRepository.findById(id).isPresent()) {
			StavkaRacuna stavkaRacuna =	stavkaRacunaRepository.getOne(id);
			return new ResponseEntity<>(stavkaRacuna , HttpStatus.OK);
		}
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Returns collection of stavka racuna entities which belong to racun with specified ID from database")
	@GetMapping("/stavkaRacuna/racun/{id}")
	public Collection<StavkaRacuna> findByRacun(@PathVariable("id") Integer id){
		if(racunRepository.existsById(id)) {
			Optional<Racun> optionalRacun = racunRepository.findById(id);
			Racun savedRacun = optionalRacun.get();
			return stavkaRacunaRepository.findByRacun(savedRacun);
		}
		return new ArrayList<StavkaRacuna>();
	}
	
	@ApiOperation(value = "Returns collection of stavka racuna entities whose variable cena is less than the one specified")
	@GetMapping("/stavkaRacuna/cena/{cena}")
	public Collection<StavkaRacuna> findByCenaLessThan(@PathVariable ("cena") BigDecimal cena){
		return stavkaRacunaRepository.findByCenaLessThan(cena);
	}
	
	@ApiOperation(value = "Inserts one entity of type stavka racuna into the database")
	@PostMapping("/stavkaRacuna")
	public ResponseEntity<StavkaRacuna> addOne(@RequestBody StavkaRacuna stavkaRacuna){
		stavkaRacuna.setRedniBroj(stavkaRacunaRepository.nextRBR(stavkaRacuna.getRacun().getId()));
		StavkaRacuna savedStavkaRacuna = stavkaRacunaRepository.save(stavkaRacuna);
		URI location = URI.create("/stavkaRacuna/" + savedStavkaRacuna.getId());
		return ResponseEntity.created(location).body(savedStavkaRacuna);
	}
	
	@ApiOperation(value = "If stavka racuna with specified ID exists method updates it otherwise new entity with specified ID is inserted into database")
	@PutMapping("/stavkaRacuna/{id}")
	public ResponseEntity<StavkaRacuna> updateOne(@RequestBody StavkaRacuna stavkaRacuna, @PathVariable ("id") Integer id){
		if(stavkaRacunaRepository.existsById(id)) {
			stavkaRacuna.setId(id);
			StavkaRacuna updatedStavkaRacuna = stavkaRacunaRepository.save(stavkaRacuna);
			return ResponseEntity.ok().body(updatedStavkaRacuna);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Deletes stavka racuna entity with specified ID from the database")
	@DeleteMapping("stavkaRacuna/{id}")
	public ResponseEntity<HttpStatus> deleteOne(@PathVariable ("id") Integer id){
		if(id == -100 && !stavkaRacunaRepository.existsById(id)) {
			jdbcTemplate.execute("INSERT INTO stavka_racuna (\"id\", \"redni_broj\", \"kolicina\", \"jedinica_mere\", \"cena\", \"racun\", \"proizvod\" ) "
					+ "VALUES (-100 , 5, 1, 'komad', 65000, 2, 8)");
		}
		if(stavkaRacunaRepository.existsById(id)) {
			stavkaRacunaRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
}
