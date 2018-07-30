package ci.jsi.entites.utilisateur;

import java.util.List;

public interface Iuser {

	public List<UserTDO> getAllUserTDO();
	public UserTDO getOneUser(String id);
	public UserTDO getOneUserByUsername(String username);
	public UserTDO saveUsersTDO(UserTDO userTDO);
	public String updateUserTDO(String id, UserTDO userTDO);
	public String deleteUser(String id);
	public String updateUserAcces(String id, UserAcces userAcces);
	public String UserAcces(UserAcces userAcces);
	
	public UserApp getUser(String id);
	public UserApp getUserByUsername(String username);
	
	public UserInfo getMeInfo();
	
}
