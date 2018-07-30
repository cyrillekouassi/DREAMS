package ci.jsi.entites.rapport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ci.jsi.entites.element.Element;
import ci.jsi.entites.option.Option;
import ci.jsi.entites.organisation.Organisation;

@Entity
public class Rapport {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long rapportid;
	
	@ManyToOne
	@JoinColumn(name="organisationid")
	private Organisation organisation;
	
	@ManyToOne
	@JoinColumn(name="elementid")
	private Element element;
	@ManyToOne
	@JoinColumn(name="optionid")
	private Option option;
	
	private String periode;
	private int valeurs;
	
	public Rapport() {
		
	}

	public Rapport(Organisation organisation, Element element, Option option, String periode, int valeurs) {
		super();
		this.organisation = organisation;
		this.element = element;
		this.option = option;
		this.periode = periode;
		this.valeurs = valeurs;
	}

	public Long getRapportid() {
		return rapportid;
	}

	public void setRapportid(Long rapportid) {
		this.rapportid = rapportid;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public int getValeurs() {
		return valeurs;
	}

	public void setValeurs(int valeurs) {
		this.valeurs = valeurs;
	}
	
	
}
