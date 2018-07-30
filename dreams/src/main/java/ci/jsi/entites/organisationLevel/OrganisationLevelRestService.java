package ci.jsi.entites.organisationLevel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organisationLevel")
public class OrganisationLevelRestService {

	@Autowired
	IorganisationLevel iorganisationLevel;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<OrganisationLevel> getOrganisationLevel(){
		return iorganisationLevel.getAllOrganisationLevel();
		
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public OrganisationLevelTDO getOneOrganisationLevelTDO(@PathVariable(name="uid")String uid) {
		return iorganisationLevel.getOneOrganisationLevelTDO(uid);
	}
	
	
	@RequestMapping(value="/{uid}",method=RequestMethod.PUT)
	public String updateOrganisationLevelTDO(@PathVariable(name="uid")String uid,@RequestBody OrganisationLevelTDO organisationLevelTDO ) {
		return iorganisationLevel.updateOrganisationLevelTDO(uid,organisationLevelTDO);
	}
	
}
