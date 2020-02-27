package wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;

import wafepa.model.Skok;
import wafepa.model.Takmicar;

public interface SkokService {
	
	Skok findOne(Long id);
	List<Skok> findAll();
	Skok save(Skok toSave);
	List<Skok> findByTakmicarId(Long id);
}
