package wafepa.dto;

public class SkokDTO {

	private Long id;
	private double daljina;
	private double poeniDaljina;
	private double ocenaSudija;
	private double zbirPoena;
	private Long takmicarId;
	private String takmicarIme;
	
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
	public Long getTakmicarId() {
		return takmicarId;
	}
	public void setTakmicarId(Long takmicarId) {
		this.takmicarId = takmicarId;
	}
	public String getTakmicarIme() {
		return takmicarIme;
	}
	public void setTakmicarIme(String takmicarIme) {
		this.takmicarIme = takmicarIme;
	}
	
}
