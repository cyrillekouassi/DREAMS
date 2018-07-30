package ci.jsi.initialisation;

public class UidInstance {

	private String instance;
	private String dateAction;
	private int ordre;
	
	
	public UidInstance() {
		
	}
	
	public UidInstance(String instance, String dateAction) {
		this.instance = instance;
		this.dateAction = dateAction;
	}
	
	public String getInstance() {
		return instance;
	}
	public void setInstance(String instance) {
		this.instance = instance;
	}
	public String getDateAction() {
		return dateAction;
	}
	public void setDateAction(String dateAction) {
		this.dateAction = dateAction;
	}

	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}
	
}
