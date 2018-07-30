package ci.jsi.entites.programme;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import ci.jsi.entites.instance.Instance;
import ci.jsi.entites.section.Section;
import ci.jsi.entites.service.Servicess;

@Entity
public class Programme implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long programmeid;
	@Column(unique=true)
	private String uid;
	@Column(unique=true, nullable=false)
	private String name;
	@Column(unique=true, nullable=false)
	private String code;
	
	@OneToMany(mappedBy="programme",fetch=FetchType.LAZY)
	//@JsonBackReference
	@JsonManagedReference
	private List<Section> sections = new ArrayList<Section>();
	
	@ManyToMany(mappedBy="programmes",fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<Servicess> services = new ArrayList<Servicess>();
	
	@OneToMany(mappedBy="programme", fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<Instance> instances = new ArrayList<Instance>();
	
	@OneToMany(mappedBy="programme",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<ProgrammeElement> programmeElements = new ArrayList<ProgrammeElement>();
	
	public Programme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Programme(String uid, String name, String code) {
		super();
		this.uid = uid;
		this.name = name;
		this.code = code;
	}
	public Long getProgrammeid() {
		return programmeid;
	}
	public void setProgrammeid(Long programmeid) {
		this.programmeid = programmeid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public List<Section> getSections() {
		return sections;
	}
	public List<ProgrammeElement> getProgrammeElements() {
		return programmeElements;
	}
	public void setProgrammeElements(List<ProgrammeElement> programmeElements) {
		this.programmeElements = programmeElements;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	public List<Servicess> getServices() {
		return services;
	}
	public void setServices(List<Servicess> services) {
		this.services = services;
	}
	public List<Instance> getInstances() {
		return instances;
	}
	public void setInstances(List<Instance> instances) {
		this.instances = instances;
	}
	
	
}
