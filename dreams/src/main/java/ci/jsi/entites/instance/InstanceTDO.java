package ci.jsi.entites.instance;

import java.util.ArrayList;
import java.util.List;

import ci.jsi.initialisation.UidBeneficiaire;

public class InstanceTDO {

	private String id;
	private String dateCreation;
	
	private String organisation;
	private String user;
	private String programme;
	private String dateActivite;
	private List<UidBeneficiaire> beneficiaires = new ArrayList<UidBeneficiaire>();
	
	
	public InstanceTDO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getOrganisation() {
		return organisation;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getProgramme() {
		return programme;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	public String getDateActivite() {
		return dateActivite;
	}
	public void setDateActivite(String dateActivite) {
		this.dateActivite = dateActivite;
	}
	public List<UidBeneficiaire> getBeneficiaires() {
		return beneficiaires;
	}
	public void setBeneficiaires(List<UidBeneficiaire> beneficiaires) {
		this.beneficiaires = beneficiaires;
	}
	
	
}
