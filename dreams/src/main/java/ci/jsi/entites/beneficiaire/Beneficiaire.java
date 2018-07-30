package ci.jsi.entites.beneficiaire;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;

import ci.jsi.entites.organisation.Organisation;

@Entity
public class Beneficiaire implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long beneficiaireid;
	@Column(unique=true)
	private String uid;
	private String name;
	private String firstName;
	@Column(unique=true)
	private String dreamsId;
	private String code;
	private String telephone;
	private Date dateNaissance;
	private Date dateEnrolement;
	private int ageEnrolement;
	@Column(name="datecreation")
	private Date dateCreation;
	@Column(name="dateupdate")
	private Date dateUpdate;
	
	@ManyToOne
	@JoinColumn(name="organisationid")
	@JsonManagedReference
	private Organisation organisation;
	
	@OneToMany(mappedBy="beneficiaire",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<InstanceBeneficiaire> instanceBeneficiaires = new ArrayList<InstanceBeneficiaire>();
	
	
	
	
	public Beneficiaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Beneficiaire(String uid, String name, String firstName, String id_dreams) {
		super();
		this.uid = uid;
		this.name = name;
		this.firstName = firstName;
		this.dreamsId = id_dreams;
	}

	public Long getBeneficiaireid() {
		return beneficiaireid;
	}

	public void setBeneficiaireid(Long beneficiaireid) {
		this.beneficiaireid = beneficiaireid;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getId_dreams() {
		return dreamsId;
	}

	public void setId_dreams(String id_dreams) {
		this.dreamsId = id_dreams;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public int getAgeEnrolement() {
		return ageEnrolement;
	}

	public void setAgeEnrolement(int ageEnrolement) {
		this.ageEnrolement = ageEnrolement;
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

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public List<InstanceBeneficiaire> getInstanceBeneficiaires() {
		return instanceBeneficiaires;
	}

	public void setInstanceBeneficiaires(List<InstanceBeneficiaire> instanceBeneficiaires) {
		this.instanceBeneficiaires = instanceBeneficiaires;
	}

	/*public String getDreamsId() {
		return dreamsId;
	}

	public void setDreamsId(String dreamsId) {
		this.dreamsId = dreamsId;
	}*/

	public Date getDateEnrolement() {
		return dateEnrolement;
	}

	public void setDateEnrolement(Date dateEnrolement) {
		this.dateEnrolement = dateEnrolement;
	}
	
	
}
