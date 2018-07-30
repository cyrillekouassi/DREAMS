package ci.jsi.entites.dataValueAudit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ci.jsi.entites.dataValue.DataValue;
import ci.jsi.entites.instance.Instance;
import ci.jsi.entites.utilisateur.UserApp;

@Entity
@Table(name="datavalueaudit")
public class DataValueAudit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long datavalueauditid;
	@Column(unique=true)
	private String uid;
	private String value;
	@Column(name="dateaction")
	private Date dateAction;
	@Column(name="typeaction")
	private String typeAction;
	
	@ManyToOne
	@JoinColumn(name="instanceid")
	private Instance instance;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userid")
	private UserApp user;
	
	@ManyToOne
	@JoinColumn(name="datavalueid")
	private DataValue dataValue;
	
	
	public Long getDatavalueauditid() {
		return datavalueauditid;
	}


	public void setDatavalueauditid(Long datavalueauditid) {
		this.datavalueauditid = datavalueauditid;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public Date getDateAction() {
		return dateAction;
	}


	public void setDateAction(Date dateAction) {
		this.dateAction = dateAction;
	}


	public String getTypeAction() {
		return typeAction;
	}


	public void setTypeAction(String typeAction) {
		this.typeAction = typeAction;
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


	public DataValue getDataValue() {
		return dataValue;
	}


	public void setDataValue(DataValue dataValue) {
		this.dataValue = dataValue;
	}


	public DataValueAudit() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DataValueAudit(String uid, String value, Date dateAction, String typeAction) {
		super();
		this.uid = uid;
		this.value = value;
		this.dateAction = dateAction;
		this.typeAction = typeAction;
	}
	
	
	
		
}
