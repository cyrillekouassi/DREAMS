package ci.jsi.entites.critere;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/critere")
public class CritereRestService {

	@Autowired
	Icritere icritere;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<CritereTDO> getAllCritereTDO(){
		return icritere.getAllCritereTDO();
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public CritereTDO getOneCritereTDO(@PathVariable(name="uid")String uid) {
		return icritere.getOneCritereTDO(uid);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveCritereTDO(@RequestBody CritereTDO critereTDO) {
		return icritere.saveCritereTDO(critereTDO);
	}
	
	@RequestMapping(value="/{uid}",method=RequestMethod.PUT)
	public String updateCritere(@PathVariable(name="uid")String uid,@RequestBody CritereTDO critereTDO) {
		return icritere.updateCritereTDO(uid, critereTDO);
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.DELETE)
	public String deleteOneCritere(@PathVariable(name="uid")String uid) {
		return icritere.deleteCritereTDO(uid);
	}
}
