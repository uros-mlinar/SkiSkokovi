package wafepa.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class SkakaonicaDTO {

	private Long id;                  //getteri i setteri,  @Validacije
	@NotEmpty
	private String naziv;
	@Min(0)
	private double k;
	@Min(0)
	@Max(5)
	private double d;
	
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


}
