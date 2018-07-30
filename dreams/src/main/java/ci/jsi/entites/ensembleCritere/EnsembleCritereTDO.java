package ci.jsi.entites.ensembleCritere;

import java.util.ArrayList;
import java.util.List;

import ci.jsi.initialisation.UidEntitie;

public class EnsembleCritereTDO {

	private String id;
	private String name;
	private String code;
	private String combinaison;
	
	private List<UidEntitie> criteres = new ArrayList<UidEntitie>();
	private List<UidEntitie> services = new ArrayList<UidEntitie>();
	
	public EnsembleCritereTDO() {
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

	public String getCombinaison() {
		return combinaison;
	}

	public void setCombinaison(String combinaison) {
		this.combinaison = combinaison;
	}

	public List<UidEntitie> getCriteres() {
		return criteres;
	}

	public void setCriteres(List<UidEntitie> criteres) {
		this.criteres = criteres;
	}

	public List<UidEntitie> getServices() {
		return services;
	}

	public void setServices(List<UidEntitie> services) {
		this.services = services;
	}
	
	
}
