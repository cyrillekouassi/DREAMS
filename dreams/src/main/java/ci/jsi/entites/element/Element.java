package ci.jsi.entites.element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ci.jsi.entites.critere.Critere;
import ci.jsi.entites.dataValue.DataValue;
import ci.jsi.entites.ensembleOption.EnsembleOption;
import ci.jsi.entites.programme.ProgrammeElement;
import ci.jsi.entites.rapport.Rapport;
import ci.jsi.entites.section.Section;

@Entity
public class Element implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long elementid;
	@Column(unique=true)
	private String uid;
	@Column(unique=true, nullable=false)
	private String name;
	@Column(unique=true, nullable=false)
	private String code;
	@Column(name="datecreation")
	private Date dateCreation;
	@Column(name="dateupdate")
	private Date dateUpdate;
	private String description;
	@Column(name="typevaleur")
	private String typeValeur;
	
	@ManyToOne
	@JoinColumn(name="ensembleoptionid")
	//@JsonManagedReference
	@JsonBackReference
	private EnsembleOption ensembleOption;
	
	@OneToMany(mappedBy="element",fetch=FetchType.LAZY)
	//@JsonBackReference
	@JsonManagedReference
	private List<Critere> critere = new ArrayList<Critere>();
	
	@OneToMany(mappedBy="element", fetch=FetchType.LAZY)
	//@JsonBackReference
	@JsonManagedReference
	private List<DataValue> dataValues = new ArrayList<DataValue>();
	
	@ManyToMany(mappedBy="elements",fetch=FetchType.LAZY)
	//@JsonBackReference
	@JsonManagedReference
	private List<Section> sections = new ArrayList<Section>();
	
	@OneToMany(mappedBy="element", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	//@JsonBackReference
	@JsonManagedReference
	private List<ProgrammeElement> programmeElements = new ArrayList<ProgrammeElement>();
	
	@OneToMany(mappedBy="element", fetch=FetchType.LAZY)
	private List<Rapport> rapports = new ArrayList<Rapport>();
	
	public List<Rapport> getRapports() {
		return rapports;
	}
	public void setRapports(List<Rapport> rapports) {
		this.rapports = rapports;
	}
	public Long getElementid() {
		return elementid;
	}	
	public void setElementid(Long elementid) {
		this.elementid = elementid;
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
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTypeValeur() {
		return typeValeur;
	}
	public void setTypeValeur(String typeValeur) {
		this.typeValeur = typeValeur;
	}
	public EnsembleOption getEnsembleOption() {
		return ensembleOption;
	}
	public void setEnsembleOption(EnsembleOption ensembleOption) {
		this.ensembleOption = ensembleOption;
	}
	public List<Critere> getCritere() {
		return critere;
	}
	public void setCritere(List<Critere> critere) {
		this.critere = critere;
	}
	public List<DataValue> getDataValues() {
		return dataValues;
	}
	public void setDataValues(List<DataValue> dataValues) {
		this.dataValues = dataValues;
	}
	
	
	public Element() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Element(String uid, String name, String code, Date dateCreation, Date dateUpdate, String description,
			String typeValeur) {
		super();
		this.uid = uid;
		this.name = name;
		this.code = code;
		this.dateCreation = dateCreation;
		this.dateUpdate = dateUpdate;
		this.description = description;
		this.typeValeur = typeValeur;
	}
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	public List<ProgrammeElement> getProgrammeElements() {
		return programmeElements;
	}
	public void setProgrammeElements(List<ProgrammeElement> programmeElements) {
		this.programmeElements = programmeElements;
	}
	
	
	
}
