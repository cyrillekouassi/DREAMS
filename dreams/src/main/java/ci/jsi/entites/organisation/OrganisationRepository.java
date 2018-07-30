package ci.jsi.entites.organisation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

	@Query("select o from Organisation o where o.uid like :uid")
	public Organisation getOneOrganisation(@Param("uid")String uid);
	
	@Query("select o from Organisation o where o.code like :code")
	public Organisation getOneOrganisationByCode(@Param("code")String code);
	
	public List<Organisation> findAllByLevel(int level);
	public List<Organisation> findByLevel(int level);
}
