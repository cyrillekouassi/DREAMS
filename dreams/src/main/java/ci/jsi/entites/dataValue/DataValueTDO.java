package ci.jsi.entites.dataValue;

public class DataValueTDO {

	
	private String value;
	private int numero;
	private String dateCreation;
	private String dateUpdate;
	//private Boolean deleted;
	
	private String instance;
	private String user;
	private String element;
	
	
	public DataValueTDO() {
		
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
	this.value = value;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	/*public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}*/
	public String getInstance() {
		return instance;
	}
	public void setInstance(String instance) {
		this.instance = instance;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	
	
}
