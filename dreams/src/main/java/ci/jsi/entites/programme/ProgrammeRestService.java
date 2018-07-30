package ci.jsi.entites.programme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/programme")
public class ProgrammeRestService {

	
	@Autowired
	Iprogramme iprogramme;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<ProgrammeTDO> getProgrammeTDO(){
		return iprogramme.getAllProgrammeTDO();
		
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public ProgrammeTDO getOneProgrammeTDO(@PathVariable(name="uid")String uid) {
		return iprogramme.getOneProgrammeTDO(uid);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveProgrammeTDO(@RequestBody ProgrammeTDO programmeTDO ) {
		return iprogramme.saveProgrammeTDO(programmeTDO);
	}
	
	@RequestMapping(value="/{uid}",method=RequestMethod.PUT)
	public String updateProgrammeTDO(@PathVariable(name="uid")String uid,@RequestBody ProgrammeTDO programmeTDO ) {
		return iprogramme.updateProgrammeTDO(uid,programmeTDO);
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.DELETE)
	public String deleteOneProgrammeTDO(@PathVariable(name="uid")String uid) {
		return iprogramme.deleteProgrammeTDO(uid);
	}
}
