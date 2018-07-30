package ci.jsi.entites.option;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ci.jsi.entites.ensembleOption.EnsembleOption;
import ci.jsi.entites.rapport.Rapport;

@Entity
@Table(name="options")
public class Option implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long optionid;
	@Column(unique=true)
	private String uid;
	//@Column(unique=true)
	private String name;
	//@Column(unique=true)
	private String code;
	
	@ManyToOne
	@JoinColumn(name="ensembleoptionid")
	private EnsembleOption ensembleOption;
	
	@OneToMany(mappedBy="option",fetch=FetchType.LAZY)
	private List<Rapport> rapports = new ArrayList<Rapport>();
	
	public List<Rapport> getRapports() {
		return rapports;
	}
	public void setRapports(List<Rapport> rapports) {
		this.rapports = rapports;
	}
	public Option() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Option(String uid, String name, String code, String typeValeur) {
		super();
		this.uid = uid;
		this.name = name;
		this.code = code;
	}
	public Long getOptionid() {
		return optionid;
	}
	public void setOptionid(Long optionid) {
		this.optionid = optionid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public EnsembleOption getEnsembleOption() {
		return ensembleOption;
	}
	public void setEnsembleOption(EnsembleOption ensembleOption) {
		this.ensembleOption = ensembleOption;
	}
	
	
}
