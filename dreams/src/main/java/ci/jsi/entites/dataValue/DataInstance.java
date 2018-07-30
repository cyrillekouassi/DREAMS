package ci.jsi.entites.dataValue;

import java.util.List;

public class DataInstance {
	
	private String programme;
	private String organisation;
	private String user;
	private String instance;
	private String dreamsId;
	private String dateActivite;
	private String codeId;
	private int order;
	private List<DataValueTDO> dataValue;
	
	public DataInstance() {
		
	}

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public String getOrganisation(){
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public List<DataValueTDO> getDataValue() {
		return dataValue;
	}

	public void setDataValue(List<DataValueTDO> dataValue) {
		this.dataValue = dataValue;
	}

	public String getDreamsId() {
		return dreamsId;
	}

	public void setDreamsId(String dreamsId) {
		this.dreamsId = dreamsId;
	}

	public String getDateActivite() {
		return dateActivite;
	}

	public void setDateActivite(String dateActivite) {
		this.dateActivite = dateActivite;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	
	
	
}
