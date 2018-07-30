package ci.jsi.entites.utilisateur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserApp, Long> {

	@Query("select u from UserApp u where u.uid like :uid")
	public UserApp getOneUser(@Param("uid")String uid);
	
	
	@Query("select u from UserApp u where u.username like :username")
	public UserApp getOneUsername(@Param("username")String username);
	
	public UserApp findByUsername(String username);
}
