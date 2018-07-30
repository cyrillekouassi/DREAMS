package ci.jsi.entites.element;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ElementRepository extends JpaRepository<Element, Long> {

	@Query("select e from Element e where e.uid like :uid")
	public Element getOneElement(@Param("uid")String uid);
	
	@Query("select e from Element e where e.name like :name")
	public Element getOneElementByName(@Param("name")String name);
	
	@Query("select e from Element e where e.code=:code")
	public Element getOneElementByCode(@Param("code")String code);
}
