package ci.jsi.entites.organisation;

import java.util.List;

public interface Iorganisation {

	public List<OrganisationTDO> getAllOrganisationTDO();
	public OrganisationTDO getOneOrganisationTDO(String id);
	public OrganisationTDO getOneOrganisationTDOByCode(String code);
	public String saveOrganisationTDO(OrganisationTDO organisationTDO);
	public String updateOrganisationTDO(String id, OrganisationTDO organisationTDO);
	public String deleteOrganisationTDO(String id);
	
	public Organisation getOneOrganisationById(String id);
	public Organisation getOneOrganisationByCode(String code);
	public List<Organisation> getOrganisationByLevel(int level);
	public List<Organisation> getAllOrganisation();
}

