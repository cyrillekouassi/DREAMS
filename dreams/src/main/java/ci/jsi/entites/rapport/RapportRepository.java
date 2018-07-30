package ci.jsi.entites.rapport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.jsi.entites.element.Element;
import ci.jsi.entites.option.Option;
import ci.jsi.entites.organisation.Organisation;

public interface RapportRepository extends JpaRepository<Rapport, Long> {
	
	public List<Rapport> findAllByOrganisationUidAndElementUidInAndOptionUidInAndPeriodeIn(String organisation,List<String> elements,List<String> options,List<String> periodes);
	
	public List<Rapport> findAllByOrganisationUidAndElementUidInAndPeriodeIn(String organisation,List<String> elements,List<String> periodes);

	public Rapport findByOrganisationAndElementAndOptionAndPeriode(Organisation organisation,Element element,Option option,String periode);
	
	public List<Rapport> findByOrganisationUidAndElementCodeAndPeriode(String organisation,String element,String periode);
	
}
