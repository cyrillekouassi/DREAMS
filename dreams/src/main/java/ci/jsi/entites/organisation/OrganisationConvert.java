package ci.jsi.entites.organisation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ci.jsi.entites.organisationLevel.OrganisationLevelService;
import ci.jsi.entites.utilisateur.UserConvertEntitie;
import ci.jsi.initialisation.ConvertDate;
import ci.jsi.initialisation.Uid;

@Service
@Transactional
public class OrganisationConvert {

	/*@Autowired
	private ConvertEntities convertEntities;*/
	@Autowired
	private ConvertDate convertDate;
	@Autowired
	private Uid uid;
	@Autowired
	private OrganisationConvertEntitie organisationConvertEntitie;
	@Autowired
	private UserConvertEntitie userConvertEntitie;
	@Autowired
	private OrganisationLevelService organisationLevelService;
	
	
	public Organisation toEntity(OrganisationTDO organisationTDO) {
		
		Organisation organisation = new Organisation();
		Organisation org = new Organisation();
		organisation.setUid(uid.getUid());
		organisation.setName(organisationTDO.getName());
		organisation.setSigle(organisationTDO.getSigle());
		organisation.setCode(organisationTDO.getCode());

		organisation.setRegion(organisationTDO.getRegion());
		organisation.setDepartement(organisationTDO.getDepartement());
		organisation.setSousPrefecture(organisationTDO.getSousPrefecture());
		organisation.setCommune(organisationTDO.getCommune());
		organisation.setQuartier(organisationTDO.getQuartier());
		organisation.setPartenaire(organisationTDO.getPartenaire());
		organisation.setOrganisationLocal(organisationTDO.getOrganisationLocal());
		
		
			organisation.setDateCreation(new Date());
			organisation.setDateUpdate(new Date());
				organisation.setDateFermeture(convertDate.getDateParse(organisationTDO.getDateFermeture()));
		organisation.setDescription(organisationTDO.getDescription());
		organisation.setEmail(organisationTDO.getEmail());
		organisation.setTelephone(organisationTDO.getTelephone());
		
		organisation.setUsers(userConvertEntitie.setUsers(organisationTDO.getUsers()));
		//organisation.setInstances(instanceConvertEntitie.setInstances(organisationTDO.getInstances()));
				
		organisation.setChildrens(organisationConvertEntitie.setOrganisations(organisationTDO.getChildrens()));
		
		org = organisationConvertEntitie.setOneOrganisation(organisationTDO.getParent());
		//organisation.setParent(organisationConvertEntitie.getOneOrganisation(organisationTDO.getParent()));
		if(org != null && organisationTDO.getParent() != null) {
			//System.out.println("org != null && organisationTDO.getParent() != null");
			organisation.setParent(organisationConvertEntitie.setOneOrganisation(organisationTDO.getParent()));
			organisation.setOrganisationLevels(organisationLevelService.getNextOrganisationLevel(organisation.getParent().getLevel()));
		}else {
			organisation.setOrganisationLevels(organisationLevelService.getNextOrganisationLevel(0));
		}
		
		
		return organisation;
	}
	
	public OrganisationTDO  toTDO(Organisation organisation) {
		OrganisationTDO organisationTDO = new OrganisationTDO();
		organisationTDO.setId(organisation.getUid());
		organisationTDO.setName(organisation.getName());
		organisationTDO.setCode(organisation.getCode());
		organisationTDO.setSigle(organisation.getSigle());
		organisationTDO.setDescription(organisation.getDescription());
		organisationTDO.setEmail(organisation.getEmail());
		organisationTDO.setTelephone(organisation.getTelephone());
		organisationTDO.setLevel(organisation.getLevel());
		organisationTDO.setRegion(organisation.getRegion());
		organisationTDO.setDepartement(organisation.getDepartement());
		organisationTDO.setSousPrefecture(organisation.getSousPrefecture());
		organisationTDO.setCommune(organisation.getCommune());
		organisationTDO.setQuartier(organisation.getQuartier());
		organisationTDO.setPartenaire(organisation.getPartenaire());
		organisationTDO.setOrganisationLocal(organisation.getOrganisationLocal());
		
		if(organisation.getDateCreation() != null)
			organisationTDO.setDateCreation(convertDate.getDateString(organisation.getDateCreation()));
		if(organisation.getDateUpdate() != null)
			organisationTDO.setDateUpdate(convertDate.getDateString(organisation.getDateUpdate()));
		if(organisation.getDateFermeture() != null)
			organisationTDO.setDateFermeture(convertDate.getDateString(organisation.getDateFermeture()));
		
		
			organisationTDO.setUsers(userConvertEntitie.getUsers(organisation.getUsers()));
		
			//organisationTDO.setInstances(instanceConvertEntitie.getInstances(organisation.getInstances()));
		
			organisationTDO.setChildrens(organisationConvertEntitie.getOrganisations(organisation.getChildrens()));
		if(organisation.getParent() != null)
			organisationTDO.setParent(organisationConvertEntitie.getOrganisation(organisation.getParent()));
		
		
		return organisationTDO;
	}
	
	public List<OrganisationTDO>  toTDOList(List<Organisation> organisations) {
		List<OrganisationTDO> organisationTDOs = new ArrayList<OrganisationTDO>();
		
		System.out.println("organisations.size() = "+organisations.size());
		for(int i = 0; i<organisations.size(); i++) {
			//System.err.println("organisations.get("+i+")");
			
			organisationTDOs.add(toTDO(organisations.get(i)));
			
		}
		//System.out.println("organisations sortie");
		
		return organisationTDOs;
	}
	
	public Organisation updated(Organisation organisation,OrganisationTDO organisationTDO) {
		
		
		if(organisationTDO.getName() != null)
			organisation.setName(organisationTDO.getName());
		else
			return null;
		//if(organisationTDO.getSigle() != null)
			organisation.setSigle(organisationTDO.getSigle());
		//if(organisationTDO.getCode() != null)
			organisation.setCode(organisationTDO.getCode());
			organisation.setDateUpdate(new Date());
		if(organisationTDO.getDateFermeture() != null)
			
				organisation.setDateFermeture(convertDate.getDateParse(organisationTDO.getDateFermeture()));
			
		//if(organisationTDO.getDescription() != null)
			organisation.setDescription(organisationTDO.getDescription());
		//if(organisationTDO.getEmail() != null)
			organisation.setEmail(organisationTDO.getEmail());
		//if(organisationTDO.getTelephone() != null)
			organisation.setTelephone(organisationTDO.getTelephone());
		//if(organisationTDO.getUsers() != null)
			organisation.setTelephone(organisationTDO.getTelephone());
		
		if(organisationTDO.getUsers() != null && organisationTDO.getUsers().size() != 0)
			organisation.setUsers(userConvertEntitie.setUsers(organisationTDO.getUsers()));
		/*if(organisationTDO.getInstances() != null && organisationTDO.getInstances().size() != 0)
			organisation.setInstances(instanceConvertEntitie.setInstances(organisationTDO.getInstances()));*/
		if(organisationTDO.getChildrens() != null && organisationTDO.getChildrens().size() != 0)
			organisation.setChildrens(organisationConvertEntitie.setOrganisations(organisationTDO.getChildrens()));
		
		Organisation org = new Organisation();
		org = organisationConvertEntitie.setOneOrganisation(organisationTDO.getParent());
		if(org != null && organisationTDO.getParent() != null) {
			organisation.setParent(organisationConvertEntitie.setOneOrganisation(organisationTDO.getParent()));
			organisation.setOrganisationLevels(organisationLevelService.getNextOrganisationLevel(organisation.getParent().getLevel()));
		}
		
		return organisation;
	}
	
}
