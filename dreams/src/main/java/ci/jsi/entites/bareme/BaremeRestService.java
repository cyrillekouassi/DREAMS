package ci.jsi.entites.bareme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bareme")
public class BaremeRestService {
	@Autowired
	Ibareme ibareme;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<BaremeTDO> getAllBaremeTDO(){
		//return elementRepository.findAll();
		return ibareme.getAllBaremeTDO();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public BaremeTDO getOneBaremeTDO(@PathVariable(name="id")String id){
		//return elementRepository.findOne(id);
		return ibareme.getOneBaremeTDO(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveBaremeTDO(@RequestBody BaremeTDO baremeTDO) {
		return ibareme.saveBaremeTDO(baremeTDO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String updateBaremeTDO(@PathVariable(name="id")String id, @RequestBody BaremeTDO baremeTDO) {
		return ibareme.updateBaremeTDO(id, baremeTDO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteBaremeTDO(@PathVariable(name="id")String id) {
		return ibareme.deleteBaremeTDO(id);
	}
}
