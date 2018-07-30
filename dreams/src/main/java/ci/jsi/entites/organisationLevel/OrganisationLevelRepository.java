package ci.jsi.entites.organisationLevel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrganisationLevelRepository extends JpaRepository<OrganisationLevel, Long> {

	@Query("select ol from OrganisationLevel ol where ol.level like :level")
	public OrganisationLevel getOneOrganisationLevel(@Param("level")int level);
	
	@Query("select ol from OrganisationLevel ol where ol.uid like :uid")
	public OrganisationLevel getOrganisationLevel(@Param("uid")String uid);
}
