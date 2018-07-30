package ci.jsi.entites.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceRestService {
	@Autowired
	Iservice iservice;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<ServiceTDO> getServiceTDO(){
		return iservice.getAllServiceTDO();
		
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public ServiceTDO getOneServiceTDO(@PathVariable(name="uid")String uid) {
		return iservice.getOneServiceTDO(uid);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveServiceTDO(@RequestBody ServiceTDO serviceTDO ) {
		return iservice.saveServiceTDO(serviceTDO);
	}
	
	@RequestMapping(value="/{uid}",method=RequestMethod.PUT)
	public String updateServiceTDO(@PathVariable(name="uid")String uid,@RequestBody ServiceTDO serviceTDO ) {
		return iservice.updateServiceTDO(uid,serviceTDO);
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.DELETE)
	public String deleteOneServiceTDO(@PathVariable(name="uid")String uid) {
		return iservice.deleteServiceTDO(uid);
	}
}
