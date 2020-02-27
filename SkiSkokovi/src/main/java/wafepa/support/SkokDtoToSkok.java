package wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import wafepa.dto.SkokDTO;
import wafepa.model.Skok;
import wafepa.model.Takmicar;
import wafepa.service.SkokService;
import wafepa.service.TakmicarService;

@Component
public class SkokDtoToSkok implements Converter<SkokDTO, Skok> {

	@Autowired
	SkokService skokSer;
	@Autowired
	TakmicarService takSer;
	
	@Override
	public Skok convert(SkokDTO dto) {
		
		Takmicar t = takSer.findOne(dto.getTakmicarId());
		
		if (t != null) {

			Skok s = null;

			if (dto.getId() != null) {
				s = skokSer.findOne(dto.getId());
				
				if (s == null) {
					throw new IllegalStateException("Tried to "
							+ "modify a non-existant entity");
				}
			} else {
				s = new Skok();
			}

			s.setId(dto.getId());
			s.setDaljina(dto.getDaljina());
			s.setOcenaSudija(dto.getOcenaSudija());
			s.setPoeniDaljina(dto.getPoeniDaljina());
			s.setZbirPoena(dto.getZbirPoena());
			s.setTakmicar(t);

			return s;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}
	
	public List<Skok> convert(List<SkokDTO> dtos){
		List<Skok> ret = new ArrayList<>();
		
		for(SkokDTO a : dtos){
			ret.add(convert(a));
		}
		
		return ret;
	}

}
