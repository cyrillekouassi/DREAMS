package ci.jsi.entites.roleUser;

import java.util.ArrayList;
import java.util.List;

import ci.jsi.initialisation.UidEntitie;

public class RoleUserTDO {

	private String id;
	private String name;
	private String code;
	private String description;
	private String dateCreation;
	private String dateUpdate;
	
	private List<UidEntitie> rolesDefinies = new ArrayList<UidEntitie>();
	private List<UidEntitie> users = new ArrayList<UidEntitie>();
	
	public RoleUserTDO() {
		super();
	}
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public List<UidEntitie> getRolesDefinies() {
		return rolesDefinies;
	}
	public void setRolesDefinies(List<UidEntitie> rolesDefinies) {
		this.rolesDefinies = rolesDefinies;
	}
	public List<UidEntitie> getUsers() {
		return users;
	}
	public void setUsers(List<UidEntitie> users) {
		this.users = users;
	}
	
	
	
}
