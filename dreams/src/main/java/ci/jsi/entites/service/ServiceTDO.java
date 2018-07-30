package ci.jsi.entites.service;

import java.util.ArrayList;
import java.util.List;

import ci.jsi.initialisation.UidEntitie;

public class ServiceTDO {

	private String id;
	private String name;
	private String code;

	private UidEntitie bareme;
	private List<UidEntitie> programmes = new ArrayList<UidEntitie>();
	private List<UidEntitie> ensembleCriteres = new ArrayList<UidEntitie>();
	

	public ServiceTDO() {

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

	public List<UidEntitie> getProgrammes() {
		return programmes;
	}

	public void setProgrammes(List<UidEntitie> programmes) {
		this.programmes = programmes;
	}

	public List<UidEntitie> getEnsembleCriteres() {
		return ensembleCriteres;
	}

	public void setEnsembleCriteres(List<UidEntitie> ensembleCriteres) {
		this.ensembleCriteres = ensembleCriteres;
	}

	public UidEntitie getBareme() {
		return bareme;
	}

	public void setBareme(UidEntitie bareme) {
		this.bareme = bareme;
	}

}
