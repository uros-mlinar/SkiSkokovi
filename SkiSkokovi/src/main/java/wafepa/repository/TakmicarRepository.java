package wafepa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import wafepa.model.Takmicar;

@Repository
public interface TakmicarRepository extends JpaRepository<Takmicar, Long> {

	@Query("SELECT t FROM Takmicar t WHERE "
			+ "(:skakaonicaId IS NULL OR t.skakaonica.id = :skakaonicaId) AND "
			+ "(:ime IS NULL OR t.ime LIKE :ime) AND "
			+ "(:drzava IS NULL OR t.drzava LIKE :drzava) AND "
			+ "(:godisteOd IS NULL OR t.godinaRodjenja >= :godisteOd) AND "
			+ "(:godisteDo IS NULL OR t.godinaRodjenja <= :godisteDo)")
	Page<Takmicar> search(
			@Param("skakaonicaId") Long skakaonicaId, 
			@Param("ime") String ime, 
			@Param("drzava") String drzava, 
			@Param("godisteOd") Integer godisteOd, 
			@Param("godisteDo") Integer godisteDo,
			Pageable pageRequest);
	
//	List<Address> findByUserId(Long userId);
//	
//	List<Address> findByUser(User user);
	

//@Query("SELECT a from Automobil a WHERE "
//	+ "(:model IS NULL OR a.model LIKE :model) AND "
//	+ " (:godisteOd IS NULL OR a.godiste >= :godisteOd) AND "
//	+ " (:potrosnjaDo IS NULL OR a.potrosnja <= :potrosnjaDo)"
//		)
//Page<Automobil> search(@Param("model")String model, 
//	@Param("godisteOd")Integer godisteOd,
//@Param("potrosnjaDo")Double potrosnjaDo, 
//Pageable pageRequest);
}
