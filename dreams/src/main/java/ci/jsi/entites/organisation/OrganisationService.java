package ci.jsi.entites.organisation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganisationService implements Iorganisation {

	@Autowired
	OrganisationRepository organisationRepository;
	@Autowired
	OrganisationConvert organisationConvert;
	
	@Override
	public List<OrganisationTDO> getAllOrganisationTDO() {
		
	    return organisationConvert.toTDOList(organisationRepository.findAll());
	    
	}
	
	@Override
	public OrganisationTDO getOneOrganisationTDO(String uid) {
		// TODO Auto-generated method stub
		return organisationConvert.toTDO(organisationRepository.getOneOrganisation(uid));
	}

	@Override
	public String saveOrganisationTDO(OrganisationTDO organisationTDO) {
		Organisation organisation = new Organisation();
		organisation = organisationConvert.toEntity(organisationTDO);
		organisationRepository.save(organisation);
		//organisationRepository.save(organisationConvert.toEntity(organisationTDO, true));
		return organisation.getUid();
	}

	@Override
	public String updateOrganisationTDO(String id, OrganisationTDO organisationTDO) {
		Organisation organisation = new Organisation();
		organisation = organisationRepository.getOneOrganisation(id);
		if(organisation != null) {
			organisation = organisationConvert.updated(organisation, organisationTDO);
			if(organisation != null) {
				organisationRepository.save(organisation);
			}
		}
		
		return organisation.getUid();
	}

	@Override
	public String deleteOrganisationTDO(String id) {
		Organisation organisation = new Organisation();
		organisation = organisationRepository.getOneOrganisation(id);
		if(organisation != null) {
			organisationRepository.delete(organisation);
		}
		
		return "Succes";
	}

	@Override
	public Organisation getOneOrganisationById(String id) {
		
		return organisationRepository.getOneOrganisation(id);
	}

	@Override
	public Organisation getOneOrganisationByCode(String code) {
		
		return organisationRepository.getOneOrganisationByCode(code);
	}

	@Override
	public OrganisationTDO getOneOrganisationTDOByCode(String code) {
		Organisation organisation = null;
		organisation = organisationRepository.getOneOrganisationByCode(code);
		if(organisation == null)
			return null;
		return organisationConvert.toTDO(organisation);
	}

	@Override
	public List<Organisation> getOrganisationByLevel(int level) {
		
		List<Organisation> list = new ArrayList<Organisation>();
		//List<Organisation> organisations = new ArrayList<Organisation>();
		list = organisationRepository.findAllByLevel(level);
		//organisations = organisationRepository.findByLevel(level);
		return list;
	}

	@Override
	public List<Organisation> getAllOrganisation() {
		return organisationRepository.findAll();
	}

	
}
