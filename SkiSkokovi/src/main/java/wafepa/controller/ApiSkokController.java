package wafepa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wafepa.dto.SkokDTO;
import wafepa.model.Skakaonica;
import wafepa.model.Skok;
import wafepa.model.Takmicar;
import wafepa.service.SkakaonicaService;
import wafepa.service.SkokService;
import wafepa.service.TakmicarService;
import wafepa.support.SkokDtoToSkok;
import wafepa.support.SkokToSkokDTO;

@RestController
@RequestMapping(value = "/api/skokovi")
public class ApiSkokController {

	@Autowired
	private SkokService skokSer;
	@Autowired
	private SkakaonicaService skakSer;
	@Autowired
	TakmicarService takSer;
	
	@Autowired
	private SkokToSkokDTO toDto;
	@Autowired
	private SkokDtoToSkok toSkok;
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<SkokDTO>> getSkokovi(){
		
		List<Skok> skokovi = skokSer.findAll();
		
		return new ResponseEntity<>(toDto.convert(skokovi), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<SkokDTO> addSkok(
			@RequestBody SkokDTO dto){
		
		Takmicar takmicar = takSer.findOne(dto.getId());
		Skakaonica skakaonica = skakSer.findOne(dto.getSkakaonicaId());
		
		if (takmicar == null || skakaonica == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Skok skok = new Skok();
		
		List<Double> ocene = new ArrayList<>();
		ocene.add(dto.getOcena1());
		ocene.add(dto.getOcena2());
		ocene.add(dto.getOcena3());
		ocene.add(dto.getOcena4());
		ocene.add(dto.getOcena5());
		
		double ocenaSudija = skokSer.izracunajOcenuSudija(ocene);
		double poeniDaljina = skokSer.izracunajPoeneZaSkok(skakaonica, dto.getDaljina());
		
		System.out.println("Sudije: " + ocenaSudija);
		System.out.println("Daljina: " + poeniDaljina);
		
		skok.setTakmicar(takmicar);
		skok.setDaljina(dto.getDaljina());
		skok.setOcenaSudija(ocenaSudija);
		skok.setPoeniDaljina(poeniDaljina);		
		skok.setZbirPoena(ocenaSudija + poeniDaljina);
		
		return new ResponseEntity<>(toDto.convert(skokSer.save(skok)), HttpStatus.OK);
	}
}
