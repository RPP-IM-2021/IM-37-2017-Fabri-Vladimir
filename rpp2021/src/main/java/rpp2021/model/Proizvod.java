package rpp2021.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the proizvod database table.
 * 
 */
@Entity
@NamedQuery(name="Proizvod.findAll", query="SELECT p FROM Proizvod p")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Proizvod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROIZVOD_ID_GENERATOR", sequenceName="PROIZVOD_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROIZVOD_ID_GENERATOR")
	private Integer id;

	private String naziv;

	//bi-directional many-to-one association to Proizvodjac
	@ManyToOne
	@JoinColumn(name="proizvodjac")
	private Proizvodjac proizvodjac;
	

	//bi-directional many-to-one association to StavkaRacuna
	@OneToMany(mappedBy="proizvod")
	@JsonIgnore
	private List<StavkaRacuna> stavkaRacuna;

	public Proizvod() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Proizvodjac getProizvodjac() {
		return this.proizvodjac;
	}

	public void setProizvodjac(Proizvodjac proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public List<StavkaRacuna> getStavkaRacuna() {
		return this.stavkaRacuna;
	}

	public void setStavkaRacunas(List<StavkaRacuna> stavkaRacuna) {
		this.stavkaRacuna = stavkaRacuna;
	}

	public StavkaRacuna addStavkaRacuna(StavkaRacuna stavkaRacuna) {
		getStavkaRacuna().add(stavkaRacuna);
		stavkaRacuna.setProizvod(this);

		return stavkaRacuna;
	}

	public StavkaRacuna removeStavkaRacuna(StavkaRacuna stavkaRacuna) {
		getStavkaRacuna().remove(stavkaRacuna);
		stavkaRacuna.setProizvod(null);

		return stavkaRacuna;
	}

}