package ci.jsi.entites.rolesDefinis;

import java.util.ArrayList;
import java.util.List;

import ci.jsi.initialisation.UidEntitie;

public class RolesDefinisTDO {
	
	private String id;
	private String autorisation;
	
	private List<UidEntitie> rolesUsers = new ArrayList<UidEntitie>();

	public RolesDefinisTDO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAutorisation() {
		return autorisation;
	}

	public void setAutorisation(String autorisation) {
		this.autorisation = autorisation;
	}

	public List<UidEntitie> getRolesUsers() {
		return rolesUsers;
	}

	public void setRolesUsers(List<UidEntitie> rolesUsers) {
		this.rolesUsers = rolesUsers;
	}
	
	
	
}
