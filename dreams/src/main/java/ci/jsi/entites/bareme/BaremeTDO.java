package ci.jsi.entites.bareme;

import java.util.ArrayList;
import java.util.List;

import ci.jsi.initialisation.UidEntitie;

public class BaremeTDO {


	private String id;
	private String name;
	private String code;
	private List<UidEntitie> services = new ArrayList<UidEntitie>();
	private List<UidEntitie> couleurs = new ArrayList<UidEntitie>();
	
	
	
	public BaremeTDO() {
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
	public List<UidEntitie> getServices() {
		return services;
	}
	public void setServices(List<UidEntitie> services) {
		this.services = services;
	}
	public List<UidEntitie> getCouleurs() {
		return couleurs;
	}
	public void setCouleurs(List<UidEntitie> couleurs) {
		this.couleurs = couleurs;
	}
	
	
}
