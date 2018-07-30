package ci.jsi.entites.rolesDefinis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roleDefinis")
public class RolesDefinisRestService {
	@Autowired
	IrolesDefinis irolesDefinis;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<RolesDefinisTDO> getRolesDefinisTDO(){
		return irolesDefinis.getAllRolesDefinisTDO();
		
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public RolesDefinisTDO getOneRolesDefinisTDO(@PathVariable(name="uid")String uid) {
		return irolesDefinis.getOneRolesDefinisTDO(uid);
	}
	
	@RequestMapping(value="/{uid}",method=RequestMethod.PUT)
	public String updateRolesDefinisTDO(@PathVariable(name="uid")String uid,@RequestBody RolesDefinisTDO rolesDefinisTDO ) {
		return irolesDefinis.updateRolesDefinisTDO(uid,rolesDefinisTDO);
	}
	
}
