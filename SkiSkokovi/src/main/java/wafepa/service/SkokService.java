package wafepa.service;

import java.util.List;

import wafepa.model.Skakaonica;
import wafepa.model.Skok;

public interface SkokService {
	
	Skok findOne(Long id);
	List<Skok> findAll();
	Skok save(Skok toSave);
	List<Skok> findByTakmicarId(Long id);
	Double izracunajOcenuSudija(List<Double> ocene);
	Double izracunajPoeneZaSkok(Skakaonica skakaonica, double daljina);
}
