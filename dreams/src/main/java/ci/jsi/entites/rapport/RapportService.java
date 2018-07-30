package ci.jsi.entites.rapport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RapportService implements Irapport {
	
	@Autowired
	RapportRepository RapportRepository;
	@Autowired
	RapportConvert rapportConvert;
	

	@Override
	public List<RapportTDO> getRapport(String organisation, List<String> elements, List<String> periode) {
		List<RapportTDO> rapportTDOs = new ArrayList<RapportTDO>();
		List<String> lesElements = new ArrayList<String>();
		//List<String> lesOptions = new ArrayList<String>();
		List<Rapport> rapports = new ArrayList<Rapport>();

		for(int i =0;i<elements.size();i++) {
			String lelement = elements.get(i);
			int index  = lelement.indexOf('.');
			if(index != -1) {
				String ment = lelement.substring(0, index);
				lesElements.add(ment);
				//index++;
				//String opt = lelement.substring(index);
				//lesOptions.add(opt);
			}else {
				lesElements.add(lelement);
			}
		}
		
		rapports = RapportRepository.findAllByOrganisationUidAndElementUidInAndPeriodeIn(organisation, lesElements, periode);
		if(!rapports.isEmpty()) {
			rapportTDOs = rapportConvert.constituRapportTDOs(elements,rapports);
		}
		return rapportTDOs;
	}

	@Override
	public String saveRapportTDO(RapportTDO rapportTDO) {
		Rapport rapportConv = rapportConvert.convertToRapport(rapportTDO);
		Rapport rapportExit = null;
		if(rapportConv == null) {
			return "fail";
		}
		rapportExit =  RapportRepository.findByOrganisationAndElementAndOptionAndPeriode(rapportConv.getOrganisation(), rapportConv.getElement(), rapportConv.getOption(), rapportConv.getPeriode());
		if(rapportExit == null) {
			rapportExit = rapportConv;
		}else {
			rapportExit.setValeurs(rapportConv.getValeurs());
		}
		rapportExit = RapportRepository.save(rapportExit);
		return "OK";
	}

	@Override
	public List<RapportTDO> getRapportOptionRapportTDOCodeNull(String organisation, String element, String periode) {
		List<Rapport> rapports = new ArrayList<Rapport>();
		List<RapportTDO> rapportTDOs  = new ArrayList<RapportTDO>();
		rapports = RapportRepository.findByOrganisationUidAndElementCodeAndPeriode(organisation, element, periode);
		if(rapports.isEmpty()) {
			return rapportTDOs;
		}
		rapportTDOs = rapportConvert.listConvertToRapportTDOCode(rapports);
		return rapportTDOs;
	}

}
