package ci.jsi.entites.utilisateur;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import ci.jsi.initialisation.UidEntitie;

public class UserTDO {

	private String id;
	private String username;
	private String password;
	private String name;
	private String firtName;
	private String code;
	private String dateCreation;
	private String dateUpdate;
	private String email;
	private String telephone;
	private String fonction;
	private String employeur;
	private String dateNaissance;
	private List<UidEntitie> roleUsers = new ArrayList<UidEntitie>();
	private List<UidEntitie> organisations = new ArrayList<UidEntitie>();
	//private List<UidEntitie> instances = new ArrayList<UidEntitie>();
	
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
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
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public String getEmployeur() {
		return employeur;
	}
	public void setEmployeur(String employeur) {
		this.employeur = employeur;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirtName() {
		return firtName;
	}
	public void setFirtName(String firtName) {
		this.firtName = firtName;
	}
	public List<UidEntitie> getRoleUsers() {
		return roleUsers;
	}
	public void setRoleUsers(List<UidEntitie> roleUsers) {
		this.roleUsers = roleUsers;
	}
	public List<UidEntitie> getOrganisations() {
		return organisations;
	}
	public void setOrganisations(List<UidEntitie> organisations) {
		this.organisations = organisations;
	}
	/*public List<UidEntitie> getInstances() {
		return instances;
	}
	public void setInstances(List<UidEntitie> instances) {
		this.instances = instances;
	}*/
	
	
	
	
	
}
