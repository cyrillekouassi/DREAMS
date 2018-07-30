package ci.jsi.entites.roleUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roleUser")
public class RoleUserRestService {
	@Autowired
	IroleUser iroleUser;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<RoleUserTDO> getRoleUserTDO(){
		return iroleUser.getAllRoleUserTDO();
		
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public RoleUserTDO getOneRoleUserTDO(@PathVariable(name="uid")String uid) {
		return iroleUser.getOneRoleUserTDO(uid);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveRoleUserTDO(@RequestBody RoleUserTDO roleUserTDO ) {
		return iroleUser.saveRoleUserTDO(roleUserTDO);
	}
	
	@RequestMapping(value="/{uid}",method=RequestMethod.PUT)
	public String updateRoleUserTDO(@PathVariable(name="uid")String uid,@RequestBody RoleUserTDO roleUserTDO) {
		return iroleUser.updateRoleUserTDO(uid,roleUserTDO);
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.DELETE)
	public String deleteOneRoleUserTDO(@PathVariable(name="uid")String uid) {
		return iroleUser.deleteRoleUserTDO(uid);
	}
}
