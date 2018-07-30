package ci.jsi.entites.dataValue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ci.jsi.initialisation.ResultatRequete;

@RestController
@RequestMapping(value="/dataValue")
public class DataValueRestService {

	@Autowired
	private IdataValues idataValues;
	
	@RequestMapping(value="/{instance}",method=RequestMethod.GET)
	public DataInstance getDataInstance(@PathVariable(name="instance") String instance){
		System.out.println("entrer dans getDataInstance");
		return idataValues.getDataInstance(instance);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<DataValueTDO> searchDataValueTDO(@RequestParam(name="programme",defaultValue="") String programme,@RequestParam(name="organisation",defaultValue="") String organisation,@RequestParam(name="element",defaultValue="") String element,@RequestParam(name="valeur",defaultValue="") String valeur){
		System.out.println("entrer dans searchDataValueTDO");
		return idataValues.SearchDataValueTDO(programme, organisation, element, valeur);
	}
	
	@RequestMapping(value="/orgPro",method=RequestMethod.GET)
	public Page<DataInstance> getDataValueOrganisation(@RequestParam(name="prog",defaultValue="") String programme,@RequestParam(name="org",defaultValue="") String organisation, @RequestParam(name="page",defaultValue="0")int page, @RequestParam(name="size",defaultValue="50")int size){
		return idataValues.getDataValueOrganisation(programme, organisation,new PageRequest(page, size));
	}
	
	@RequestMapping(value="/analyse",method=RequestMethod.GET)
	public List<DataInstance> getDataValueAnalyse(@RequestParam(name="org") List<String> organisation,@RequestParam(name="prog") String programme, @RequestParam(name="debut")String debut, @RequestParam(name="fin")String fin){
		return idataValues.dataAnalysePeriode(organisation, programme, debut, fin);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResultatRequete saveDataInstance(@RequestBody DataInstance dataInstance){
		System.out.println("entrer dans saveDataInstance org =  " +dataInstance.getOrganisation());
		return idataValues.saveDataInstance(dataInstance);
	}
	
	@RequestMapping(value="/saveValue",method=RequestMethod.POST)
	public DataValueTDO saveElementValue(@RequestBody DataValueTDO dataValueTDO){
		return idataValues.saveDataValueTDO(dataValueTDO);
	}
	
}
