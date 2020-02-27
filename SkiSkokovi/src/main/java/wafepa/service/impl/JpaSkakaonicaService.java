package wafepa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import wafepa.model.Skakaonica;
import wafepa.repository.SkakaonicaRepository;
import wafepa.service.SkakaonicaService;

@Service
@Transactional
public class JpaSkakaonicaService implements SkakaonicaService{

	@Autowired
	private SkakaonicaRepository skakRep;
	
	
	@Override
	public Skakaonica findOne(Long id) {
		return skakRep.findOne(id);
	}

	@Override
	public Page<Skakaonica> findAll(int pageNum) {
		return skakRep.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public List<Skakaonica> findAll() {
		return skakRep.findAll();
	}

	@Override
	public Skakaonica save(Skakaonica skakaonica) {
		return skakRep.save(skakaonica);
	}

	@Override
	public Skakaonica delete(Long id) {
		Skakaonica s = skakRep.findOne(id);
		if (s == null) {
			throw new IllegalArgumentException("Pokusaj brisanja "
					+ "nepostojeceg entiteta");
		}
		skakRep.delete(id);
		return s;
	}
	
//	@Override
//	public Page<Record> search( String activityName, Integer minDuration,
//								String intensity, int pageNum){
//		
//		if(activityName != null) {
//			activityName = '%' + activityName + '%';
//		}
//		if(intensity != null) {
//			intensity = '%' + intensity + '%';
//		}
//		
//		return recordRepository.search(activityName, minDuration, intensity, new PageRequest(pageNum, 5));
//	}
	
//	@Override
//	public Page<Automobil> pretraga(String model, Integer godisteOd, Double potrosnjaDo, int pageNum) {
//		if (model != null) {
//			model = "%" + model + "%";
//		}
//		return autoRep.pretraga(model, godisteOd, potrosnjaDo, new PageRequest(pageNum, 5));
//	}

//	@Override
//	public Page<Record> findByUserId(Long id, int pageNum) {
//		return recordRepository.findByUserId(id, new PageRequest(pageNum, 5));
//	}
}
