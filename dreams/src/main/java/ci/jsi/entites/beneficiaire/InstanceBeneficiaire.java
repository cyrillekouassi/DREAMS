package ci.jsi.entites.beneficiaire;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ci.jsi.entites.instance.Instance;


@Entity
public class InstanceBeneficiaire {
	
	@Id @GeneratedValue
	@Column(name="Instancebeneficiareid")
	private Long InstanceBeneficiareid;
	
	@ManyToOne
	@JoinColumn(name="instanceid")
	@JsonBackReference
	private Instance instance;
	
	@ManyToOne
	@JoinColumn(name="beneficiaireid")
	@JsonBackReference
	private Beneficiaire beneficiaire;
	
	@Column(name="dateaction")
	private Date dateAction;
	
	private String codeId;
	private int ordre;

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public InstanceBeneficiaire() {
		
	}

	public InstanceBeneficiaire(Date dateAction) {
		super();
		this.dateAction = dateAction;
	}

	public Instance getInstance() {
		return instance;
	}

	public void setInstance(Instance instance) {
		this.instance = instance;
	}

	public Beneficiaire getBeneficiaire() {
		return beneficiaire;
	}

	public void setBeneficiaire(Beneficiaire beneficiaire) {
		this.beneficiaire = beneficiaire;
	}

	public Date getDateAction() {
		return dateAction;
	}

	public void setDateAction(Date dateAction) {
		this.dateAction = dateAction;
	}

	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}
	
	
}
