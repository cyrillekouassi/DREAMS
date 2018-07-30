package ci.jsi.entites.option;

import ci.jsi.initialisation.UidEntitie;

public class OptionTDO {

	
	private String id;
	private String name;
	private String code;
	private UidEntitie ensembleOption;
	
	public OptionTDO() {
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

	public UidEntitie getEnsembleOption() {
		return ensembleOption;
	}

	public void setEnsembleOption(UidEntitie ensembleOption) {
		this.ensembleOption = ensembleOption;
	}
	
}
