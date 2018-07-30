package ci.jsi.entites.instance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instance")
public class InstanceRestService {
	
	@Autowired
	Iinstance iinstance;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<InstanceTDO> getAllInstanceTDO(){
		return iinstance.getAllInstanceTDO();
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public InstanceTDO getOneInstanceTDO(@PathVariable(name="uid")String uid) {
		return iinstance.getOneInstanceTDO(uid);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveInstanceTDO(@RequestBody InstanceTDO instanceTDO) {
		return iinstance.saveInstanceTDO(instanceTDO);
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.DELETE)
	public String deleteOneInstanceTDO(@PathVariable(name="uid")String uid) {
		return iinstance.deleteInstanceTDO(uid);
	}
}
