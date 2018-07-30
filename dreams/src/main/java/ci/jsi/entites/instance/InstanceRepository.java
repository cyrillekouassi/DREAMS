package ci.jsi.entites.instance;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InstanceRepository extends JpaRepository<Instance, Long> {

		
	@Query("select i from Instance i where i.deleted = false")
	public List<Instance> getAllInstance();
	
	@Query("select i from Instance i where i.deleted = false and i.uid like :uid")
	public Instance getOneInstance(@Param("uid")String uid);
	
	public Page<Instance> findAllByDeletedIsFalseAndProgrammeUidAndOrganisationUid(String programmeuid,String organisationuid,Pageable pageable);
	
	public List<Instance> findAllByDeletedIsFalseAndOrganisationUidInAndProgrammeUidAndDateActiviteGreaterThanEqualAndDateActiviteLessThanEqual(List<String> organisationUid,String programmeuid,Date debutActivte, Date finActivite);

	public List<Instance> findAllByDeletedIsFalseAndOrganisationUidInAndProgrammeUidAndDateActiviteLessThan(List<String> organisationUid,String programmeuid,Date finActivite);

}
