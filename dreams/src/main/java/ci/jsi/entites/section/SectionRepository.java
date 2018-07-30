package ci.jsi.entites.section;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SectionRepository extends JpaRepository<Section, Long> {

	@Query("select s from Section s where s.uid like :uid")
	public Section getOneSection(@Param("uid")String uid);
}
