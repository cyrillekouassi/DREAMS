package ci.jsi.entites.section;

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ci.jsi.entites.element.Element;
import ci.jsi.entites.programme.Programme;

@Entity
public class Section implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long sectionid;
	@Column(unique=true)
	private String uid;
	@Column(unique=true, nullable=false)
	private String name;
	@Column(unique=true, nullable=false)
	private String code;
	
	@ManyToOne
	@JoinColumn(name="programmeid")
	//@JsonManagedReference
	@JsonBackReference
	private Programme programme;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="elementSection",joinColumns=@JoinColumn(name="sectionid"), inverseJoinColumns=@JoinColumn(name="elementid"))
	//@JsonBackReference
	@JsonManagedReference
	private List<Element> elements = new ArrayList<Element>();
	
	public Section() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Section(String uid, String name, String code) {
		super();
		this.uid = uid;
		this.name = name;
		this.code = code;
	}


	public Long getSectionid() {
		return sectionid;
	}


	public void setSectionid(Long sectionid) {
		this.sectionid = sectionid;
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


	public Programme getProgramme() {
		return programme;
	}


	public void setProgramme(Programme programme) {
		this.programme = programme;
	}


	public List<Element> getElements() {
		return elements;
	}


	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

}
