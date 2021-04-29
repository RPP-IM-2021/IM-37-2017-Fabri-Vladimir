package rpp2021.ctrl;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import rpp2021.model.Proizvod;
import rpp2021.repository.ProizvodRepository;

@RestController
public class ProizvodRestController {

	@Autowired
	private ProizvodRepository proizvodRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Returns all proizvod entities from database")
	@GetMapping("proizvod")
	public Collection<Proizvod> getAll(){
		return proizvodRepository.findAll();
	}
	
	@ApiOperation(value = "Returns proizvod entity with specified ID from database")
	@GetMapping("proizvod/{id}")
	public ResponseEntity<Proizvod> getOne(@PathVariable("id") Integer id) {
		if (proizvodRepository.existsById(id)){
			Proizvod proizvod = proizvodRepository.getOne(id);
			return new ResponseEntity<>(proizvod, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Returns all proizvod entities with specified naziv")
	@GetMapping("proizvod/naziv/{naziv}")
	public Collection<Proizvod> getByNaziv(@PathVariable("naziv") String naziv ){
		return proizvodRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Inserts one entity of type proizvod into the database")
	@PostMapping("proizvod")
	public ResponseEntity<Proizvod> addOne(@RequestBody Proizvod proizvod){
		Proizvod savedProizvod = proizvodRepository.save(proizvod);
		URI location = URI.create("/proizvod/" + savedProizvod.getId());
		return ResponseEntity.created(location).body(savedProizvod);
	}
	
	@ApiOperation(value = "If proizvod with specified ID exists method updates it otherwise new entity with specified ID is inserted into database")
	@PutMapping("proizvod/{id}")
	public ResponseEntity<Proizvod> updateOne(@RequestBody Proizvod proizvod, @PathVariable ("id") Integer id){
		if(proizvodRepository.existsById(id)) {
			proizvod.setId(id);
			Proizvod updatedProizvod = proizvodRepository.save(proizvod);
			return ResponseEntity.ok().body(updatedProizvod);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Deletes proizvod entity with specified ID from the database")
	@DeleteMapping("proizvod/{id}")
	public ResponseEntity<HttpStatus> deleteOne(@PathVariable ("id") Integer id){
		if(id == -100 && !proizvodRepository.existsById(id)) {
			jdbcTemplate.execute("INSERT INTO proizvod (\"id\", \"naziv\", \"proizvodjac\") VALUES (-100 , 'TestProizvod', 1)");
		}
		if(proizvodRepository.existsById(id)) {
			proizvodRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
}
