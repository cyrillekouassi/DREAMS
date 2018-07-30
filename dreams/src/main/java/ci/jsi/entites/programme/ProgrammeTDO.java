package ci.jsi.entites.programme;

import java.util.ArrayList;
import java.util.List;

import ci.jsi.initialisation.UidEntitie;
import ci.jsi.initialisation.UidProgrammeElement;

public class ProgrammeTDO {

	
	private String id;
	private String name;
	private String code;
	
	private List<UidEntitie> sections = new ArrayList<UidEntitie>();
	private List<UidEntitie> services = new ArrayList<UidEntitie>();
	//private List<UidEntitie> instances = new ArrayList<UidEntitie>();
	private List<UidProgrammeElement> elements = new ArrayList<UidProgrammeElement>();
	
	public ProgrammeTDO() {
		super();
		// TODO Auto-generated constructor stub
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

	public List<UidEntitie> getSections() {
		return sections;
	}

	public void setSections(List<UidEntitie> sections) {
		this.sections = sections;
	}

	public List<UidEntitie> getServices() {
		return services;
	}

	public void setServices(List<UidEntitie> services) {
		this.services = services;
	}

	/*public List<UidEntitie> getInstances() {
		return instances;
	}

	public void setInstances(List<UidEntitie> instances) {
		this.instances = instances;
	}*/

	public List<UidProgrammeElement> getElements() {
		return elements;
	}

	public void setElements(List<UidProgrammeElement> elements) {
		this.elements = elements;
	}
	
	
}
