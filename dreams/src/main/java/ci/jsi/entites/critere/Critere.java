package ci.jsi.entites.critere;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ci.jsi.entites.element.Element;
import ci.jsi.entites.ensembleCritere.EnsembleCritere;

@Entity
public class Critere implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	private Long critereid;
	@Column(unique=true)
	private String uid;
	@Column(unique=true, nullable=false)
	private String name;
	@Column(unique=true, nullable=false)
	private String code;
	@Column(unique=true, nullable=false)
	private String operateur;
	@Column(unique=true, nullable=false)
	private String attendu;
	
	@ManyToOne
	@JoinColumn(name="elementid")
	//@JsonManagedReference
	@JsonBackReference
	private Element element;
	
	@ManyToMany(mappedBy="criteres", fetch=FetchType.LAZY)
	//@JsonBackReference
	@JsonManagedReference
	private List<EnsembleCritere> ensembleCriteres = new ArrayList<EnsembleCritere>();
	
	public Critere() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Critere(String uid, String name, String code, String operateur, String attendu) {
		super();
		this.uid = uid;
		this.name = name;
		this.code = code;
		this.operateur = operateur;
		this.attendu = attendu;
	}
	public Long getCritereid() {
		return critereid;
	}
	public void setCritereid(Long critereid) {
		this.critereid = critereid;
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
	public String getOperateur() {
		return operateur;
	}
	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}
	public String getAttendu() {
		return attendu;
	}
	public void setAttendu(String attendu) {
		this.attendu = attendu;
	}
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	public List<EnsembleCritere> getEnsembleCriteres() {
		return ensembleCriteres;
	}
	public void setEnsembleCriteres(List<EnsembleCritere> ensembleCriteres) {
		this.ensembleCriteres = ensembleCriteres;
	}
	
	
	
}
