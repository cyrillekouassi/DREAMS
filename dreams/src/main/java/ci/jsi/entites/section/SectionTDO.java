package ci.jsi.entites.section;

import java.util.ArrayList;
import java.util.List;

import ci.jsi.initialisation.UidEntitie;

public class SectionTDO {

	private String id;
	private String name;
	private String code;

	private UidEntitie programme;
	private List<UidEntitie> elements = new ArrayList<UidEntitie>();

	public SectionTDO() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public UidEntitie getProgramme() {
		return programme;
	}

	public void setProgramme(UidEntitie programme) {
		this.programme = programme;
	}

	public List<UidEntitie> getElements() {
		return elements;
	}

	public void setElements(List<UidEntitie> elements) {
		this.elements = elements;
	}

}
