package wafepa.service.impl;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import wafepa.model.Skakaonica;
import wafepa.model.Skok;
import wafepa.repository.SkokRepository;
import wafepa.service.SkokService;

@Service
@Transactional
public class JpaSkokService implements SkokService {

	@Autowired
	SkokRepository skokRep;
	
	@Override
	public Skok findOne(Long id) {
		return skokRep.findOne(id);
	}

	@Override
	public List<Skok> findAll() {
		return skokRep.findAll();
	}

	@Override
	public Skok save(Skok toSave) {
		return skokRep.save(toSave);
	}

	@Override
	public List<Skok> findByTakmicarId(Long id) {
		return skokRep.findByTakmicarId(id);
	}

	@Override
	public Double izracunajOcenuSudija(List<Double> ocene) {
		Double konacnaOcena = null;
		
		Double min = Collections.min(ocene);
		Double max = Collections.max(ocene);
		
		Double total = 0d;
		
		for (Double ocena : ocene) {
		if(ocena != max && ocena != min) {
			total = total + ocena;
		}
	}
		
		konacnaOcena = total / 3;
		
		return konacnaOcena;
	}
	
	@Override
	public Double izracunajPoeneZaSkok(Skakaonica skakaonica, double daljina) {
		double k = skakaonica.getK();
		double d = skakaonica.getD();
		
		double razlika = daljina - k;
		
		double poeniDaljina = 60d + razlika * d; 
		return poeniDaljina;
	}

}
