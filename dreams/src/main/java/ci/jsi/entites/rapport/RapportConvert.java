package ci.jsi.entites.rapport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.element.Element;
import ci.jsi.entites.element.Ielement;
import ci.jsi.entites.option.Ioption;
import ci.jsi.entites.option.Option;
import ci.jsi.entites.organisation.Iorganisation;
import ci.jsi.entites.organisation.Organisation;

@Service
public class RapportConvert {

	@Autowired
	Ielement ielement;
	@Autowired
	Ioption ioption;
	@Autowired
	Iorganisation iorganisation;

	/*
	 * public List<RapportTDO> getRapports(List<Rapport> rapports,List<String>
	 * elements) {
	 * 
	 * }
	 */

	public Rapport convertToRapport(RapportTDO rapportTDO) {
		Rapport rapport = new Rapport();
		String elementCode = null;
		String optionCode = null;
		Element element = null;
		Option option = null;
		Organisation organisation = null;

		String elementDto = rapportTDO.getElement();
		int index = elementDto.indexOf('.');
		if (index != -1) {
			elementCode = elementDto.substring(0, index);
			index++;
			optionCode = elementDto.substring(index);

		} else {
			elementCode = elementDto;
		}

		element = ielement.getOneElmentByCode(elementCode);
		if (element == null) {
			return null;
		}

		organisation = iorganisation.getOneOrganisationById(rapportTDO.getOrganisation());
		if (organisation == null) {
			return null;
		}

		if (optionCode != null) {
			for (int i = 0; i < element.getEnsembleOption().getOptions().size(); i++) {
				if (element.getEnsembleOption().getOptions().get(i).getCode().equals(optionCode)) {
					option = element.getEnsembleOption().getOptions().get(i);
				}
			}
		}

		rapport.setElement(element);
		rapport.setOption(option);
		rapport.setOrganisation(organisation);
		rapport.setPeriode(rapportTDO.getPeriode());
		rapport.setValeurs(rapportTDO.getValeurs());

		return rapport;
	}

	public List<RapportTDO> listConvertToRapportTDOCode(List<Rapport> rapports) {
		List<RapportTDO> rapportTDOs = new ArrayList<>();
		for (int i = 0; i < rapports.size(); i++) {
			rapportTDOs.add(convertToRapportTDOCode(rapports.get(i)));
		}

		return rapportTDOs;
	}

	private RapportTDO convertToRapportTDOCode(Rapport rapport) {
		RapportTDO rapportTDO = new RapportTDO();

		String elementCode = null;
		if (rapport.getOption() == null) {
			elementCode = rapport.getElement().getCode();
		} else {
			elementCode = rapport.getElement().getCode() + "." + rapport.getOption().getCode();
		}

		rapportTDO.setOrganisation(rapport.getOrganisation().getUid());
		rapportTDO.setElement(elementCode);
		rapportTDO.setPeriode(rapport.getPeriode());
		rapportTDO.setValeurs(rapport.getValeurs());

		return rapportTDO;
	}

	public List<RapportTDO> constituRapportTDOs(List<String> elements, List<Rapport> rapports) {
		List<RapportTDO> rapportTDOs = new ArrayList<RapportTDO>();
		for (int i = 0; i < elements.size(); i++) {
			for (int j = 0; j < rapports.size(); j++) {
				if (rapports.get(j).getElement() == null) {
					break;
				}
				String elementOption = null;
				if (rapports.get(j).getOption() != null) {
					elementOption = rapports.get(j).getElement().getUid() + "." + rapports.get(j).getOption().getUid();
				} else {
					elementOption = rapports.get(j).getElement().getUid();
				}

				if (elements.get(i).equals(elementOption)) {
					RapportTDO rapportTDO = new RapportTDO();
					rapportTDO.setElement(elementOption);
					rapportTDO.setOrganisation(rapports.get(j).getOrganisation().getUid());
					rapportTDO.setPeriode(rapports.get(j).getPeriode());
					rapportTDO.setValeurs(rapports.get(j).getValeurs());
					rapportTDOs.add(rapportTDO);
				}
			}
		}
		for (int j = 0; j < rapports.size(); j++) {
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++ ");
			System.out.println("rapport: j = "+j);
			if (rapports.get(j).getElement() != null) {
				if (rapports.get(j).getOption() != null) {
					System.out.println("Organisation = "+rapports.get(j).getOrganisation().getName());
					System.out.println("Element = "+rapports.get(j).getElement().getName()+" - "+rapports.get(j).getOption().getName());
					System.out.println("Periode = "+rapports.get(j).getPeriode());
					System.out.println("Valeur = "+rapports.get(j).getValeurs());
				} else {
					System.out.println("Organisation = "+rapports.get(j).getOrganisation().getName());
					System.out.println("Element = "+rapports.get(j).getElement().getName());
					System.out.println("Periode = "+rapports.get(j).getPeriode());
					System.out.println("Valeur = "+rapports.get(j).getValeurs());
				}
			}else {
				System.out.println("Element null; j = "+j);
			}
		}
		return rapportTDOs;
	}

}
