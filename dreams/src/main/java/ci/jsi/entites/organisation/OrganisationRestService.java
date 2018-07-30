package ci.jsi.entites.organisation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organisation")
public class OrganisationRestService {
	
	@Autowired
	OrganisationService organisationService;
	@Autowired
	Iorganisation iorganisation;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<OrganisationTDO> getOrganisation(){
		return iorganisation.getAllOrganisationTDO();
		//return organisationService.getOrganisationTDO();
		//return organisation.getOrganisation();
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public OrganisationTDO getOneOrganisation(@PathVariable(name="uid")String uid) {
		return iorganisation.getOneOrganisationTDO(uid);
	}
	
	@RequestMapping(value="code/{code}", method=RequestMethod.GET)
	public OrganisationTDO getOneOrganisationByCode(@PathVariable(name="code")String code) {
		return iorganisation.getOneOrganisationTDO(code);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveOrganisation(@RequestBody OrganisationTDO organisationTDO ) {
		return iorganisation.saveOrganisationTDO(organisationTDO);
	}
	
	@RequestMapping(value="/{uid}",method=RequestMethod.PUT)
	public String updateOrganisation(@PathVariable(name="uid")String uid,@RequestBody OrganisationTDO organisationTDO ) {
		return iorganisation.updateOrganisationTDO(uid,organisationTDO);
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.DELETE)
	public String deleteOneOrganisation(@PathVariable(name="uid")String uid) {
		return iorganisation.deleteOrganisationTDO(uid);
	}
}
