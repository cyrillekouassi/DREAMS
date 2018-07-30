package ci.jsi.entites.roleUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.rolesDefinis.RolesDefinisConvertEntitie;
import ci.jsi.entites.utilisateur.UserConvertEntitie;
import ci.jsi.initialisation.ConvertDate;
import ci.jsi.initialisation.Uid;

@Service
public class RoleUserConvert {
	
	@Autowired
	ConvertDate convertDate;
	@Autowired
	RolesDefinisConvertEntitie rolesDefinisConvertEntitie;
	@Autowired
	UserConvertEntitie userConvertEntitie;
	@Autowired
	Uid uid;

	public RoleUserTDO getRoleUserTDO(RoleUser roleUser) {
		RoleUserTDO roleUserTDO = new RoleUserTDO();
		roleUserTDO.setId(roleUser.getUid());
		roleUserTDO.setName(roleUser.getName());
		roleUserTDO.setCode(roleUser.getCode());
		roleUserTDO.setDescription(roleUser.getDescription());
		roleUserTDO.setDateCreation(convertDate.getDateString(roleUser.getDateCreation()));
		roleUserTDO.setDateUpdate(convertDate.getDateString(roleUser.getDateUpdate()));
		roleUserTDO.setRolesDefinies(rolesDefinisConvertEntitie.getRolesDefinis(roleUser.getRoledefinie()));
		roleUserTDO.setUsers(userConvertEntitie.getUsers(roleUser.getUsers()));
		return roleUserTDO;
		
	}
	public List<RoleUserTDO> getRoleUserTDOs(List<RoleUser> roleUsers) {
		List<RoleUserTDO> roleUserTDOs = new ArrayList<RoleUserTDO>();
		
		for(int i=0;i<roleUsers.size();i++) {
			roleUserTDOs.add(getRoleUserTDO(roleUsers.get(i)));
		}
		
		return roleUserTDOs;
	}
	
	public RoleUser saveRoleUserTDO(RoleUserTDO roleUserTDO) {
		RoleUser roleUser = new RoleUser();
		roleUser.setUid(uid.getUid());
		if(roleUserTDO.getName() == null) {
			return null;
		}
		roleUser.setName(roleUserTDO.getName());
		roleUser.setCode(roleUserTDO.getCode());
		roleUser.setDescription(roleUserTDO.getDescription());
		roleUser.setDateCreation(new Date());
		roleUser.setDateUpdate(new Date());
		if(!roleUserTDO.getRolesDefinies().isEmpty()) {
			roleUser.setRoledefinie(rolesDefinisConvertEntitie.setRolesDefinis(roleUserTDO.getRolesDefinies()));
		}
		if(!roleUserTDO.getUsers().isEmpty()) {
			roleUser.setUsers(userConvertEntitie.setUsers(roleUserTDO.getUsers()));
		}
		
		return roleUser;
	}
	
	public RoleUser updateRoleUserTDO(RoleUser roleUser,RoleUserTDO roleUserTDO) {
		if(roleUserTDO.getName() == null) {
			return null;
		}
			
		roleUser.setName(roleUserTDO.getName());
		roleUser.setCode(roleUserTDO.getCode());
		roleUser.setDescription(roleUserTDO.getDescription());
		roleUser.setDateUpdate(new Date());
		if(!roleUserTDO.getRolesDefinies().isEmpty())
			roleUser.setRoledefinie(rolesDefinisConvertEntitie.setRolesDefinis(roleUserTDO.getRolesDefinies()));
		else {
			//List<RolesDefinis> rolesDefinis = new ArrayList<RolesDefinis>();
			//roleUser.setRoledefinie(rolesDefinis);
			roleUser.setRoledefinie(null);
		}
		if(!roleUserTDO.getUsers().isEmpty()) {
			roleUser.setUsers(userConvertEntitie.setUsers(roleUserTDO.getUsers()));
		}else {
			roleUser.setUsers(null);
		}
			
		
		return roleUser;
	}
	
	
	
	
	
	
	
}
