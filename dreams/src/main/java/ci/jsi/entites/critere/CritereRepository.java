package ci.jsi.entites.critere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CritereRepository extends JpaRepository<Critere, Long> {

	@Query("select c from Critere c where c.uid like :uid")
	public Critere getOneCritere(@Param("uid")String uid);
}
