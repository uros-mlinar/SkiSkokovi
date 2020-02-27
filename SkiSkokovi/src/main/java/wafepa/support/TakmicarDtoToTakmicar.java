package wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import wafepa.dto.TakmicarDTO;
import wafepa.model.Skakaonica;
import wafepa.model.Takmicar;
import wafepa.service.SkakaonicaService;
import wafepa.service.TakmicarService;

@Component
public class TakmicarDtoToTakmicar implements Converter<TakmicarDTO, Takmicar> {

	@Autowired
	private TakmicarService takSer;

	@Autowired
	private SkakaonicaService skakSer;

	@Override
	public Takmicar convert(TakmicarDTO dto) {

		Skakaonica s = skakSer.findOne(dto.getSkakaonicaId());


		if (s != null) {

			Takmicar t = null;

			if (dto.getId() != null) {
				t = takSer.findOne(dto.getId());
				
				if (t == null) {
					throw new IllegalStateException("Tried to "
							+ "modify a non-existant entity");
				}
			} else {
				t = new Takmicar();
			}

			t.setId(dto.getId());
			t.setIme(dto.getIme());
			t.setDrzava(dto.getDrzava());
			t.setVisina(dto.getVisina());
			t.setEmail(dto.getEmail());
			t.setGodinaRodjenja(dto.getGodinaRodjenja());
			t.setSkakaonica(s);

			return t;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}
	
	
	public List<Takmicar> convert(List<TakmicarDTO> dtos){
		List<Takmicar> ret = new ArrayList<>();
		
		for(TakmicarDTO a : dtos){
			ret.add(convert(a));
		}
		
		return ret;
	}

}
