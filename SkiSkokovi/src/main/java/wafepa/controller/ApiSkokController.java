package wafepa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import wafepa.dto.SkokDTO;
import wafepa.model.Skakaonica;
import wafepa.model.Skok;
import wafepa.service.SkakaonicaService;
import wafepa.service.SkokService;
import wafepa.support.SkokDtoToSkok;
import wafepa.support.SkokToSkokDTO;

@Controller
@RequestMapping(value = "/api/skokovi")
public class ApiSkokController {

	@Autowired
	private SkokService skokSer;
	@Autowired
	private SkakaonicaService skakSer;
	
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
	ResponseEntity<Skok> addSkok(
			@RequestParam(value="ocene") List<Double> ocene, 
			@RequestParam(value = "daljina") double daljina,
			@RequestParam(value = "skakaonicaId") Long skakaonicaId){
		
		Skakaonica skakaonica = skakSer.findOne(skakaonicaId);
		Skok skok = new Skok();
		skok.setDaljina(daljina);
		skok.setOcenaSudija(skokSer.izracunajOcenuSudija(ocene));
		skok.setPoeniDaljina(skokSer.izracunajPoeneZaSkok(skakaonica, daljina));		
		
		return new ResponseEntity<>(skokSer.save(skok), HttpStatus.OK);
	}
}
