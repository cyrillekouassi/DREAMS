package ci.jsi.entites.utilisateur;

public class UserAcces {

	private String id;
	private String username;
	private String password;
	private String dateCreationPassword;
	private String datePasswordUpdate;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDateCreationPassword() {
		return dateCreationPassword;
	}
	public void setDateCreationPassword(String dateCreationPassword) {
		this.dateCreationPassword = dateCreationPassword;
	}
	public String getDatePasswordUpdate() {
		return datePasswordUpdate;
	}
	public void setDatePasswordUpdate(String datePasswordUpdate) {
		this.datePasswordUpdate = datePasswordUpdate;
	}
	
	
}
