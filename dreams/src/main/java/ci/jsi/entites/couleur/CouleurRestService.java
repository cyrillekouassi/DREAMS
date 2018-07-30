package ci.jsi.entites.couleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/couleur")
public class CouleurRestService {

	@Autowired
	Icouleur icouleur;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<CouleurTDO> getAllCouleurTDO(){
		//return elementRepository.findAll();
		return icouleur.getAllCouleurTDO();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public CouleurTDO getOneCouleurTDO(@PathVariable(name="id")String id){
		//return elementRepository.findOne(id);
		return icouleur.getOneCouleurTDO(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveCouleurTDO(@RequestBody CouleurTDO couleurTDO) {
		return icouleur.saveCouleurTDO(couleurTDO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String updateCouleurTDO(@PathVariable(name="id")String id, @RequestBody CouleurTDO couleurTDO) {
		return icouleur.updateCouleurTDO(id, couleurTDO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteCouleurTDO(@PathVariable(name="id")String id) {
		return icouleur.deleteCouleurTDO(id);
	}
}
