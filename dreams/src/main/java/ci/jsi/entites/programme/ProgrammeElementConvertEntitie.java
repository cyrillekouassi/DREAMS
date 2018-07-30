package ci.jsi.entites.programme;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.element.Element;
import ci.jsi.entites.element.ElementRepository;
import ci.jsi.initialisation.UidElementPrograme;
import ci.jsi.initialisation.UidProgrammeElement;

@Service
public class ProgrammeElementConvertEntitie {
	@Autowired
	ProgrammeRepository programmeRepository;
	@Autowired
	ElementRepository elementRepository;

	public UidElementPrograme getElementProgramme(ProgrammeElement programmeElement) {
		UidElementPrograme uidElementPrograme = new UidElementPrograme();
		uidElementPrograme.setProgramme(programmeElement.getProgramme().getUid());
		uidElementPrograme.setShowRapport(programmeElement.isAfficheRapport());
		return uidElementPrograme;
	}
	
	
	public UidProgrammeElement getProgrammeElement(ProgrammeElement programmeElement) {
		UidProgrammeElement uidProgrammeElement = new UidProgrammeElement();
		uidProgrammeElement.setElement(programmeElement.getElement().getUid());
		uidProgrammeElement.setShowRapport(programmeElement.isAfficheRapport());
		return uidProgrammeElement;
	}
	
	public List<UidElementPrograme> getElementProgrammes(List<ProgrammeElement> ProgrammeElements){
		List<UidElementPrograme> uidElementProgrames = new ArrayList<UidElementPrograme>();
		for(int i = 0; i<ProgrammeElements.size(); i++) {
			uidElementProgrames.add(getElementProgramme(ProgrammeElements.get(i)));
		}
		return uidElementProgrames;
	}
	
	public List<UidProgrammeElement> getProgrammeElements(List<ProgrammeElement> ProgrammeElements){
		List<UidProgrammeElement> uidProgrammeElements = new ArrayList<UidProgrammeElement>();
		for(int i = 0; i<ProgrammeElements.size(); i++) {
			uidProgrammeElements.add(getProgrammeElement(ProgrammeElements.get(i)));
		}
		return uidProgrammeElements;
	}
	
	public ProgrammeElement SetElementProgramme(UidElementPrograme uidElementPrograme) {
		ProgrammeElement programmeElement = new ProgrammeElement();
		Programme programme = new Programme();
		programme = programmeRepository.getOneProgramme(uidElementPrograme.getProgramme());
		programmeElement.setProgramme(programme);
		programmeElement.setAfficheRapport(uidElementPrograme.getShowRapport());
		
		return programmeElement;
		
	}
	public List<ProgrammeElement> SetElementProgrammes(List<UidElementPrograme> uidElementProgrames) {
		
		List<ProgrammeElement> programmeElements = new ArrayList<ProgrammeElement>();
		
		 for(int i=0;i<uidElementProgrames.size();i++) {
			 ProgrammeElement programmeElement = new ProgrammeElement();
			 programmeElement = SetElementProgramme(uidElementProgrames.get(i));
			 if(programmeElement.getProgramme() != null)
				 programmeElements.add(programmeElement);
		 }
		
		return programmeElements;
		
	}
	
	
	public ProgrammeElement setProgrammeElement(UidProgrammeElement uidProgrammeElement) {
		ProgrammeElement programmeElement = new ProgrammeElement();
		Element element = new Element();
		element = elementRepository.getOneElement(uidProgrammeElement.getElement());
		programmeElement.setElement(element);
		programmeElement.setAfficheRapport(uidProgrammeElement.getShowRapport());
		return programmeElement;
	}
	
	public List<ProgrammeElement> setProgrammeElements(List<UidProgrammeElement> uidProgrammeElements) {
		List<ProgrammeElement> programmeElements = new ArrayList<ProgrammeElement>();
		
		for(int i=0;i<uidProgrammeElements.size(); i++) {
			ProgrammeElement programmeElement = new ProgrammeElement();
			programmeElement = setProgrammeElement(uidProgrammeElements.get(i));
			if(programmeElement.getElement() != null) {
				programmeElements.add(programmeElement);
			}
			
		}
		
		return programmeElements;
	}
	
	
	
}
