package wafepa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wafepa.model.Skok;

@Repository
public interface SkokRepository extends JpaRepository<Skok, Long>{

	List<Skok> findByTakmicarId(Long id);

}
