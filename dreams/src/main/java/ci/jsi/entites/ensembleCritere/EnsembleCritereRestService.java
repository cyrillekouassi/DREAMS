package ci.jsi.entites.ensembleCritere;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ensembleCritere")
public class EnsembleCritereRestService {

	@Autowired
	IensembleCritere iensembleCritere;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<EnsembleCritereTDO> getAllEnsembleCritereTDO(){
		return iensembleCritere.getAllEnsembleCritereTDO();
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public EnsembleCritereTDO getOneEnsembleCritereTDO(@PathVariable(name="uid")String uid) {
		return iensembleCritere.getOneEnsembleCritereTDO(uid);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveEnsembleCritereTDO(@RequestBody EnsembleCritereTDO ensembleCritereTDO) {
		return iensembleCritere.saveEnsembleCritereTDO(ensembleCritereTDO);
	}
	
	@RequestMapping(value="/{uid}",method=RequestMethod.PUT)
	public String updateEnsembleCritereTDO(@PathVariable(name="uid")String uid,@RequestBody EnsembleCritereTDO ensembleCritereTDO) {
		return iensembleCritere.updateEnsembleCritereTDO(uid, ensembleCritereTDO);
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.DELETE)
	public String deleteOneEnsembleCritereTDO(@PathVariable(name="uid")String uid) {
		return iensembleCritere.deleteEnsembleCritereTDO(uid);
	}
}
