package wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;

import wafepa.model.Skakaonica;

public interface SkakaonicaService {
	
	Skakaonica findOne(Long id);
	
	Page<Skakaonica>  findAll(int pageNum);
	List<Skakaonica> findAll();
	
	Skakaonica save(Skakaonica modelJedan);
	
//	List<ModelJedan> save(List<ModelJedan> modeli);
	
	Skakaonica delete(Long id);
	
//	Page<Record> search(
//	@Param("activityName") String activityName, 
//	@Param("minDuration") Integer minDuration, 
//	@Param("intensity") String intensity,
//	 int pageNum);

//  Page<Automobil> pretraga(@Param("model")String model,
//       @Param("godisteOd")Integer godisteOd,
//  @Param("potrosnjaDo")Double potrosnjaDo, 
//  int pageNum);

//  List<Zadatak> findBySprintId(Long sprintID);	
//  Page<Record> findByUserId(Long id, int pageNum);
//  List<Address> findByUser(Long userId);
}
