package ci.jsi.entites.roleUser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.UidEntitie;

@Service
public class RoleUserConvertEntitie {

	
	private List<UidEntitie> uidEntities;
	private List<RoleUser> lesroleUsers;
	
	@Autowired
	private RoleUserRepository roleUserRepository;
	
	public List<UidEntitie> getUsers(List<RoleUser> roleUsers) {
		uidEntities = new ArrayList<UidEntitie>();
		
		for(int i=0; i<roleUsers.size();i++) {
			uidEntities.add(new UidEntitie(roleUsers.get(i).getUid()));
		}
		return uidEntities;
	}
	
	public List<RoleUser> setRoleUser(List<UidEntitie> UidEntities){
		lesroleUsers = new ArrayList<RoleUser>();
		
		for(int i = 0; i<UidEntities.size();i++) {
			lesroleUsers.add(roleUserRepository.getOneRoleUser(UidEntities.get(i).getId()));
		}
		return lesroleUsers;
	}
}
