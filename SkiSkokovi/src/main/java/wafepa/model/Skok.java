package wafepa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Skok {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private double daljina;
	@Column
	private double poeniDaljina;
	@Column
	private double ocenaSudija;
	@Column
	private double zbirPoena;
	@ManyToOne(fetch=FetchType.EAGER)
	private Takmicar takmicar;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getDaljina() {
		return daljina;
	}
	public void setDaljina(double daljina) {
		this.daljina = daljina;
	}
	public double getPoeniDaljina() {
		return poeniDaljina;
	}
	public void setPoeniDaljina(double poeniDaljina) {
		this.poeniDaljina = poeniDaljina;
	}
	public double getOcenaSudija() {
		return ocenaSudija;
	}
	public void setOcenaSudija(double ocenaSudija) {
		this.ocenaSudija = ocenaSudija;
	}
	public double getZbirPoena() {
		return zbirPoena;
	}
	public void setZbirPoena(double zbirPoena) {
		this.zbirPoena = zbirPoena;
	}
	public Takmicar getTakmicar() {
		return takmicar;
	}
	public void setTakmicar(Takmicar takmicar) {
		this.takmicar = takmicar;
	}
	
	
}
