package ci.jsi.entites.ensembleOption;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnsembleOptionRepository extends JpaRepository<EnsembleOption, Long> {

	@Query("select eo from EnsembleOption eo where eo.uid like :uid")
	public EnsembleOption getOneEnsembleOption(@Param("uid")String uid);
	
	@Query("select eo from EnsembleOption eo where eo.name like :name")
	public EnsembleOption getOneEnsembleOptionByName(@Param("name")String name);
}
