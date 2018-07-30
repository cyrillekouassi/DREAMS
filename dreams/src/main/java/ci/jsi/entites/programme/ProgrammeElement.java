package ci.jsi.entites.programme;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ci.jsi.entites.element.Element;

@Entity
public class ProgrammeElement implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name="programmeelementid")
	private Long programmeElementid;
	
	@ManyToOne
	@JoinColumn(name="programmeid")
	@JsonBackReference
	private Programme programme;
	
	@ManyToOne
	@JoinColumn(name="elementid")
	//@JsonManagedReference
	@JsonBackReference
	private Element element;
	
	@Column(name="afficherapport")
	private boolean afficheRapport;
	
	
	public ProgrammeElement() {
		super();
	}
	public ProgrammeElement(boolean afficheRapport) {
		super();
		this.afficheRapport = afficheRapport;
	}
	public Long getProgrammeElementid() {
		return programmeElementid;
	}
	public void setProgrammeElementid(Long programmeElementid) {
		this.programmeElementid = programmeElementid;
	}
	public Programme getProgramme() {
		return programme;
	}
	public void setProgramme(Programme programme) {
		this.programme = programme;
	}
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	public boolean isAfficheRapport() {
		return afficheRapport;
	}
	public void setAfficheRapport(boolean afficheRapport) {
		this.afficheRapport = afficheRapport;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (afficheRapport ? 1231 : 1237);
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		result = prime * result + ((programme == null) ? 0 : programme.hashCode());
		result = prime * result + ((programmeElementid == null) ? 0 : programmeElementid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProgrammeElement other = (ProgrammeElement) obj;
		if (afficheRapport != other.afficheRapport)
			return false;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		if (programme == null) {
			if (other.programme != null)
				return false;
		} else if (!programme.equals(other.programme))
			return false;
		if (programmeElementid == null) {
			if (other.programmeElementid != null)
				return false;
		} else if (!programmeElementid.equals(other.programmeElementid))
			return false;
		return true;
	}
	
}
