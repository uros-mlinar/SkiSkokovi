package wafepa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wafepa.dto.SkokDTO;
import wafepa.dto.TakmicarDTO;
import wafepa.model.Skok;
import wafepa.model.Takmicar;
import wafepa.service.SkokService;
import wafepa.service.TakmicarService;
import wafepa.support.SkokDtoToSkok;
import wafepa.support.SkokToSkokDTO;
import wafepa.support.TakmicarDtoToTakmicar;
import wafepa.support.TakmicarToTakmicarDTO;

@RestController
@RequestMapping(value = "/api/takmicari")
public class ApiTakmicarController {
	
	@Autowired
	private SkokService skokSer;

	@Autowired
	private TakmicarService takSer;

	@Autowired
	private TakmicarDtoToTakmicar toTakmicar;

	@Autowired
	private TakmicarToTakmicarDTO toDto;
	
	@Autowired
	private SkokToSkokDTO toSkokDto;
	@Autowired
	private SkokDtoToSkok toSkok;
	
	
//svi takmicari paginirano
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<TakmicarDTO>> getTakmicari(
			@RequestParam(required=false, value = "skakaonicaId") Long skakaonicaId,
			@RequestParam(required=false, value = "ime") String ime,
			@RequestParam(required = false, value = "drzava") String drzava,
			@RequestParam(required = false, value = "godisteOd") Integer godisteOd,
			@RequestParam(required = false, value = "godisteDo") Integer godisteDo,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {

		Page<Takmicar> stranica = null;
				
			if (skakaonicaId != null || ime != null || drzava != null || godisteOd != null || godisteDo != null) {
				stranica = takSer.search(skakaonicaId, ime, drzava, godisteOd, godisteDo, pageNum);
			} else {
				stranica = takSer.findAll(pageNum);
			}	

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(stranica.getTotalPages()));

		return new ResponseEntity<>(toDto.convert(stranica.getContent()), headers, HttpStatus.OK);
	}
//takmicar po ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<TakmicarDTO> getTakmicar(@PathVariable Long id) {
		Takmicar takmicar = takSer.findOne(id);
		if (takmicar == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDto.convert(takmicar), HttpStatus.OK);
	}
//skokovi po takmicarId	
	@RequestMapping(value= "/{id}/skokovi", method=RequestMethod.GET)
	ResponseEntity<List<SkokDTO>> getSkokoviByTakmicar(
			@PathVariable Long id, @RequestParam(defaultValue="0") int pageNum){
		
		List<Skok> skokovi = skokSer.findByTakmicarId(id);
		
		if(skokovi == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toSkokDto.convert(skokovi), HttpStatus.OK);
	}
	
//dodavanje takmicara	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<TakmicarDTO> add(@Validated @RequestBody TakmicarDTO dto) {
		
		Takmicar saved = takSer.save(toTakmicar.convert(dto));
		
		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
	}
//izmena takmicara	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<TakmicarDTO> edit(@Validated @RequestBody TakmicarDTO dto, @PathVariable Long id) {
		
		if (!id.equals(dto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Takmicar persisted = takSer.save(toTakmicar.convert(dto));
		
		return new ResponseEntity<>(toDto.convert(persisted), HttpStatus.OK);
	}
	
	
//brisanje takmicara
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<TakmicarDTO> delete(@PathVariable Long id) {
		Takmicar deleted = takSer.delete(id);

		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDto.convert(deleted), HttpStatus.OK);
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
