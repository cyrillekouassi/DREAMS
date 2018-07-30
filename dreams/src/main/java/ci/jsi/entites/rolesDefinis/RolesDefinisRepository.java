package ci.jsi.entites.rolesDefinis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolesDefinisRepository extends JpaRepository<RolesDefinis, Long> {
	@Query("select rd from RolesDefinis rd where rd.uid like :uid")
	public RolesDefinis getOneRolesDefinis(@Param("uid")String uid);
	
	public RolesDefinis findByAutorisation(String autorisation);
}
