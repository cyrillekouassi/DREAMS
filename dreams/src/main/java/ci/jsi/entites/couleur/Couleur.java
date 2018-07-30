package ci.jsi.entites.couleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import ci.jsi.entites.bareme.Bareme;

@Entity
public class Couleur  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long couleurid;
	private String uid;
	@Column(unique=true, nullable=false)
	private String name;
	@Column(unique=true, nullable=false)
	private String code;
	private String htmlCouleur;
	
	@ManyToMany(mappedBy="couleurs", fetch=FetchType.LAZY)
	private List<Bareme> baremes = new ArrayList<Bareme>();
	
	public Couleur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Couleur(String uid, String name, String code,String htmlCouleur) {
		super();
		this.uid = uid;
		this.name = name;
		this.code = code;
		this.htmlCouleur = htmlCouleur;
	}
	public Long getCouleurid() {
		return couleurid;
	}
	public void setCouleurid(Long couleurid) {
		this.couleurid = couleurid;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getHtmlCouleur() {
		return htmlCouleur;
	}
	public void setHtmlCouleur(String htmlCouleur) {
		this.htmlCouleur = htmlCouleur;
	}
	public List<Bareme> getBaremes() {
		return baremes;
	}
	public void setBaremes(List<Bareme> baremes) {
		this.baremes = baremes;
	}
	
}
