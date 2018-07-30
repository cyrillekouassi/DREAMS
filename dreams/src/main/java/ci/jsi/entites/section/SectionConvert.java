package ci.jsi.entites.section;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.element.ElementConvertEntitie;
import ci.jsi.entites.programme.ProgrammeConvertEntite;
import ci.jsi.initialisation.Uid;

@Service
public class SectionConvert {

	@Autowired
	ProgrammeConvertEntite programmeConvertEntite;
	@Autowired
	ElementConvertEntitie elementConvertEntitie;
	@Autowired
	Uid uid;

	public SectionTDO getSectionTDO(Section section) {
		SectionTDO sectionTDO = new SectionTDO();
		sectionTDO.setId(section.getUid());
		sectionTDO.setName(section.getName());
		sectionTDO.setCode(section.getCode());

		if (section.getProgramme() != null)
			sectionTDO.setProgramme(programmeConvertEntite.getProgramme(section.getProgramme()));
		sectionTDO.setElements(elementConvertEntitie.getElements(section.getElements()));

		return sectionTDO;
	}

	public List<SectionTDO> getSectionTDOs(List<Section> sections) {
		List<SectionTDO> sectionTDOs = new ArrayList<SectionTDO>();
		for (int i = 0; i < sections.size(); i++) {
			sectionTDOs.add(getSectionTDO(sections.get(i)));
		}
		return sectionTDOs;
	}

	public Section saveSectionTDO(SectionTDO sectionTDO) {
		Section section = new Section();
		section.setUid(uid.getUid());
		section.setName(sectionTDO.getName());
		section.setCode(sectionTDO.getCode());
		section.setProgramme(programmeConvertEntite.setProgramme(sectionTDO.getProgramme()));
		section.setElements(elementConvertEntitie.setElements(sectionTDO.getElements()));
		return section;
	}

	public Section UpdateSectionTDO(Section section, SectionTDO sectionTDO) {
		if (sectionTDO.getName() != null)
			section.setName(sectionTDO.getName());
		if (sectionTDO.getCode() != null)
			section.setCode(sectionTDO.getCode());
		if (sectionTDO.getProgramme().getId() != null)
			section.setProgramme(programmeConvertEntite.setProgramme(sectionTDO.getProgramme()));
		if (sectionTDO.getElements().size() != 0)
			section.setElements(elementConvertEntitie.setElements(sectionTDO.getElements()));
		return section;
	}

}
