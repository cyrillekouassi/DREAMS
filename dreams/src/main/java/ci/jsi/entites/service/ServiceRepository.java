package ci.jsi.entites.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends JpaRepository<Servicess, Long> {

	@Query("select s from Servicess s where s.uid like :uid")
	public Servicess getOneService(@Param("uid")String uid);
}
