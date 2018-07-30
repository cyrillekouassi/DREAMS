package ci.jsi.entites.bareme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BaremeRepository extends JpaRepository<Bareme, Long> {

	@Query("select b from Bareme b where b.uid like :uid")
	public Bareme getOneBareme(@Param("uid")String uid);
}
