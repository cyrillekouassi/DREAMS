package ci.jsi.entites.beneficiaire;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Long> {
	
	
	public Beneficiaire findByDreamsId(String idDreams);
	
	public Beneficiaire findByUid(String uid);
	
	public List<Beneficiaire> findByOrganisationUidAndDreamsIdContaining(String organisation,String dreamsId);
	
	public List<Beneficiaire> findAllByOrganisationUidInAndDateEnrolementGreaterThanEqualAndDateEnrolementLessThanEqual(List<String> organisation,Date debut, Date fin);
	
	public List<Beneficiaire> findAllByOrganisationUidInAndDateEnrolementLessThanEqual(List<String> organisation, Date fin);
}
