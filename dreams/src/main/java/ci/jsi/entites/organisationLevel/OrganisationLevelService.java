package ci.jsi.entites.organisationLevel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.Uid;

@Service
public class OrganisationLevelService implements IorganisationLevel{

	private OrganisationLevel organisationLevel = new OrganisationLevel();
	@Autowired
	private OrganisationLevelRepository organisationLevelRepository;
	
	@Autowired
	OrganisationLevelConvert organisationLevelConvert;
	
	@Autowired
	private Uid uid;
	
	
	
	public OrganisationLevel getNextOrganisationLevel(int level) {
		//System.out.println("parent level = "+orglevel.getLevel());
		level ++;
		organisationLevel = organisationLevelRepository.getOneOrganisationLevel(level);
		//System.out.println("organisationLevel.getUid() = "+organisationLevel.getUid());
		if(organisationLevel == null)
			organisationLevel = addOrganisationLevel(level);
		return organisationLevel;
		
	}
	
	
	public OrganisationLevel addOrganisationLevel(int level) {
		OrganisationLevel  orglevel = new OrganisationLevel();
		orglevel.setUid(uid.getUid());
		orglevel.setName("Niveau "+level);
		orglevel.setLevel(level);
		organisationLevelRepository.save(orglevel);
		return 	orglevel;
	}


	@Override
	public List<OrganisationLevel> getAllOrganisationLevel() {
		// TODO Auto-generated method stub
		return organisationLevelRepository.findAll();
	}


	@Override
	public OrganisationLevelTDO getOneOrganisationLevelTDO(String id) {
		OrganisationLevelTDO organisationLevelTDO = new OrganisationLevelTDO();
		OrganisationLevel organisationLevel = new OrganisationLevel();
		organisationLevel = organisationLevelRepository.getOrganisationLevel(id);
		if(organisationLevel != null)
			organisationLevelTDO = organisationLevelConvert.getOrganisationLevelTDO(organisationLevel);
		
		return organisationLevelTDO;
	}


	@Override
	public String updateOrganisationLevelTDO(String id, OrganisationLevelTDO organisationLevelTDO) {
		OrganisationLevel organisationLevel = new OrganisationLevel();
		organisationLevel = organisationLevelRepository.getOrganisationLevel(id);
		if(organisationLevel != null) {
			organisationLevel = organisationLevelConvert.updateOrganisationLevel(organisationLevel, organisationLevelTDO);
			organisationLevel = organisationLevelRepository.save(organisationLevel);
		}
		return "updated id: "+organisationLevel.getUid();
	}
}
