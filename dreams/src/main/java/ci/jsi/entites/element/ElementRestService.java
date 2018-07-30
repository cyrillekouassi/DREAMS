package ci.jsi.entites.element;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/element")
public class ElementRestService {
	
	/*@Autowired
	private ElementRepository elementRepository;*/
	@Autowired
	Ielement ielement;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<ElementTDO> getAllElementTDO(){
		//return elementRepository.findAll();
		return ielement.getAllElementTDO();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ElementTDO getOneElementTDO(@PathVariable(name="id")String id){
		//return elementRepository.findOne(id);
		return ielement.getOneElmentTDO(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveElementTDO(@RequestBody ElementTDO elementTDO) {
		return ielement.saveElement(elementTDO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String updateElementTDO(@PathVariable(name="id")String id, @RequestBody ElementTDO elementTDO) {
		return ielement.updateElement(id, elementTDO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteElementTDO(@PathVariable(name="id")String id) {
		return ielement.deleteElement(id);
	}

}
