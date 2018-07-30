package ci.jsi.entites.ensembleOption;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ensembleOption")
public class EnsembleOptionRestService {

	@Autowired
	IensembleOption iensembleOption;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<EnsembleOptionTDO> getAllEnsembleOption(){
		return iensembleOption.getAllEnsembleOptionTDO();
	}
	
	/*@RequestMapping(value="/details",method=RequestMethod.GET)
	public List<EnsembleOptionTDO> getAllEnsembleOptionDetails(){
		return iensembleOption.getAllEnsembleOptionTDO();
	}*/
	
	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public EnsembleOptionTDO getOneEnsembleOption(@PathVariable(name="uid")String uid) {
		return iensembleOption.getOneEnsembleOptionTDO(uid);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveEnsembleOption(@RequestBody EnsembleOptionTDO ensembleOptionTDO) {
		return iensembleOption.saveEnsembleOptionTDO(ensembleOptionTDO);
	}
	
	@RequestMapping(value="/{uid}",method=RequestMethod.PUT)
	public String updateEnsembleOption(@PathVariable(name="uid")String uid,@RequestBody EnsembleOptionTDO ensembleOptionTDO) {
		return iensembleOption.updateEnsembleOptionTDO(uid, ensembleOptionTDO);
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.DELETE)
	public String deleteOneEnsembleOption(@PathVariable(name="uid")String uid) {
		return iensembleOption.deleteEnsembleOptionTDO(uid);
	}
	
	
}
