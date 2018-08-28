package ci.jsi.entites.option;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ci.jsi.entites.ensembleOption.EnsembleOption;

public interface OptionRepository extends JpaRepository<Option, Long> {

	@Query("select op from Option op where op.uid like :uid")
	public Option getOneOption(@Param("uid")String uid);
	
	@Query("select op from Option op where op.name like :name")
	public Option getOneOptionByName(@Param("name")String name);
	
	@Query("select op from Option op where op.name=:name and op.ensembleOption=:ensembleoption")
	public Option getOneOptionNameEnsemble(@Param("name")String name,@Param("ensembleoption")EnsembleOption ensembleoption);
	
	@Query("select op from Option op where op.code=:code and op.ensembleOption=:ensembleoption")
	public Option getOneOptionCodeEnsemble(@Param("code")String code,@Param("ensembleoption")EnsembleOption ensembleoption);
	
	public List<Option> findAllByEnsembleOptionUid(String ensembleUid);
}
