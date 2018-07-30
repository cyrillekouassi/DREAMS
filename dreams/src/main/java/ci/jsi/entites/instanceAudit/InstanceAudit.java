package ci.jsi.entites.instanceAudit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ci.jsi.entites.instance.Instance;
import ci.jsi.entites.organisation.Organisation;
import ci.jsi.entites.utilisateur.UserApp;

@Entity
@Table(name="instanceaudit")
public class InstanceAudit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long instanceauditid;
	@Column(name="dateaction")
	private Date dateAction;
	@Column(name="typeaction")
	private String typeAction;
	
	@ManyToOne
	@JoinColumn(name="userid")
	@JsonBackReference
	private UserApp user;
	
	@ManyToOne
	@JoinColumn(name="organisationid")
	@JsonBackReference
	private Organisation organisation;
	
	@ManyToOne
	@JoinColumn(name="instanceid")
	@JsonBackReference
	private Instance instance;

	public InstanceAudit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InstanceAudit(Date dateAction, String typeAction) {
		super();
		this.dateAction = dateAction;
		this.typeAction = typeAction;
	}

	public Long getInstanceauditid() {
		return instanceauditid;
	}

	public void setInstanceauditid(Long instanceauditid) {
		this.instanceauditid = instanceauditid;
	}

	public Date getDateAction() {
		return dateAction;
	}

	public void setDateAction(Date dateAction) {
		this.dateAction = dateAction;
	}

	public String getTypeAction() {
		return typeAction;
	}

	public void setTypeAction(String typeAction) {
		this.typeAction = typeAction;
	}

	public UserApp getUser() {
		return user;
	}

	public void setUser(UserApp user) {
		this.user = user;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Instance getInstance() {
		return instance;
	}

	public void setInstance(Instance instance) {
		this.instance = instance;
	}
	
	
	
}














