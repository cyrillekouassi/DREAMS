package ci.jsi.entites.organisationLevel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrganisationLevelConvert {
	
	
	public OrganisationLevelTDO getOrganisationLevelTDO(OrganisationLevel organisationLevel) {
		OrganisationLevelTDO organisationLevelTDO = new OrganisationLevelTDO();
		organisationLevelTDO.setId(organisationLevel.getUid());
		organisationLevelTDO.setName(organisationLevel.getName());
		organisationLevelTDO.setCode(organisationLevel.getCode());
		organisationLevelTDO.setLevel(organisationLevel.getLevel());
		organisationLevelTDO.setDescription(organisationLevel.getDescription());
		return organisationLevelTDO;
	}
	
	public List<OrganisationLevelTDO> getOrganisationLevelTDOs(List<OrganisationLevel> organisationLevels) {
		List<OrganisationLevelTDO> organisationLevelTDOs = new ArrayList<OrganisationLevelTDO>();
		
		for(int i =0;i<organisationLevels.size();i++) {
			organisationLevelTDOs.add(getOrganisationLevelTDO(organisationLevels.get(i)));
		}
		
		return organisationLevelTDOs;
	}
	
	public OrganisationLevel updateOrganisationLevel(OrganisationLevel organisationLevel, OrganisationLevelTDO organisationLevelTDO) {
		
		if(organisationLevelTDO.getName() != null)
			organisationLevel.setName(organisationLevelTDO.getName());
		organisationLevel.setCode(organisationLevelTDO.getCode());
		organisationLevel.setDescription(organisationLevelTDO.getDescription());
		
		return organisationLevel;
	}
	
}
