package ci.jsi.entites.section;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/section")
public class SectionRestService {
	
	@Autowired
	Isection isection;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<SectionTDO> getSectionTDO(){
		return isection.getAllSectionTDO();
		
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public SectionTDO getOneSectionTDO(@PathVariable(name="uid")String uid) {
		return isection.getOneSectionTDO(uid);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveSectionTDO(@RequestBody SectionTDO sectionTDO ) {
		return isection.saveSectionTDO(sectionTDO);
	}
	
	@RequestMapping(value="/{uid}",method=RequestMethod.PUT)
	public String updateSectionTDO(@PathVariable(name="uid")String uid,@RequestBody SectionTDO sectionTDO ) {
		return isection.updateSectionTDO(uid,sectionTDO);
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.DELETE)
	public String deleteOneSectionTDO(@PathVariable(name="uid")String uid) {
		return isection.deleteSectionTDO(uid);
	}
}
