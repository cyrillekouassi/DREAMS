package ci.jsi.entites.roleUser;

import java.util.List;

import ci.jsi.initialisation.UidEntitie;

public interface IroleUser {
	public List<RoleUserTDO> getAllRoleUserTDO();
	public RoleUserTDO getOneRoleUserTDO(String id);
	public String saveRoleUserTDO(RoleUserTDO roleUserTDO);
	public String updateRoleUserTDO(String id, RoleUserTDO roleUserTDO);
	public String deleteRoleUserTDO(String id);
	
	public List<RoleUser> getRolesUsers(List<UidEntitie> uidEntities);
}
