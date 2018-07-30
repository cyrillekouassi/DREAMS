package ci.jsi.entites.section;

import java.util.List;

public interface Isection {
	public List<SectionTDO> getAllSectionTDO();
	public SectionTDO getOneSectionTDO(String id);
	public String saveSectionTDO(SectionTDO sectionTDO);
	public String updateSectionTDO(String id, SectionTDO sectionTDO);
	public String deleteSectionTDO(String id);
}
