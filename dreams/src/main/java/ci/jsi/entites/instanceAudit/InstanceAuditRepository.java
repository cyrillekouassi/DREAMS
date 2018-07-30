package ci.jsi.entites.instanceAudit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstanceAuditRepository extends JpaRepository<InstanceAudit, Long> {

	/*
	 * @Query("select i from instanceaudit i where i.uid like :uid") public
	 * InstanceAudit chercherUid (String uid);
	 */

}
