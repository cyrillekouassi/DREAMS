package ci.jsi.entites.dataValue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ci.jsi.entites.dataValueAudit.DataValueAudit;
import ci.jsi.entites.element.Element;
import ci.jsi.entites.instance.Instance;
import ci.jsi.entites.utilisateur.UserApp;

@Entity
@Table(name="datavalue")
public class DataValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long datavalueid;
	/*@Column(unique=true)
	private String uid;*/
	private String value;
	@Column(name="datecreation")
	private Date dateCreation;
	@Column(name="dateupdate")
	private Date dateUpdate;
	private int numero;
	
	
	@ManyToOne
	@JoinColumn(name="instanceid")
	//@JsonManagedReference
	@JsonBackReference
	private Instance instance;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userid")
	//@JsonManagedReference
	@JsonBackReference
	private UserApp user;
	
	@OneToMany(mappedBy="dataValue", fetch=FetchType.LAZY)
	//@JsonBackReference
	@JsonManagedReference
	private List<DataValueAudit> dataValueAudits = new ArrayList<DataValueAudit>();
	
	@ManyToOne
	@JoinColumn(name="elementid")
	//@JsonManagedReference
	@JsonBackReference
	private Element element;
	
	public DataValue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DataValue(String uid, String value, Date dateCreation, Date dateUpdate) {
		super();
		//this.uid = uid;
		this.value = value;
		this.dateCreation = dateCreation;
		this.dateUpdate = dateUpdate;
		//this.deleted = deleted;
	}
	public Long getDatavalueid() {
		return datavalueid;
	}
	public void setDatavalueid(Long datavalueid) {
		this.datavalueid = datavalueid;
	}
	/*public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}*/
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Instance getInstance() {
		return instance;
	}
	public void setInstance(Instance instance) {
		this.instance = instance;
	}
	public UserApp getUser() {
		return user;
	}
	public void setUser(UserApp user) {
		this.user = user;
	}
	public List<DataValueAudit> getDataValueAudits() {
		return dataValueAudits;
	}
	public void setDataValueAudits(List<DataValueAudit> dataValueAudits) {
		this.dataValueAudits = dataValueAudits;
	}
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	
	
	
}
