package ci.jsi.entites.element;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.critere.CritereConvertEntitie;
import ci.jsi.entites.ensembleOption.EnsembleOptionConvertEntitie;
import ci.jsi.entites.programme.ProgrammeElementConvertEntitie;
import ci.jsi.entites.section.SectionConvertEntitie;
import ci.jsi.initialisation.ConvertDate;
import ci.jsi.initialisation.Uid;

@Service
public class ElementConvert {
	@Autowired
	ConvertDate convertDate;
	@Autowired
	CritereConvertEntitie critereConvertEntitie;
	@Autowired
	SectionConvertEntitie sectionConvertEntitie;
	@Autowired
	ProgrammeElementConvertEntitie programmeElementConvertEntitie;
	@Autowired
	EnsembleOptionConvertEntitie ensembleOptionConvertEntitie;
	@Autowired
	Uid uid;
	
	public ElementTDO getOneElementTDO(Element element) {
		ElementTDO elementTDO = new ElementTDO();
		elementTDO.setId(element.getUid());
		elementTDO.setName(element.getName());
		elementTDO.setCode(element.getCode());
		elementTDO.setDateCreation(convertDate.getDateString(element.getDateCreation()));
		elementTDO.setDateUpdate(convertDate.getDateString(element.getDateUpdate()));
		elementTDO.setDescription(element.getDescription());
		elementTDO.setTypeValeur(element.getTypeValeur());
		
		if(element.getEnsembleOption() != null)
			elementTDO.setEnsembleOption(ensembleOptionConvertEntitie.getEnsembleOption(element.getEnsembleOption()));
		
		elementTDO.setCritere(critereConvertEntitie.getCriteres(element.getCritere()));
		elementTDO.setSections(sectionConvertEntitie.getSections(element.getSections()));
		elementTDO.setProgrammes(programmeElementConvertEntitie.getElementProgrammes(element.getProgrammeElements()));
		return elementTDO;
	}
	
	public List<ElementTDO> getElementTDOs(List<Element> elements) {
		List<ElementTDO> elementTDOs = new ArrayList<ElementTDO>();
		
		for(int i=0;i<elements.size();i++) {
			elementTDOs.add(getOneElementTDO(elements.get(i)));
		}
		return elementTDOs;
	}
	
	public Element saveElement(ElementTDO elementTDO) {
		Element element = new Element();
		element.setUid(uid.getUid());
		element.setName(elementTDO.getName());
		element.setCode(elementTDO.getCode());
		element.setDateCreation(new Date());
		element.setDateUpdate(new Date());
		element.setDescription(elementTDO.getDescription());
		element.setTypeValeur(elementTDO.getTypeValeur());
		
		element.setEnsembleOption(ensembleOptionConvertEntitie.setEnsembleOption(elementTDO.getEnsembleOption()));
		
		element.setCritere(critereConvertEntitie.setCriteres(elementTDO.getCritere()));
		element.setSections(sectionConvertEntitie.setSections(elementTDO.getSections()));
		element.setProgrammeElements(programmeElementConvertEntitie.SetElementProgrammes(elementTDO.getProgrammes()));
		
		return element;
	}
	
	public Element updateElement(Element element,ElementTDO elementTDO) {
		//element.setUid(uid.getUid());
		if(elementTDO.getName() != null)
			element.setName(elementTDO.getName());
		if(elementTDO.getCode() != null)
			element.setCode(elementTDO.getCode());
		element.setDateUpdate(new Date());
		if(elementTDO.getDescription() != null)
			element.setDescription(elementTDO.getDescription());
		if(elementTDO.getTypeValeur() != null)
			element.setTypeValeur(elementTDO.getTypeValeur());
		
		if(elementTDO.getEnsembleOption() != null)
			element.setEnsembleOption(ensembleOptionConvertEntitie.setEnsembleOption(elementTDO.getEnsembleOption()));
		
		if(elementTDO.getCritere().size() != 0)
			element.setCritere(critereConvertEntitie.setCriteres(elementTDO.getCritere()));
		if(elementTDO.getSections().size() != 0)
			element.setSections(sectionConvertEntitie.setSections(elementTDO.getSections()));
		if(elementTDO.getProgrammes().size() != 0)
			element.setProgrammeElements(programmeElementConvertEntitie.SetElementProgrammes(elementTDO.getProgrammes()));
		
		return element;
	}
}
