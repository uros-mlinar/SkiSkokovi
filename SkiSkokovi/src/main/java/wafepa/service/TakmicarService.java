package wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import wafepa.model.Takmicar;

public interface TakmicarService {

	Takmicar findOne(Long id);
	
	Page<Takmicar> findAll(int pageNum);
	List<Takmicar> findAll();
	
	Takmicar save(Takmicar modelVise);

    Takmicar delete(Long id);

	Page<Takmicar> search(
			@Param("skakaonicaId") Long skakaonicaId, 
			@Param("ime") String ime, 
			@Param("drzava") String drzava, 
			@Param("godisteOd") Integer godisteOd, 
			@Param("godisteDo") Integer godisteDo,
			int pageNum);
    
	
    
//    List<Zadatak> findBySprintId(Long sprintID);	
//    Page<Record> findByUserId(Long id, int pageNum);
//    List<Address> findByUser(Long userId);
}
