package ci.jsi.entites.critere;

import java.util.ArrayList;
import java.util.List;

import ci.jsi.initialisation.UidEntitie;

public class CritereTDO {
	private String id;
	private String name;
	private String code;
	private String operateur;
	private String attendu;

	private UidEntitie element;

	private List<UidEntitie> ensembleCriteres = new ArrayList<UidEntitie>();

	public CritereTDO() {
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

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	public String getAttendu() {
		return attendu;
	}

	public void setAttendu(String attendu) {
		this.attendu = attendu;
	}

	public UidEntitie getElement() {
		return element;
	}

	public void setElement(UidEntitie element) {
		this.element = element;
	}

	public List<UidEntitie> getEnsembleCriteres() {
		return ensembleCriteres;
	}

	public void setEnsembleCriteres(List<UidEntitie> ensembleCriteres) {
		this.ensembleCriteres = ensembleCriteres;
	}

	
	
}
