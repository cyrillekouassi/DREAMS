package ci.jsi.entites.ensembleCritere;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ci.jsi.entites.critere.Critere;
import ci.jsi.entites.service.Servicess;

@Entity
@Table(name="ensemblecritere")
public class EnsembleCritere implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long ensemblecritereid;
	@Column(unique=true)
	private String uid;
	@Column(unique=true, nullable=false)
	private String name;
	@Column(unique=true, nullable=false)
	private String code;
	@Column(unique=true, nullable=false)
	private String combinaison;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="critereEnsemble", joinColumns=@JoinColumn(name="ensemblecritereid"), inverseJoinColumns=@JoinColumn(name="critereid"))
	private List<Critere> criteres = new ArrayList<Critere>();
	
	@ManyToMany(mappedBy="ensembleCriteres", fetch=FetchType.LAZY)
	private List<Servicess> services = new ArrayList<Servicess>();
	
	public EnsembleCritere(String uid, String name, String code, String combinaison) {
		super();
		this.uid = uid;
		this.name = name;
		this.code = code;
		this.combinaison = combinaison;
	}


	public EnsembleCritere() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getEnsemblecritereid() {
		return ensemblecritereid;
	}


	public void setEnsemblecritereid(Long ensemblecritereid) {
		this.ensemblecritereid = ensemblecritereid;
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


	public String getCombinaison() {
		return combinaison;
	}


	public void setCombinaison(String combinaison) {
		this.combinaison = combinaison;
	}


	public List<Critere> getCriteres() {
		return criteres;
	}


	public void setCriteres(List<Critere> criteres) {
		this.criteres = criteres;
	}


	public List<Servicess> getServices() {
		return services;
	}


	public void setServices(List<Servicess> services) {
		this.services = services;
	}
	
	
	
}
