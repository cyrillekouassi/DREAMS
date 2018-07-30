package ci.jsi.entites.ensembleOption;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import ci.jsi.entites.element.Element;
import ci.jsi.entites.option.Option;

@Entity
@Table(name="ensembleoption")
public class EnsembleOption implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long ensembleoptionid;
	@Column(unique=true)
	private String uid;
	private String typeValeur;
	@Column(unique=true, nullable=false)
	private String name;
	@Column(unique=true, nullable=false)
	private String code;
	private boolean multiple;
	
	@OneToMany(mappedBy="ensembleOption", fetch=FetchType.LAZY)
	//@JsonBackReference
	@JsonManagedReference
	private List<Option> options = new ArrayList<Option>();
	
	@OneToMany(mappedBy="ensembleOption", fetch=FetchType.LAZY)
	//@JsonBackReference
	@JsonManagedReference
	private List<Element> elements = new ArrayList<Element>();
	
	public EnsembleOption() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnsembleOption(String uid, String typeValeur, String name, String code, boolean multiple) {
		super();
		this.uid = uid;
		this.typeValeur = typeValeur;
		this.name = name;
		this.code = code;
		this.multiple = multiple;
	}

	public Long getEnsembleoptionid() {
		return ensembleoptionid;
	}

	public void setEnsembleoptionid(Long ensembleoptionid) {
		this.ensembleoptionid = ensembleoptionid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTypeValeur() {
		return typeValeur;
	}

	public void setTypeValeur(String typeValeur) {
		this.typeValeur = typeValeur;
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

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	
	
}
