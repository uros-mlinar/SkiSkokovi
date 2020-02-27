package wafepa.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class TakmicarDTO {

	private Long id;                  //getteri i setteri,  @Validacije
	@NotEmpty
	private String ime;
	@Size(min = 2, max = 35)
	private String drzava;
	private int visina;
	@Email
	private String email;
	private int godinaRodjenja;
	private Long skakaonicaId;
	private String skakaonicaNaziv;
	
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

	public Long getSkakaonicaId() {
		return skakaonicaId;
	}

	public void setSkakaonicaId(Long skakaonicaId) {
		this.skakaonicaId = skakaonicaId;
	}

	public String getSkakaonicaNaziv() {
		return skakaonicaNaziv;
	}

	public void setSkakaonicaNaziv(String skakaonicaNaziv) {
		this.skakaonicaNaziv = skakaonicaNaziv;
	}
	
	
}
