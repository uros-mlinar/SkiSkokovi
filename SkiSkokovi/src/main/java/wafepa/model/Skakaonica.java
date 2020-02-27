package wafepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Skakaonica {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true)
	private String naziv;
	@Column
	private double k;
	@Column
	private double d;
	@OneToMany(mappedBy = "skakaonica", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Takmicar> takmicari = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public List<Takmicar> getTakmicari() {
		return takmicari;
	}

	public void setTakmicari(List<Takmicar> takmicari) {
		this.takmicari = takmicari;
	}

	public double getK() {
		return k;
	}

	public void setK(double k) {
		this.k = k;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}
	
//	public void addRecord(Record record) {
//		if(record.getUser() != this) {
//			record.setUser(this);
//		}
//		records.add(record);
//	}
}
