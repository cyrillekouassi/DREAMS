package ci.jsi.entites.rapport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rapport")
public class RapportRestService {
	
	@Autowired
	Irapport irapport;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public List<RapportTDO> searchDataValueTDO(@RequestParam(name="organisation",defaultValue="") String organisation,@RequestParam(name="element",defaultValue="") List<String> elements,@RequestParam(name="periode",defaultValue="") List<String> periode){
		System.out.println("entrer dans searchDataValueTDO");
		return irapport.getRapport(organisation, elements, periode);
	}
}
