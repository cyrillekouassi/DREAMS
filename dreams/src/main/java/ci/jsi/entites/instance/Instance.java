package ci.jsi.entites.instance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ci.jsi.entites.beneficiaire.InstanceBeneficiaire;
import ci.jsi.entites.dataValue.DataValue;
import ci.jsi.entites.dataValueAudit.DataValueAudit;
import ci.jsi.entites.instanceAudit.InstanceAudit;
import ci.jsi.entites.organisation.Organisation;
import ci.jsi.entites.programme.Programme;
import ci.jsi.entites.utilisateur.UserApp;

@Entity
@Table(name="instance")
public class Instance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long instanceid;
	@Column(unique=true)
	private String uid;
	private Boolean deleted;
	@Column(name="datecreation")
	private Date dateCreation;
	@Column(name="dateactivite")
	private Date dateActivite;
	
	@JsonManagedReference
	@OneToMany(mappedBy="instance")
	private List<InstanceAudit> instanceAudits = new ArrayList<InstanceAudit>();
	
	@ManyToOne
	@JoinColumn(name="organisationid")
	@JsonBackReference
	private Organisation organisation;
	
	@OneToMany(mappedBy="instance")
	@JsonManagedReference
	private List<DataValueAudit> dataValueAudits = new ArrayList<DataValueAudit>();
	
	@ManyToOne
	@JoinColumn(name="userid")
	@JsonBackReference
	private UserApp user;
	
	@OneToMany(mappedBy="instance")
	@JsonManagedReference
	private List<DataValue> dataValues = new ArrayList<DataValue>();;
	
	@ManyToOne
	@JoinColumn(name="programmeId")
	@JsonBackReference
	private Programme programme;
	
	@OneToMany(mappedBy="instance", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	//@JsonBackReference
	@JsonManagedReference
	private List<InstanceBeneficiaire> instanceBeneficiaires = new ArrayList<InstanceBeneficiaire>();
	
	public Instance() {
		super();
	}
	public Instance(String uid, Boolean deleted, Date dateCreation) {
		super();
		this.uid = uid;
		this.deleted = deleted;
		this.dateCreation = dateCreation;
	}
	public Long getInstanceid() {
		return instanceid;
	}
	public void setInstanceid(Long instanceid) {
		this.instanceid = instanceid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public List<InstanceAudit> getInstanceAudits() {
		return instanceAudits;
	}
	public void setInstanceAudits(List<InstanceAudit> instanceAudits) {
		this.instanceAudits = instanceAudits;
	}
	public Organisation getOrganisation() {
		return organisation;
	}
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	public List<DataValueAudit> getDataValueAudits() {
		return dataValueAudits;
	}
	public void setDataValueAudits(List<DataValueAudit> dataValueAudits) {
		this.dataValueAudits = dataValueAudits;
	}
	public UserApp getUser() {
		return user;
	}
	public void setUser(UserApp user) {
		this.user = user;
	}
	public List<DataValue> getDataValues() {
		return dataValues;
	}
	public void setDataValues(List<DataValue> dataValues) {
		this.dataValues = dataValues;
	}
	public Programme getProgramme() {
		return programme;
	}
	public void setProgramme(Programme programme) {
		this.programme = programme;
	}
	public Date getDateActivite() {
		return dateActivite;
	}
	public void setDateActivite(Date dateActivite) {
		this.dateActivite = dateActivite;
	}
	public List<InstanceBeneficiaire> getInstanceBeneficiaires() {
		return instanceBeneficiaires;
	}
	public void setInstanceBeneficiaires(List<InstanceBeneficiaire> instanceBeneficiaires) {
		this.instanceBeneficiaires = instanceBeneficiaires;
	}
	
	
	
	
}
