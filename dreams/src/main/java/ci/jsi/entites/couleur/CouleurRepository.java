package ci.jsi.entites.couleur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouleurRepository extends JpaRepository<Couleur, Long>{

	@Query("select c from Couleur c where c.uid like :uid")
	public Couleur getOneCouleur(@Param("uid")String uid);
}
