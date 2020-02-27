package wafepa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import wafepa.model.Takmicar;
import wafepa.repository.TakmicarRepository;
import wafepa.service.TakmicarService;

@Service
@Transactional
public class JpaTakmicarService implements TakmicarService{

	@Autowired
	private TakmicarRepository takRep;
	
	@Override
	public Takmicar findOne(Long id) {
		return takRep.findOne(id);
	}

	@Override
	public Page<Takmicar> findAll(int pageNum) {
		return takRep.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public List<Takmicar> findAll() {
		return takRep.findAll();
	}

	@Override
	public Takmicar save(Takmicar takmicar) {
		return takRep.save(takmicar);
	}

	@Override
	public Takmicar delete(Long id) {
		Takmicar takmicar = takRep.findOne(id);
		if (takmicar == null) {
			throw new IllegalArgumentException("Pokusaj brisanja "
					+ "nepostojeceg entiteta");
		}
		takRep.delete(id);
		return takmicar;
	}

	@Override
	public Page<Takmicar> search(Long skakaonicaId, String ime, String drzava, Integer godisteOd, Integer godisteDo,
			int pageNum) {
		if(ime != null) {
			ime = "%" + ime + "%";
		}
		if(drzava != null) {
			drzava = "%" + drzava + "%";
		}
		return takRep.search(skakaonicaId, ime, drzava, godisteOd, godisteDo, new PageRequest(pageNum, 5));
	}


//	@Override
//	public Page<Record> findByUserId(Long id, int pageNum) {
//		return recordRepository.findByUserId(id, new PageRequest(pageNum, 5));
//	}
}
