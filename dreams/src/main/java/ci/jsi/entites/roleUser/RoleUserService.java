package ci.jsi.entites.roleUser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.UidEntitie;

@Service
public class RoleUserService implements IroleUser {
	
	@Autowired
	RoleUserConvert roleUserConvert;
	@Autowired
	RoleUserRepository roleUserRepository;

	@Override
	public List<RoleUserTDO> getAllRoleUserTDO() {
		
		return roleUserConvert.getRoleUserTDOs(roleUserRepository.findAll());
	}

	@Override
	public RoleUserTDO getOneRoleUserTDO(String id) {
		RoleUserTDO roleUserTDO = new RoleUserTDO();
		RoleUser roleUser = new RoleUser();
		roleUser = roleUserRepository.getOneRoleUser(id);
		if(roleUser != null)
			roleUserTDO = roleUserConvert.getRoleUserTDO(roleUser);
		
		return roleUserTDO;
	}

	@Override
	public String saveRoleUserTDO(RoleUserTDO roleUserTDO) {
		RoleUser roleUser = new RoleUser();
		roleUser =  roleUserConvert.saveRoleUserTDO(roleUserTDO);
		if(roleUser == null) {
			return null;
		}
		roleUser = roleUserRepository.save(roleUser);
		return "Saved id: "+roleUser.getUid();
	}

	@Override
	public String updateRoleUserTDO(String id, RoleUserTDO roleUserTDO) {
		RoleUser roleUser = new RoleUser();
		roleUser = roleUserRepository.getOneRoleUser(id);
		if(roleUser != null) {
			roleUser = roleUserConvert.updateRoleUserTDO(roleUser, roleUserTDO);
			roleUser = roleUserRepository.save(roleUser);
		}
		
		return "Updated id: "+roleUser.getUid();
	}

	@Override
	public String deleteRoleUserTDO(String id) {
		RoleUser roleUser = new RoleUser();
		roleUser = roleUserRepository.getOneRoleUser(id);
		if(roleUser != null) {
			roleUserRepository.delete(roleUser);
		}
		return "Success deleted";
	}

	@Override
	public List<RoleUser> getRolesUsers(List<UidEntitie> uidEntities) {
		List<RoleUser> roleUsers = new ArrayList<RoleUser>();
		for(int i = 0;i<uidEntities.size();i++) {
			RoleUser ru = roleUserRepository.findByUid(uidEntities.get(i).getId());
			if(ru != null) {
				roleUsers.add(ru);
			}
		}
		
		return roleUsers;
	}

}
