package ci.jsi.entites.rolesDefinis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ci.jsi.entites.roleUser.RoleUser;


@Entity
@Table(name="rolesdefinis")
public class RolesDefinis implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long roledefinieid;
	@Column(unique=true)
	private String uid;
	private String autorisation;
	
	@ManyToMany(mappedBy="rolesDefinies",fetch=FetchType.LAZY)
	private List<RoleUser> rolesUsers = new ArrayList<RoleUser>();
	
	
	public RolesDefinis() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RolesDefinis(String uid, String autorisation) {
		super();
		this.uid = uid;
		this.autorisation = autorisation;
	}

	public List<RoleUser> getRolesUsers() {
		return rolesUsers;
	}

	public void setRolesUsers(List<RoleUser> rolesUsers) {
		this.rolesUsers = rolesUsers;
	}

	
	public Long getRoledefinieid() {
		return roledefinieid;
	}

	public void setRoledefinieid(Long roledefinieid) {
		this.roledefinieid = roledefinieid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAutorisation() {
		return autorisation;
	}

	public void setAutorisation(String autorisation) {
		this.autorisation = autorisation;
	}
	
	
	
}
