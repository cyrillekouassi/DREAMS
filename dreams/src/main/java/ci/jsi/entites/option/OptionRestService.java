package ci.jsi.entites.option;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/option")
public class OptionRestService {

	
	@Autowired
	Ioption ioption;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<OptionTDO> getAllOptionTDO(){
		return ioption.getAllOptionTDO();
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public OptionTDO getOneOption(@PathVariable(name="uid")String uid) {
		return ioption.getOneOptionTDO(uid);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveOptionTDO(@RequestBody OptionTDO optionTDO) {
		return ioption.saveOptionTDO(optionTDO);
	}
	
	@RequestMapping(value="/{uid}",method=RequestMethod.PUT)
	public String updateOptionTDO(@PathVariable(name="uid")String uid,@RequestBody OptionTDO optionTDO) {
		return ioption.updateOptionTDO(uid, optionTDO);
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.DELETE)
	public String deleteOneOptionTDO(@PathVariable(name="uid")String uid) {
		return ioption.deleteOptionTDO(uid);
	}
}
