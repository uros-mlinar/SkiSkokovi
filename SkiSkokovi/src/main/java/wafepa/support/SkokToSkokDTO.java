package wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import wafepa.dto.SkokDTO;
import wafepa.model.Skok;

@Component
public class SkokToSkokDTO implements Converter<Skok, SkokDTO>{

	@Override
	public SkokDTO convert(Skok source) {
		
		if (source == null) {
			return null;
		}
		
		SkokDTO dto = new SkokDTO();
		dto.setId(source.getId());
		dto.setDaljina(source.getDaljina());
		dto.setOcenaSudija(source.getOcenaSudija());
		dto.setPoeniDaljina(source.getPoeniDaljina());
		dto.setZbirPoena(source.getZbirPoena());
		dto.setTakmicarId(source.getTakmicar().getId());
		dto.setTakmicarIme(source.getTakmicar().getIme());
		
		return dto;
	}

	public List<SkokDTO> convert(List<Skok> lista){
		List<SkokDTO> ret = new ArrayList<>();
		
		for(Skok s : lista){
			ret.add(convert(s));
		}
		
		return ret;
	}
}
