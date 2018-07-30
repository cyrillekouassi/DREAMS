package ci.jsi.entites.service;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ci.jsi.entites.bareme.Bareme;
import ci.jsi.entites.ensembleCritere.EnsembleCritere;
import ci.jsi.entites.programme.Programme;

@Entity
@Table(name="service")
public class Servicess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long serviceid;
	@Column(unique=true)
	private String uid;
	@Column(unique=true, nullable=false)
	private String name;
	@Column(unique=true, nullable=false)
	private String code;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="serviceProgramme", joinColumns=@JoinColumn(name="serviceid"),inverseJoinColumns=@JoinColumn(name="programmeId"))
	private List<Programme> programmes = new ArrayList<Programme>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="serviceEnsemblecritere", joinColumns=@JoinColumn(name="serviceid"),inverseJoinColumns=@JoinColumn(name="ensemblecritereid"))
	private List<EnsembleCritere> ensembleCriteres = new ArrayList<EnsembleCritere>();
	
	@ManyToOne
	@JoinColumn(name="baremeid")
	private Bareme bareme;
	
	public Servicess() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Servicess(String uid, String name, String code) {
		super();
		this.uid = uid;
		this.name = name;
		this.code = code;
	}


	public Long getServiceid() {
		return serviceid;
	}


	public void setServiceid(Long serviceid) {
		this.serviceid = serviceid;
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


	public List<Programme> getProgrammes() {
		return programmes;
	}


	public void setProgrammes(List<Programme> programmes) {
		this.programmes = programmes;
	}


	public List<EnsembleCritere> getEnsembleCriteres() {
		return ensembleCriteres;
	}


	public void setEnsembleCriteres(List<EnsembleCritere> ensembleCriteres) {
		this.ensembleCriteres = ensembleCriteres;
	}


	public Bareme getBareme() {
		return bareme;
	}


	public void setBareme(Bareme bareme) {
		this.bareme = bareme;
	}
	
	
	
}
