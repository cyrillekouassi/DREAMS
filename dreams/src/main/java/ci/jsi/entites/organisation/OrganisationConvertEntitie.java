package ci.jsi.entites.organisation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.UidEntitie;


@Service
public class OrganisationConvertEntitie {

	private List<UidEntitie> uidEntities;
	private UidEntitie uidEntitie;
	private List<Organisation> organisations; 
	@Autowired
	OrganisationRepository organisationRepository;
	
	public List<UidEntitie> getOrganisations(List<Organisation> Organisations) {

		uidEntities = new ArrayList<UidEntitie>();

		for (int i = 0; i < Organisations.size(); i++) {
			uidEntities.add(new UidEntitie(Organisations.get(i).getUid()));
		}

		//System.out.println("uidEntities.size = "+uidEntities.size());
		return uidEntities;
	}
	
	public UidEntitie getOrganisation(Organisation Organisations) {

		uidEntitie = new UidEntitie(Organisations.getUid());

		return uidEntitie;
	}
	
	public List<Organisation> setOrganisations(List<UidEntitie> uidEntities) {
		organisations = new ArrayList<Organisation>();
		for (int i = 0; i < uidEntities.size(); i++) {
			organisations.add(organisationRepository.getOneOrganisation(uidEntities.get(i).getId()));
		}
		return organisations;
	}
	
	public Organisation setOneOrganisation(UidEntitie uidEntitie) {
		
		Organisation org = new Organisation();
		if(uidEntitie != null) {
			System.out.println("uidEntitie.getId() = "+uidEntitie.getId());
			org = organisationRepository.getOneOrganisation(uidEntitie.getId());
		}
			

		return org;
	}
}
