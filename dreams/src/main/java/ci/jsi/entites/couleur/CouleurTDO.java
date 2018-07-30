package ci.jsi.entites.couleur;

import java.util.ArrayList;
import java.util.List;

import ci.jsi.initialisation.UidEntitie;

public class CouleurTDO {

	
	private String id;
	private String name;
	private String code;
	private String htmlCouleur;
	private List<UidEntitie> baremes = new ArrayList<UidEntitie>();
	
	public CouleurTDO() {
		super();
		// TODO Auto-generated constructor stub
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
	public List<UidEntitie> getBaremes() {
		return baremes;
	}
	public void setBaremes(List<UidEntitie> baremes) {
		this.baremes = baremes;
	}
	
}
