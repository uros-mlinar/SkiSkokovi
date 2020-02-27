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
		
		double total = 0;
		double max = 0;
		double min = 0;
		
		for (Double ocena : ocene) {
			if(ocena > max) max = ocena;
			if(ocena < min) min = ocena;
		}
		
		for (Double ocena : ocene) {
			if(ocena != max && ocena != min) {
				total = total + ocena;
			}
		}
		double ocenaSudija = total/3;
		skok.setOcenaSudija(ocenaSudija);
		
		double k = skakaonica.getK();
		double d = skakaonica.getD();
		
		double razlika = daljina - k;
		
		double poeniDaljina = 60d + razlika * d; 
		skok.setPoeniDaljina(poeniDaljina);
		skok.setZbirPoena(ocenaSudija + poeniDaljina);
		
		
		return new ResponseEntity<>(skokSer.save(skok), HttpStatus.OK);
	}
}
