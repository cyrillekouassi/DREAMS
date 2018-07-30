package ci.jsi.initialisation;

public class UidProgrammeElement {

	private String element;
	private Boolean showRapport;
	public UidProgrammeElement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UidProgrammeElement(String element, Boolean showRapport) {
		super();
		this.element = element;
		this.showRapport = showRapport;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public Boolean getShowRapport() {
		return showRapport;
	}
	public void setShowRapport(Boolean showRapport) {
		this.showRapport = showRapport;
	}
	
}
