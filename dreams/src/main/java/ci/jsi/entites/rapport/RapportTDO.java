package ci.jsi.entites.rapport;

public class RapportTDO {
	
	private String organisation;
	private String element;
	private String periode;
	private int valeurs;
	
	
	public RapportTDO() {
		
	}


	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
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
