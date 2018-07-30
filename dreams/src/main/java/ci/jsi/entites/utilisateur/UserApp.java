package ci.jsi.entites.utilisateur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import ci.jsi.entites.dataValue.DataValue;
import ci.jsi.entites.dataValueAudit.DataValueAudit;
import ci.jsi.entites.instance.Instance;
import ci.jsi.entites.instanceAudit.InstanceAudit;
import ci.jsi.entites.organisation.Organisation;
import ci.jsi.entites.roleUser.RoleUser;

@Entity
@Table(name="user")
@PrimaryKeyJoinColumn(name="userid")
public class UserApp extends UtilisateurInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(unique=true)
	private String uid;
	@Column(unique=true)
	private String username;
	private String password;
	@Column(name="datecreationpassword")
	private Date dateCreationPassword;
	@Column(name="datepasswordupdate")
	private Date datePasswordUpdate;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="userrole", joinColumns=@JoinColumn(name="userid"), inverseJoinColumns=@JoinColumn(name="roleuserid"))
	@JsonManagedReference 
	private List<RoleUser> roleUsers = new ArrayList<RoleUser>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="userorganisation", joinColumns=@JoinColumn(name="userid"), inverseJoinColumns=@JoinColumn(name="organisationid"))
	@JsonManagedReference
	private List<Organisation> organisations = new ArrayList<Organisation>();
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<InstanceAudit> instanceAudits = new ArrayList<InstanceAudit>();
	
	@OneToMany(mappedBy="user")
	@JsonManagedReference
	private List<DataValueAudit> dataValueAudits = new ArrayList<DataValueAudit>();
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<Instance> instances = new ArrayList<Instance>();
	
	@OneToMany(mappedBy="user")
	@JsonManagedReference
	private List<DataValue> dataValues = new ArrayList<DataValue>();
	
	
	public UserApp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserApp(String username, String password, Date dateCreationPassword, Date datePasswordUpdate) {
		super();
		this.username = username;
		this.password = password;
		this.dateCreationPassword = dateCreationPassword;
		this.datePasswordUpdate = datePasswordUpdate;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<InstanceAudit> getInstanceAudits() {
		return instanceAudits;
	}

	public void setInstanceAudits(List<InstanceAudit> instanceAudits) {
		this.instanceAudits = instanceAudits;
	}

	public List<DataValueAudit> getDataValueAudits() {
		return dataValueAudits;
	}

	public void setDataValueAudits(List<DataValueAudit> dataValueAudits) {
		this.dataValueAudits = dataValueAudits;
	}

	public List<Instance> getInstances() {
		return instances;
	}

	public void setInstances(List<Instance> instances) {
		this.instances = instances;
	}

	public List<DataValue> getDataValues() {
		return dataValues;
	}

	public void setDataValues(List<DataValue> dataValues) {
		this.dataValues = dataValues;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateCreationPassword() {
		return dateCreationPassword;
	}

	public void setDateCreationPassword(Date dateCreationPassword) {
		this.dateCreationPassword = dateCreationPassword;
	}

	public Date getDatePasswordUpdate() {
		return datePasswordUpdate;
	}

	public void setDatePasswordUpdate(Date datePasswordUpdate) {
		this.datePasswordUpdate = datePasswordUpdate;
	}

	public List<RoleUser> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(List<RoleUser> roleUsers) {
		this.roleUsers = roleUsers;
	}

	public List<Organisation> getOrganisations() {
		return organisations;
	}

	public void setOrganisations(List<Organisation> organisations) {
		this.organisations = organisations;
	}
	
	
	
}
