package ci.jsi.entites.ensembleCritere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnsembleCritereRepository extends JpaRepository<EnsembleCritere, Long> {

	@Query("select ec from EnsembleCritere ec where ec.uid like :uid")
	public EnsembleCritere getOneEnsembleCritere(@Param("uid")String uid);
}
