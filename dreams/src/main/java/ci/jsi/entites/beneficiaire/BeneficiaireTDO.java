package ci.jsi.entites.beneficiaire;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ci.jsi.initialisation.UidEntitie;
import ci.jsi.initialisation.UidInstance;

public class BeneficiaireTDO {
	
	
	private String id;
	private String name;
	private String firstName;
	private String id_dreams;
	private String code;
	private String telephone;
	private String dateNaissance;
	private String ageEnrolement;
	private String dateEnrolement;
	private String dateCreation;
	private String dateUpdate;

	private UidEntitie organisation;
	private List<UidInstance> instance = new ArrayList<UidInstance>();
	
	public BeneficiaireTDO() {
		
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getId_dreams() {
		return id_dreams;
	}

	public void setId_dreams(String id_dreams) {
		this.id_dreams = id_dreams;
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

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getAgeEnrolement() {
		return ageEnrolement;
	}

	public void setAgeEnrolement(String ageEnrolement) {
		this.ageEnrolement = ageEnrolement;
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

	public UidEntitie getOrganisation() {
		return organisation;
	}

	public void setOrganisation(UidEntitie organisation) {
		this.organisation = organisation;
	}

	public List<UidInstance> getInstance() {
		return instance;
	}

	public void setInstance(List<UidInstance> instance) {
		this.instance = instance;
	}

	public String getDateEnrolement() {
		return dateEnrolement;
	}

	public void setDateEnrolement(String dateEnrolement) {
		this.dateEnrolement = dateEnrolement;
	}
	
	
}
