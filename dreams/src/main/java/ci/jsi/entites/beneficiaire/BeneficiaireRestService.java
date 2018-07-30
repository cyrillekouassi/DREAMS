package ci.jsi.entites.beneficiaire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ci.jsi.initialisation.ResultatRequete;


@RestController
@RequestMapping(value="/beneficiaire")
public class BeneficiaireRestService {
	
	@Autowired
	Ibeneficiaire ibeneficiaire;

	@RequestMapping(method=RequestMethod.GET)
	public List<BeneficiaireTDO> searchBeneficiaireTDO(@RequestParam(name="organisation",defaultValue="") String organisation,@RequestParam(name="idDreams",defaultValue="") String idDreams){
		//return idataValues.SearchDataValueTDO(programme, organisation, element, valeur);
		return ibeneficiaire.SearchBeneficiaireTDOByIdDreams(idDreams, organisation);
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResultatRequete SaveBeneficiaireTDO(@RequestBody BeneficiaireTDO beneficiaireTDO){
		//return idataValues.SearchDataValueTDO(programme, organisation, element, valeur);
		return ibeneficiaire.saveBeneficiaireTDO(beneficiaireTDO);
	}
}
