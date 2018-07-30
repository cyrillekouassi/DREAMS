package ci.jsi.entites.programme;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.instance.InstanceConvertEntitie;
import ci.jsi.entites.section.SectionConvertEntitie;
import ci.jsi.entites.service.ServiceConvertEntitie;
import ci.jsi.initialisation.Uid;

@Service
public class ProgrammeConvert {
	
	@Autowired
	SectionConvertEntitie sectionConvertEntitie;
	@Autowired
	ServiceConvertEntitie serviceConvertEntitie;
	@Autowired
	InstanceConvertEntitie instanceConvertEntitie;
	@Autowired
	ProgrammeElementConvertEntitie programmeElementConvertEntitie;
	@Autowired
	Uid uid;

	public ProgrammeTDO getProgrammeTDO(Programme programme) {
		ProgrammeTDO programmeTDO = new ProgrammeTDO();
		programmeTDO.setId(programme.getUid());
		programmeTDO.setCode(programme.getCode());
		programmeTDO.setName(programme.getName());
		programmeTDO.setSections(sectionConvertEntitie.getSections(programme.getSections()));
		programmeTDO.setServices(serviceConvertEntitie.getServices(programme.getServices()));
		//programmeTDO.setInstances(instanceConvertEntitie.getInstances(programme.getInstances()));
		programmeTDO.setElements(programmeElementConvertEntitie.getProgrammeElements(programme.getProgrammeElements()));
		
		return programmeTDO;
		
		
	}
	
	public List<ProgrammeTDO> getProgrammeTDOs(List<Programme> programmes) {
		List<ProgrammeTDO> programmeTDOs = new ArrayList<ProgrammeTDO>();
		
		for(int i = 0;i<programmes.size();i++) {
			programmeTDOs.add(getProgrammeTDO(programmes.get(i)));
		}
		
		return programmeTDOs;
	}
	
	public Programme saveProgrammeTDO (ProgrammeTDO programmeTDO) {
		Programme programme = new Programme();
		
		programme.setUid(uid.getUid());
		programme.setName(programmeTDO.getName());
		programme.setCode(programmeTDO.getCode());
		
		programme.setSections(sectionConvertEntitie.setSections(programmeTDO.getSections()));
		programme.setServices(serviceConvertEntitie.setServices(programmeTDO.getServices()));
		//programme.setInstances(instanceConvertEntitie.setInstances(programmeTDO.getInstances()));
		programme.setProgrammeElements(programmeElementConvertEntitie.setProgrammeElements(programmeTDO.getElements()));
		
		return programme;
	}
	
	public Programme updateProgrammeTDO (Programme programme,ProgrammeTDO programmeTDO) {
		
		if(programmeTDO.getName() != null)
			programme.setName(programmeTDO.getName());
		if(programmeTDO.getCode() != null)
			programme.setCode(programmeTDO.getCode());
		
		if(programmeTDO.getSections().size() != 0)
			programme.setSections(sectionConvertEntitie.setSections(programmeTDO.getSections()));
		if(programmeTDO.getServices().size() != 0)
			programme.setServices(serviceConvertEntitie.setServices(programmeTDO.getServices()));
		//if(programmeTDO.getInstances().size() != 0)
		//	programme.setInstances(instanceConvertEntitie.setInstances(programmeTDO.getInstances()));
		if(programmeTDO.getElements().size() != 0)
			programme.setProgrammeElements(programmeElementConvertEntitie.setProgrammeElements(programmeTDO.getElements()));
		
		return programme;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
