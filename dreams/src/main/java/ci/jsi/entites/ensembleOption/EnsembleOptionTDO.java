package ci.jsi.entites.ensembleOption;

import java.util.ArrayList;
import java.util.List;

import ci.jsi.initialisation.CompleteEntite;
import ci.jsi.initialisation.UidEntitie;

public class EnsembleOptionTDO {

	private String id;
	private String name;
	private String code;
	private String typeValeur;
	private boolean multiple;
	private List<CompleteEntite> options = new ArrayList<CompleteEntite>();
	private List<UidEntitie> elements = new ArrayList<UidEntitie>();
	
	
	
	
	
	public EnsembleOptionTDO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeValeur() {
		return typeValeur;
	}
	public void setTypeValeur(String typeValeur) {
		this.typeValeur = typeValeur;
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
	public List<CompleteEntite> getOptions() {
		return options;
	}
	public void setOptions(List<CompleteEntite> options) {
		this.options = options;
	}
	public List<UidEntitie> getElements() {
		return elements;
	}
	public void setElements(List<UidEntitie> elements) {
		this.elements = elements;
	}
	public boolean isMultiple() {
		return multiple;
	}
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	
	
}
