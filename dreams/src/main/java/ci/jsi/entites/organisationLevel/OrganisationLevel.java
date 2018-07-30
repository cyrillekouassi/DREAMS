package ci.jsi.entites.organisationLevel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ci.jsi.entites.organisation.Organisation;

@Entity
@Table(name="organisationlevel")
public class OrganisationLevel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long organisationlevelid;
	@Column(unique=true)
	private String uid;
	@Column(unique=true)
	private String name;
	@Column(unique=true)
	private int level;
	@Column(unique=true)
	private String code;
	private String description;
	
	@OneToMany(mappedBy="organisationLevels",fetch=FetchType.LAZY)
	@JsonBackReference
	private List<Organisation> organisations = new ArrayList<Organisation>();
	
	public OrganisationLevel() {
		super();
		// TODO Auto-generated constructor stub
	}



	public OrganisationLevel(String uid, String name, int level, String code, String description) {
		super();
		this.uid = uid;
		this.name = name;
		this.level = level;
		this.code = code;
		this.description = description;
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



	public int getLevel() {
		return level;
	}



	public void setLevel(int level) {
		this.level = level;
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



	public List<Organisation> getOrganisations() {
		return organisations;
	}



	public void setOrganisations(List<Organisation> organisations) {
		this.organisations = organisations;
	}
	
	
	
	
}
