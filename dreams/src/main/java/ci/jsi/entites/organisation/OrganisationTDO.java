package ci.jsi.entites.organisation;


import java.util.ArrayList;
import java.util.List;

import ci.jsi.initialisation.UidEntitie;

public class OrganisationTDO {
	
	private String id;
	private String name;
	private String sigle;
	private String code;
	private String dateCreation;
	private String dateUpdate;
	private String dateFermeture;
	private String description;
	private String email;
	private String telephone;
	private int level;
	private String region;
	private String departement;
	private String sousPrefecture;
	private String commune;
	private String quartier;
	private String partenaire;
	private String organisationLocal;
	
	private List<UidEntitie> users = new ArrayList<UidEntitie>();
	//private List<UidEntitie> instances = new ArrayList<UidEntitie>();
	private List<UidEntitie> childrens = new ArrayList<UidEntitie>();
	private UidEntitie parent;
	
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
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public String getDateFermeture() {
		return dateFermeture;
	}
	public void setDateFermeture(String dateFermeture) {
		this.dateFermeture = dateFermeture;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public List<UidEntitie> getUsers() {
		return users;
	}
	public void setUsers(List<UidEntitie> users) {
		this.users = users;
	}
	
	/*public List<UidEntitie> getInstances() {
		return instances;
	}
	public void setInstances(List<UidEntitie> instances) {
		this.instances = instances;
	}*/
	public List<UidEntitie> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<UidEntitie> childrens) {
		this.childrens = childrens;
	}
	public UidEntitie getParent() {
		return parent;
	}
	public void setParent(UidEntitie parent) {
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
 
    
	
}
