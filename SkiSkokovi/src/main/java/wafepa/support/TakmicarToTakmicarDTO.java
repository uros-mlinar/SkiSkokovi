package wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import wafepa.dto.TakmicarDTO;
import wafepa.model.Takmicar;

@Component
public class TakmicarToTakmicarDTO implements Converter<Takmicar, TakmicarDTO>{

	@Override
	public TakmicarDTO convert(Takmicar source) {
		
		if (source == null) {
			return null;
		}
		
		TakmicarDTO dto = new TakmicarDTO();
		
		dto.setId(source.getId());
		dto.setIme(source.getIme());
		dto.setDrzava(source.getDrzava());
		dto.setVisina(source.getVisina());
		dto.setEmail(source.getEmail());
		dto.setGodinaRodjenja(source.getGodinaRodjenja());
		dto.setSkakaonicaId(source.getSkakaonica().getId());
		dto.setSkakaonicaNaziv(source.getSkakaonica().getNaziv());

		return dto;
	}
	
	public List<TakmicarDTO> convert(List<Takmicar> lista){
		List<TakmicarDTO> ret = new ArrayList<>();
		
		for(Takmicar k : lista){
			ret.add(convert(k));
		}
		
		return ret;
	}
}
