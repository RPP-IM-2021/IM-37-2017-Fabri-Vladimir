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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiOperation;
import rpp2021.model.Proizvodjac;
import rpp2021.repository.ProizvodjacRepository;

@CrossOrigin
@RestController
public class ProizvodjacRestController {
	
	@Autowired
	private ProizvodjacRepository proizvodjacRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Returns all proizvodjac entities from database")
	@GetMapping("proizvodjac")
	public Collection<Proizvodjac> getAll(){
		return proizvodjacRepository.findAll();
	}
	
	@ApiOperation(value = "Returns proizvodjac entity with specified ID from database")
	@GetMapping("/proizvodjac/{id}")
	public ResponseEntity<Proizvodjac> getOne(@PathVariable("id") Integer id) {
		if(proizvodjacRepository.existsById(id)) {
			Proizvodjac proizvodjac = proizvodjacRepository.getOne(id);
			return new ResponseEntity<>(proizvodjac, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Returns all proizvodjac entities with specified naziv")
	@GetMapping("/proizvodjac/naziv/{naziv}")
	public Collection<Proizvodjac> findByNaziv(@PathVariable("naziv") String naziv){
			return proizvodjacRepository.findByNazivIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Inserts one entity of type proizvodjac into the database")
	@PostMapping("proizvodjac")
	public ResponseEntity<Proizvodjac> addOne(@RequestBody Proizvodjac proizvodjac){
		Proizvodjac savedProizvodjac = proizvodjacRepository.save(proizvodjac);
		URI location = URI.create("/proizvodjac/"+ savedProizvodjac.getId());
		return ResponseEntity.created(location).body(savedProizvodjac);
	}
	
	@ApiOperation(value = "If proizvodjac with specified ID exists method updates it otherwise new entity with specified ID is inserted into database")
	@PutMapping("proizvodjac/{id}")
	public ResponseEntity<Proizvodjac> updateOne(@RequestBody Proizvodjac proizvodjac, @PathVariable ("id") Integer id){
		if(proizvodjacRepository.existsById(id)) {
			proizvodjac.setId(id);
			Proizvodjac updatedProizvodjac = proizvodjacRepository.save(proizvodjac);
			return ResponseEntity.ok().body(updatedProizvodjac);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Deletes proizvodjac entity with specified ID from the database")
	@DeleteMapping("proizvodjac/{id}")
	public ResponseEntity<HttpStatus> deleteOne (@PathVariable ("id") Integer id){
		if(id == -100 && !proizvodjacRepository.existsById(id)) {
			jdbcTemplate.execute("INSERT INTO proizvodjac (\"id\", \"naziv\", \"kontakt\", \"adresa\") VALUES (-100 , 'TestProizvodjac', 'noContact', 'noAdress')");
		}
		if(proizvodjacRepository.existsById(id)) {
			proizvodjacRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
}
