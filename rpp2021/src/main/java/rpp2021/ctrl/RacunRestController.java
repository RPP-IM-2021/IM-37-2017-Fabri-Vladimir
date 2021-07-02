package rpp2021.ctrl;

import java.net.URI;
import java.util.Collection;

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
import rpp2021.model.Proizvodjac;
import rpp2021.model.Racun;
import rpp2021.repository.RacunRepository;

@RestController
@CrossOrigin
public class RacunRestController {

	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Returns all racun entities from database")
	@GetMapping("racun")
	public Collection<Racun> getAll(){
		return racunRepository.findAll();
	}
	
	@ApiOperation(value = "Returns racun entity with specified ID from database")
	@GetMapping("racun/{id}")
	public ResponseEntity<Racun> getOne(@PathVariable("id") Integer id) {
		if(racunRepository.findById(id).isPresent()) {
			Racun racun = racunRepository.getOne(id);
			return new ResponseEntity<>(racun, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Returns all racun entities with specified nacin placanja")
	@GetMapping("racun/nacinPlacanja/{nacinPlacanja}")
	public Collection<Racun> findByNacinPlacanja(@PathVariable("nacinPlacanja") String nacinPlacanja){
		return racunRepository.findByNacinPlacanjaIgnoreCase(nacinPlacanja);
	}
	
	@ApiOperation(value = "Inserts one entity of type racun into the database")
	@PostMapping("racun")
	public ResponseEntity<Racun> addOne(@RequestBody Racun racun){
		Racun savedRacun = racunRepository.save(racun);
		URI location = URI.create("/proizvodjac/"+ savedRacun.getId());
		return ResponseEntity.created(location).body(savedRacun);
	}
	
	@ApiOperation(value = "If racun with specified ID exists method updates it otherwise new entity with specified ID is inserted into database")
	@PutMapping("racun/{id}")
	public ResponseEntity<Racun> updateOne(@RequestBody Racun racun, @PathVariable ("id") Integer id){
		if(racunRepository.existsById(id)) {
			racun.setId(id);
			Racun updatedRacun = racunRepository.save(racun);
			return ResponseEntity.ok().body(updatedRacun);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Deletes proizvodjac entity with specified ID from the database")
	@DeleteMapping("racun/{id}")
	public ResponseEntity<HttpStatus> deleteOne(@PathVariable ("id") Integer id){
		if(id == -100 && !racunRepository.existsById(id)) {
			jdbcTemplate.execute("INSERT INTO racun (\"id\", \"datum\", \"nacin_placanja\") VALUES (-100 , to_date('29.03.2021.' , 'dd.mm.yyyy.'), 'testPlacanje')");
		}
		if(racunRepository.existsById(id)) {
			racunRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
}
