package ci.jsi.entites.element;

import java.util.ArrayList;
import java.util.List;

import ci.jsi.initialisation.UidElementPrograme;
import ci.jsi.initialisation.UidEntitie;

public class ElementTDO {

	
	private String id;
	private String name;
	private String code;
	private String dateCreation;
	private String dateUpdate;
	private String description;
	private String typeValeur;
	private UidEntitie ensembleOption;
	private List<UidEntitie> critere = new ArrayList<UidEntitie>();
	//private List<UidEntitie> dataValues = new ArrayList<UidEntitie>();
	private List<UidEntitie> sections = new ArrayList<UidEntitie>();
	private List<UidElementPrograme> programmes = new ArrayList<UidElementPrograme>();
	
	
	public ElementTDO() {
		
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTypeValeur() {
		return typeValeur;
	}
	public void setTypeValeur(String typeValeur) {
		this.typeValeur = typeValeur;
	}
	public UidEntitie getEnsembleOption() {
		return ensembleOption;
	}
	public void setEnsembleOption(UidEntitie ensembleOption) {
		this.ensembleOption = ensembleOption;
	}
	public List<UidEntitie> getCritere() {
		return critere;
	}
	public void setCritere(List<UidEntitie> critere) {
		this.critere = critere;
	}
	/*public List<UidEntitie> getDataValues() {
		return dataValues;
	}
	public void setDataValues(List<UidEntitie> dataValues) {
		this.dataValues = dataValues;
	}*/
	public List<UidEntitie> getSections() {
		return sections;
	}
	public void setSections(List<UidEntitie> sections) {
		this.sections = sections;
	}
	public List<UidElementPrograme> getProgrammes() {
		return programmes;
	}
	public void setProgrammes(List<UidElementPrograme> programmes) {
		this.programmes = programmes;
	}
	

}
