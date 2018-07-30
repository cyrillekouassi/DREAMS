package ci.jsi.entites.utilisateur;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
@Entity
@Table(name="utilisateurinfo")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class UtilisateurInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="utilisateurinfoid")
	private Long utilisateurInfoid;
	
	private String code;
	private String name;
	@Column(name="firstname")
	private String firtName;
	@Column(name="datecreation")
	private Date dateCreation;
	@Column(name="dateupdate")
	private Date dateUpdate;
	private String email;
	private String telephone;
	private String fonction;
	private String employeur;
	@Column(name="datenaissance")
	private String dateNaissance;
	
	
	public UtilisateurInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UtilisateurInfo(String name, String firtName, Date dateCreation, Date dateUpdate, String email,
			String telephone, String fonction, String employeur, String dateNaissance) {
		super();
		this.name = name;
		this.firtName = firtName;
		this.dateCreation = dateCreation;
		this.dateUpdate = dateUpdate;
		this.email = email;
		this.telephone = telephone;
		this.fonction = fonction;
		this.employeur = employeur;
		this.dateNaissance = dateNaissance;
	}
	public Long getUtilisateurInfoid() {
		return utilisateurInfoid;
	}
	public void setUtilisateurInfoid(Long utilisateurInfoid) {
		this.utilisateurInfoid = utilisateurInfoid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirtName() {
		return firtName;
	}
	public void setFirtName(String firtName) {
		this.firtName = firtName;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public String getEmployeur() {
		return employeur;
	}
	public void setEmployeur(String employeur) {
		this.employeur = employeur;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
}
