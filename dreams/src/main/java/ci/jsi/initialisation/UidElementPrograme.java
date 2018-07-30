package ci.jsi.initialisation;

public class UidElementPrograme {

	private String programme;
	private Boolean showRapport;
	public UidElementPrograme(String programme, Boolean showRapport) {
		super();
		this.programme = programme;
		this.showRapport = showRapport;
	}
	public UidElementPrograme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getProgramme() {
		return programme;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	public Boolean getShowRapport() {
		return showRapport;
	}
	public void setShowRapport(Boolean showRapport) {
		this.showRapport = showRapport;
	}
	
	
}
