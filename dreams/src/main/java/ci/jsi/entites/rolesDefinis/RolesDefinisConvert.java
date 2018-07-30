package ci.jsi.entites.rolesDefinis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.roleUser.RoleUserConvertEntitie;
import ci.jsi.initialisation.Uid;

@Service
public class RolesDefinisConvert {

	@Autowired
	RoleUserConvertEntitie roleUserConvertEntitie;
	
	@Autowired
	Uid uid;

	public RolesDefinisTDO getRolesDefinisTDO(RolesDefinis rolesDefinis) {
		RolesDefinisTDO rolesDefinisTDO = new RolesDefinisTDO();
		rolesDefinisTDO.setId(rolesDefinis.getUid());
		rolesDefinisTDO.setAutorisation(rolesDefinis.getAutorisation());
		rolesDefinisTDO.setRolesUsers(roleUserConvertEntitie.getUsers(rolesDefinis.getRolesUsers()));
		return rolesDefinisTDO;
	}

	public List<RolesDefinisTDO> getRolesDefinisTDOs(List<RolesDefinis> rolesDefinis) {
		List<RolesDefinisTDO> rolesDefinisTDOs = new ArrayList<RolesDefinisTDO>();

		for (int i = 0; i < rolesDefinis.size(); i++) {
			rolesDefinisTDOs.add(getRolesDefinisTDO(rolesDefinis.get(i)));
		}
		return rolesDefinisTDOs;
	}

	public RolesDefinis setRolesDefinisTDO(RolesDefinisTDO rolesDefinisTDO) {
		RolesDefinis rolesDefinis = new RolesDefinis();

		rolesDefinis.setUid(uid.getUid());
		rolesDefinis.setAutorisation(rolesDefinisTDO.getAutorisation());
		rolesDefinis.setRolesUsers(roleUserConvertEntitie.setRoleUser(rolesDefinisTDO.getRolesUsers()));
		return rolesDefinis;

	}

	public RolesDefinis UpdateRolesDefinis(RolesDefinis rolesDefinis, RolesDefinisTDO rolesDefinisTDO) {
		if (rolesDefinisTDO.getRolesUsers().size() != 0)
			rolesDefinis.setRolesUsers(roleUserConvertEntitie.setRoleUser(rolesDefinisTDO.getRolesUsers()));
		return rolesDefinis;
	}
}
