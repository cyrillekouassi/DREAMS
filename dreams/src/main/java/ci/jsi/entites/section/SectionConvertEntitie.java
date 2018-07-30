package ci.jsi.entites.section;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.UidEntitie;


@Service
public class SectionConvertEntitie {

	@Autowired
	SectionRepository sectionRepository;
	

	public UidEntitie getSection(Section section) {
		UidEntitie uidEntitie = new UidEntitie();
		uidEntitie.setId(section.getUid());
		return uidEntitie;
	}

	public Section setSection(UidEntitie uidEntitie) {
		Section section = new Section();
		section = sectionRepository.getOneSection(uidEntitie.getId());
		return section;
	}

	public List<UidEntitie> getSections(List<Section> sections) {
		List<UidEntitie> uidEntities = new ArrayList<UidEntitie>();

		for (int i = 0; i < sections.size(); i++) {
			uidEntities.add(getSection(sections.get(i)));
		}
		return uidEntities;
	}

	public List<Section> setSections(List<UidEntitie> uidEntities) {
		List<Section> sections = new ArrayList<Section>();
		for (int i = 0; i < uidEntities.size(); i++) {
			sections.add(setSection(uidEntities.get(i)));
		}
		return sections;
	}

}
