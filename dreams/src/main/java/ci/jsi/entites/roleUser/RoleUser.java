package ci.jsi.entites.roleUser;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import ci.jsi.entites.rolesDefinis.RolesDefinis;
import ci.jsi.entites.utilisateur.UserApp;

@Entity
@Table(name="Roleuser")
public class RoleUser implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long roleuserid;
	@Column(unique=true)
	private String uid;
	@Column(unique=true)
	private String name;
	private String code;
	private String description;
	@Column(name="datecreation")
	private Date dateCreation;
	@Column(name="dateupdate")
	private Date dateUpdate;
	

	@ManyToMany
	@JoinTable(name="roleuserDefinie", joinColumns=@JoinColumn(name="roleuserid"), inverseJoinColumns=@JoinColumn(name="roledefinieid"))
	private List<RolesDefinis> rolesDefinies = new ArrayList<RolesDefinis>();
	
	@ManyToMany(mappedBy="roleUsers",fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<UserApp> users = new ArrayList<UserApp>();
	
	
	public RoleUser(String uid, String name, String code, String description, Date dateCreation, Date dateUpdate)
			 {
		this.uid = uid;
		this.name = name;
		this.code = code;
		this.description = description;
		this.dateCreation = dateCreation;
		this.dateUpdate = dateUpdate;
	}
	public RoleUser() {
		
	}
	public Long getRoleuserid() {
		return roleuserid;
	}
	public void setRoleuserid(Long roleuserid) {
		this.roleuserid = roleuserid;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public List<RolesDefinis> getRoledefinie() {
		return rolesDefinies;
	}
	public void setRoledefinie(List<RolesDefinis> roledefinie) {
		this.rolesDefinies = roledefinie;
	}
	
	public List<UserApp> getUsers() {
		return users;
	}
	public void setUsers(List<UserApp> users) {
		this.users = users;
	}
	
	
	
}
