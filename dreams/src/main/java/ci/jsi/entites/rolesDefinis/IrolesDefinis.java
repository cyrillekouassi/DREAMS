package ci.jsi.entites.rolesDefinis;

import java.util.List;

public interface IrolesDefinis {
	public List<RolesDefinisTDO> getAllRolesDefinisTDO();
	public RolesDefinisTDO getOneRolesDefinisTDO(String id);
	public String saveRolesDefinisTDO(RolesDefinisTDO rolesDefinisTDO);
	public String updateRolesDefinisTDO(String id, RolesDefinisTDO rolesDefinisTDO);
	public void saveRolesDefinie(String definie);
	
}
