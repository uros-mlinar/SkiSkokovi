package wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import wafepa.dto.SkakaonicaDTO;
import wafepa.model.Skakaonica;

@Component
public class SkakaonicaToSkakaonicaDTO 
				implements Converter<Skakaonica, SkakaonicaDTO>{

	@Override
	public SkakaonicaDTO convert(Skakaonica source) {
		
		if (source == null) {
			return null;
		}
		
		SkakaonicaDTO dto = new SkakaonicaDTO();
		
		dto.setId(source.getId());
		dto.setD(source.getD());
		dto.setK(source.getK());
		dto.setNaziv(source.getNaziv());
		
		return dto;
	}
	
	public List<SkakaonicaDTO> convert(List<Skakaonica> lista){
		List<SkakaonicaDTO> ret = new ArrayList<>();
		
		for(Skakaonica s : lista){
			ret.add(convert(s));
		}
		
		return ret;
	}

}
