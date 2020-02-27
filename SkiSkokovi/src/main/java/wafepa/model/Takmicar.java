package wafepa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Takmicar {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String ime;
	@Column
	private String drzava;
	@Column
	private int visina;
	@Column(unique = true)
	private String email;
	@Column
	private int godinaRodjenja;	
	@ManyToOne(fetch=FetchType.EAGER)
	private Skakaonica skakaonica;
	@OneToMany(mappedBy = "takmicar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Skok> skokovi;
	
	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getDrzava() {
		return drzava;
	}


	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}


	public int getVisina() {
		return visina;
	}


	public void setVisina(int visina) {
		this.visina = visina;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getGodinaRodjenja() {
		return godinaRodjenja;
	}


	public void setGodinaRodjenja(int godinaRodjenja) {
		this.godinaRodjenja = godinaRodjenja;
	}


	public Skakaonica getSkakaonica() {
		return skakaonica;
	}


	public void setSkakaonica(Skakaonica skakaonica) {
		this.skakaonica = skakaonica;
	}


	public List<Skok> getSkokovi() {
		return skokovi;
	}


	public void setSkokovi(List<Skok> skokovi) {
		this.skokovi = skokovi;
	}

	

	

//	public void setUser(User user) {
//		this.user = user;
//		if(!user.getAddresses().contains(this)){
//			user.getAddresses().add(this);
//		}
//	}
	
	
	
}
