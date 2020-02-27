package wafepa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import wafepa.dto.SkakaonicaDTO;
import wafepa.model.Skakaonica;
import wafepa.service.SkakaonicaService;
import wafepa.service.TakmicarService;
import wafepa.support.SkakaonicaDtoToSkakaonica;
import wafepa.support.SkakaonicaToSkakaonicaDTO;

@Controller
@RequestMapping(value = "/api/skakaonice")
public class ApiSkakaonicaController {

	@Autowired
	private SkakaonicaService skakSer;

	@Autowired
	private TakmicarService modelViseService;

	@Autowired
	private SkakaonicaDtoToSkakaonica toSkakaonica;

	@Autowired
	private SkakaonicaToSkakaonicaDTO toDto;


	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<SkakaonicaDTO>> getSkakaonice(){
		
		List<Skakaonica> skakaonice = skakSer.findAll();
		
		return new ResponseEntity<>(toDto.convert(skakaonice), HttpStatus.OK);
	}
	
	
//	SA PAGINACIJOM I PRETRAGOM
//	@RequestMapping(method = RequestMethod.GET)
//	ResponseEntity<List<ModelJedanDTO>> getMENJAJ(
//			@RequestParam(required=false, value = "imeZadatka") String imeZadatka,
//			@RequestParam(required=false, value = "sprintID") Long sprintID,
//			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {
//
//		Page<ModelJedan> stranica = null;
//				
//			if (imeZadatka != null || sprintID != null) {
//				stranica = modelJedanService.pretraga(imeZadatka, sprintID, pageNum);
//			} else {
//				stranica = modelJedanService.findAll(pageNum);
//			}	
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("totalPages", Integer.toString(stranica.getTotalPages()));
//
//		return new ResponseEntity<>(toDto.convert(stranica.getContent()), headers, HttpStatus.OK);
//	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<SkakaonicaDTO> getSkakaonica(@PathVariable Long id) {
		Skakaonica model = skakSer.findOne(id);
		if (model == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDto.convert(model), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<SkakaonicaDTO> delete(@PathVariable Long id) {
		Skakaonica deleted = skakSer.delete(id);

		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDto.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<SkakaonicaDTO> add(@Validated @RequestBody SkakaonicaDTO dto) {

		Skakaonica saved = skakSer.save(toSkakaonica.convert(dto));

		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
	}

	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<SkakaonicaDTO> edit(@Validated @RequestBody SkakaonicaDTO dto, @PathVariable Long id) {

		if (!id.equals(dto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Skakaonica persisted = skakSer.save(toSkakaonica.convert(dto));

		return new ResponseEntity<>(toDto.convert(persisted), HttpStatus.OK);
	}
	
//	JEDAN PRIMER SA DODATNOM PROMENLJIVOM
//	(value="/api/users")
//	@RequestMapping(value= "/{id}/MENJAJ", method=RequestMethod.GET)
//	ResponseEntity<List<ModelViseDTO>> getUsersRecords(
//			@PathVariable Long id, @RequestParam(defaultValue="0") int pageNum){
//		
//		Page<Record> stranica = recordService.findByModelJedanId(id, pageNum);
//		
//		if(stranica == null || stranica.getContent().isEmpty()){
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("totalPages", Integer.toString(stranica.getTotalPages()) );
//		
//		return new ResponseEntity<>(
//				toModelViseDto.convert(stranica.getContent()),
//				headers,
//				HttpStatus.OK);
//	}
	
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
