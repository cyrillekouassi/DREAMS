package ci.jsi.entites.programme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProgrammeRepository extends JpaRepository<Programme, Long> {

	@Query("select p from Programme p where p.uid like :uid")
	public Programme getOneProgramme(@Param("uid")String uid);
	
	public Programme findByCode(String code);
	
	public Programme findByName(String name);
}
