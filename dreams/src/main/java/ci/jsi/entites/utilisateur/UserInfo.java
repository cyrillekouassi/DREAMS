package ci.jsi.entites.utilisateur;

import java.util.ArrayList;
import java.util.List;

import ci.jsi.initialisation.UidEntitie;

public class UserInfo {

	private String id;
	private String username;
	private String name;
	private String firtName;
	private String code;
	private List<UidEntitie> organisations = new ArrayList<UidEntitie>();
	private List<UidEntitie> roleUsers = new ArrayList<UidEntitie>();
	private List<String> roleDefinis = new ArrayList<String>();
	
	
	public UserInfo() {
		
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


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public List<UidEntitie> getOrganisations() {
		return organisations;
	}


	public void setOrganisations(List<UidEntitie> organisations) {
		this.organisations = organisations;
	}


	public List<UidEntitie> getRoleUsers() {
		return roleUsers;
	}


	public void setRoleUsers(List<UidEntitie> roleUsers) {
		this.roleUsers = roleUsers;
	}


	public List<String> getRoleDefinis() {
		return roleDefinis;
	}


	public void setRoleDefinis(List<String> roleDefinis) {
		this.roleDefinis = roleDefinis;
	}
	
	 
	
}
