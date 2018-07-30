package ci.jsi.entites.organisation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import ci.jsi.entites.beneficiaire.Beneficiaire;
import ci.jsi.entites.instance.Instance;
import ci.jsi.entites.instanceAudit.InstanceAudit;
import ci.jsi.entites.organisationLevel.OrganisationLevel;
import ci.jsi.entites.rapport.Rapport;
import ci.jsi.entites.utilisateur.UserApp;

@Entity
public class Organisation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long organisationid;
	@Column(unique=true)
	private String uid;
	private String name;
	private String sigle;
	private String code;
	@Column(name="datecreation")
	private Date dateCreation;
	@Column(name="dateupdate")
	private Date dateUpdate;
	@Column(name="datefermeture")
	private Date dateFermeture;
	private String description;
	private String email;
	private String telephone;
	private String region;
	private String departement;
	private String sousPrefecture;
	private String commune;
	private String quartier;
	private String partenaire;
	private String organisationLocal;
	private int level;
	//private String districtSanitaire;
	//private String longitude;
	//private String latitude;
	
	//@ManyToMany(mappedBy="organisations")
	@ManyToMany(mappedBy="organisations",fetch=FetchType.LAZY)
	@JsonManagedReference
	//@JsonBackReference
	private List<UserApp> users = new ArrayList<UserApp>();
	
	@ManyToOne
	@JoinColumn(name="organisationlevelid")
	//@JsonManagedReference
	//@JsonBackReference
	private OrganisationLevel organisationLevels;
	
	@OneToMany(mappedBy="organisation", fetch=FetchType.LAZY)
	//@JsonManagedReference
	//@JsonBackReference
	private List<InstanceAudit> instanceAudits = new ArrayList<InstanceAudit>();
	
	@OneToMany(mappedBy="organisation",fetch=FetchType.LAZY)
	//@JsonManagedReference
	//@JsonBackReference
	private List<Instance> instances = new ArrayList<Instance>();
	
	@OneToMany(mappedBy="parent",fetch=FetchType.LAZY)
	//@JsonManagedReference
	private List<Organisation> childrens = new ArrayList<Organisation>();
	
	@OneToMany(mappedBy="organisation",fetch=FetchType.LAZY)
	//@JsonBackReference
	private List<Beneficiaire> beneficiaires = new ArrayList<Beneficiaire>();
	
	@OneToMany(mappedBy="organisation",fetch=FetchType.LAZY)
	//@JsonManagedReference
	private List<Rapport> rapports = new ArrayList<Rapport>();
	
	
	@ManyToOne
	@JoinColumn(name="parentid")
	//@JsonBackReference
	private Organisation parent;
	
	
	public Organisation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Organisation(String uid, String name, String sigle, Long parentid, String code, Date dateCreation,
			Date dateUpdate, Date dateFermeture, String descirption, String email, String telephone, int level) {
		this.uid = uid;
		this.name = name;
		this.sigle = sigle;
		this.code = code;
		this.dateCreation = dateCreation;
		this.dateUpdate = dateUpdate;
		this.dateFermeture = dateFermeture;
		this.description = descirption;
		this.email = email;
		this.telephone = telephone;
		this.level = level;
	}
	public Long getOrganisationid() {
		return organisationid;
	}
	public void setOrganisationid(Long organisationid) {
		this.organisationid = organisationid;
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
	public String getSigle() {
		return sigle;
	}
	public void setSigle(String sigle) {
		this.sigle = sigle;
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
	public Date getDateFermeture() {
		return dateFermeture;
	}
	public void setDateFermeture(Date dateFermeture) {
		this.dateFermeture = dateFermeture;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String descirption) {
		this.description = descirption;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public List<UserApp> getUsers() {
		return users;
	}
	public void setUsers(List<UserApp> users) {
		this.users = users;
	}
	public OrganisationLevel getOrganisationLevels() {
		return organisationLevels;
	}
	public void setOrganisationLevels(OrganisationLevel organisationLevels) {
		this.level = organisationLevels.getLevel();
		this.organisationLevels = organisationLevels;
	}
	public List<InstanceAudit> getInstanceAudits() {
		return instanceAudits;
	}
	public void setInstanceAudits(List<InstanceAudit> instanceAudits) {
		this.instanceAudits = instanceAudits;
	}
	public List<Instance> getInstances() {
		return instances;
	}
	public void setInstances(List<Instance> instances) {
		this.instances = instances;
	}
	public List<Organisation> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<Organisation> childrens) {
		this.childrens = childrens;
	}
	public Organisation getParent() {
		return parent;
	}
	public void setParent(Organisation parent) {
		this.parent = parent;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public String getSousPrefecture() {
		return sousPrefecture;
	}
	public void setSousPrefecture(String sousPrefecture) {
		this.sousPrefecture = sousPrefecture;
	}
	public String getCommune() {
		return commune;
	}
	public void setCommune(String commune) {
		this.commune = commune;
	}
	public String getQuartier() {
		return quartier;
	}
	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}
	public String getPartenaire() {
		return partenaire;
	}
	public void setPartenaire(String partenaire) {
		this.partenaire = partenaire;
	}
	public String getOrganisationLocal() {
		return organisationLocal;
	}
	public void setOrganisationLocal(String organisationLocal) {
		this.organisationLocal = organisationLocal;
	}
	public List<Beneficiaire> getBeneficiaires() {
		return beneficiaires;
	}
	public void setBeneficiaires(List<Beneficiaire> beneficiaires) {
		this.beneficiaires = beneficiaires;
	}
	public List<Rapport> getRapports() {
		return rapports;
	}
	public void setRapports(List<Rapport> rapports) {
		this.rapports = rapports;
	}
	
	
	
	
}
