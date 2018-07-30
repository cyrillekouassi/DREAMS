package ci.jsi.entites.bareme;

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
import javax.persistence.OneToMany;

import ci.jsi.entites.couleur.Couleur;
import ci.jsi.entites.service.Servicess;

@Entity
public class Bareme  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long baremeid;
	private String uid;
	@Column(unique=true, nullable=false)
	private String name;
	@Column(unique=true, nullable=false)
	private String code;
	
	@OneToMany(mappedBy="bareme",fetch=FetchType.LAZY)
	private List<Servicess> services = new ArrayList<Servicess>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="couleurBareme", joinColumns=@JoinColumn(name="baremeid"),inverseJoinColumns=@JoinColumn(name="couleurid"))
	private List<Couleur> couleurs = new ArrayList<Couleur>();
	
	
	
	public Bareme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bareme(String uid, String name, String code) {
		super();
		this.uid = uid;
		this.name = name;
		this.code = code;
	}
	
	
	public Long getBaremeid() {
		return baremeid;
	}
	public void setBaremeid(Long baremeid) {
		this.baremeid = baremeid;
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
	public List<Servicess> getServices() {
		return services;
	}
	public void setServices(List<Servicess> services) {
		this.services = services;
	}
	public List<Couleur> getCouleurs() {
		return couleurs;
	}
	public void setCouleurs(List<Couleur> couleurs) {
		this.couleurs = couleurs;
	}
	
	
	
	
}
