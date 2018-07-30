package ci.jsi.entites.section;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService implements Isection {

	@Autowired
	SectionConvert sectionConvert;
	@Autowired
	SectionRepository sectionRepository;

	@Override
	public List<SectionTDO> getAllSectionTDO() {

		return sectionConvert.getSectionTDOs(sectionRepository.findAll());
	}

	@Override
	public SectionTDO getOneSectionTDO(String id) {
		SectionTDO sectionTDO = new SectionTDO();
		Section section = new Section();

		section = sectionRepository.getOneSection(id);
		if (section != null)
			sectionTDO = sectionConvert.getSectionTDO(section);

		return sectionTDO;
	}

	@Override
	public String saveSectionTDO(SectionTDO sectionTDO) {
		Section section = new Section();
		section = sectionConvert.saveSectionTDO(sectionTDO);
		section = sectionRepository.save(section);
		return "Saved id: "+section.getUid();
	}

	@Override
	public String updateSectionTDO(String id, SectionTDO sectionTDO) {
		Section section = new Section();

		section = sectionRepository.getOneSection(id);
		if (section != null) {
			section = sectionConvert.UpdateSectionTDO(section, sectionTDO);
			section = sectionRepository.save(section);
		}
		return "Update id: "+section.getUid();
	}

	@Override
	public String deleteSectionTDO(String id) {
		Section section = new Section();
		section = sectionRepository.getOneSection(id);
		if (section != null)
			sectionRepository.delete(section);
		return "Success deleted";
	}

}
