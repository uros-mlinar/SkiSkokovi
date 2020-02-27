package wafepa;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wafepa.model.Skakaonica;
import wafepa.model.Takmicar;
import wafepa.service.SkakaonicaService;
import wafepa.service.SkokService;
import wafepa.service.TakmicarService;

@Component
public class TestData {
	
	@Autowired
	SkokService skokSer;
	@Autowired
	SkakaonicaService skakSer;
	@Autowired
	TakmicarService takSer;
	
	@PostConstruct
	public void init() {
		
		Skakaonica s1 = new Skakaonica();
		s1.setNaziv("Satenbergsance");
		s1.setD(3.2);
		s1.setK(10);
		skakSer.save(s1);
		
		Skakaonica s2 = new Skakaonica();
		s2.setNaziv("Bergizelsance");
		s2.setD(2.2);
		s2.setK(8);
		skakSer.save(s2);
		
		Takmicar t1 = new Takmicar();
		t1.setIme("Hari Oli");
		t1.setDrzava("Finska");
		t1.setGodinaRodjenja(1987);
		t1.setVisina(192);
		t1.setEmail("hari@gmail.com");
		t1.setSkakaonica(s1);
		takSer.save(t1);
		
		Takmicar t2 = new Takmicar();
		t2.setIme("Martin Smit");
		t2.setDrzava("Nemacka");
		t2.setGodinaRodjenja(1989);
		t2.setVisina(193);
		t2.setEmail("smit@gmail.com");
		t2.setSkakaonica(s1);
		takSer.save(t2);
		
		Takmicar t3 = new Takmicar();
		t3.setIme("Sep Bradl");
		t3.setDrzava("Austrija");
		t3.setGodinaRodjenja(1987);
		t3.setVisina(187);
		t3.setEmail("bradl@gmail.com");
		t3.setSkakaonica(s2);
		takSer.save(t3);
	}
}
