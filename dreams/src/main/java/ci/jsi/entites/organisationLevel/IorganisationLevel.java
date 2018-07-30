package ci.jsi.entites.organisationLevel;

import java.util.List;

public interface IorganisationLevel {

	public List<OrganisationLevel> getAllOrganisationLevel();
	public OrganisationLevelTDO getOneOrganisationLevelTDO(String id);
	public String updateOrganisationLevelTDO(String id, OrganisationLevelTDO organisationLevelTDO);
}
