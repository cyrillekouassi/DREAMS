package ci.jsi.entites.roleUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {

	@Query("select r from RoleUser r where r.uid like :uid")
	public RoleUser getOneRoleUser(@Param("uid")String uid);
	
	public RoleUser findByUid(String uid);
	
	public RoleUser findByName(String roleName);
}
