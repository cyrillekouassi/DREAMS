package ci.jsi.initialisation;

import java.util.ArrayList;
import java.util.List;

public class ResultatRequete {
	private String id;
	private String status;
	private int importe;
	private int ignore;
	private int update;
	private int delete;
	List<String> raisonNonImport = new ArrayList<String>();
	List<String> raisonAutreEchec = new ArrayList<String>();
	
	public ResultatRequete() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus	() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getImporte() {
		return importe;
	}
	public void setImporte(int importe) {
		this.importe = importe;
	}
	public int getIgnore() {
		return ignore;
	}
	public void setIgnore(int ignore) {
		this.ignore = ignore;
	}
	public int getDelete() {
		return delete;
	}
	public void setDelete(int delete) {
		this.delete = delete;
	}
	public List<String> getRaisonNonImport() {
		return raisonNonImport;
	}
	public void setRaisonNonImport(List<String> raisonNonImport) {
		this.raisonNonImport = raisonNonImport;
	}
	public List<String> getRaisonAutreEchec() {
		return raisonAutreEchec;
	}
	public void setRaisonAutreEchec(List<String> raisonAutreEchec) {
		this.raisonAutreEchec = raisonAutreEchec;
	}
	public int getUpdate() {
		return update;
	}
	public void setUpdate(int update) {
		this.update = update;
	}
	
	

}
