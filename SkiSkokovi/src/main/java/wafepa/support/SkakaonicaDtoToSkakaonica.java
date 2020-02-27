package wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import wafepa.dto.SkakaonicaDTO;
import wafepa.model.Skakaonica;
import wafepa.service.SkakaonicaService;

@Component
public class SkakaonicaDtoToSkakaonica implements Converter<SkakaonicaDTO, Skakaonica>{

	@Autowired
	private SkakaonicaService skakSer;
	
	
	@Override
	public Skakaonica convert(SkakaonicaDTO dto) {
		
		Skakaonica s = null;
		
		if (dto.getId() == null) {
			s = new Skakaonica();
		}else {
			s = skakSer.findOne(dto.getId());
			if (s == null) {
				throw new IllegalStateException("Pokusaj konvertovanja "
						+ "nepostojeceg entiteta");
			}
		}
		
		s.setId(dto.getId());
		s.setNaziv(dto.getNaziv());
		s.setD(dto.getD());
		s.setK(dto.getK());
		
		return s;
	}
	
	public List<Skakaonica> convert(List<SkakaonicaDTO> dtos){
		
		List<Skakaonica> ret = new ArrayList<>();
		
		for (SkakaonicaDTO dto : dtos) {
			ret.add(convert(dto));
		}
		
		return ret;
	}

}
