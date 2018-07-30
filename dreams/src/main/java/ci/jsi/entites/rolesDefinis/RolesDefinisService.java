package ci.jsi.entites.rolesDefinis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.Uid;

@Service
public class RolesDefinisService implements IrolesDefinis {

	@Autowired
	RolesDefinisConvert rolesDefinisConvert;
	
	@Autowired
	RolesDefinisRepository rolesDefinisRepository;
	
	@Autowired
	Uid uid;
	
	
	@Override
	public List<RolesDefinisTDO> getAllRolesDefinisTDO() {
		
		return rolesDefinisConvert.getRolesDefinisTDOs(rolesDefinisRepository.findAll());
	}

	@Override
	public RolesDefinisTDO getOneRolesDefinisTDO(String id) {
		RolesDefinis rolesDefinis = new RolesDefinis();
		RolesDefinisTDO rolesDefinisTDO = new RolesDefinisTDO();
		
		rolesDefinis = rolesDefinisRepository.getOneRolesDefinis(id);
		if(rolesDefinis != null)
			rolesDefinisTDO = rolesDefinisConvert.getRolesDefinisTDO(rolesDefinis);
		
		return rolesDefinisTDO;
	}

	@Override
	public String saveRolesDefinisTDO(RolesDefinisTDO rolesDefinisTDO) {
		RolesDefinis rolesDefinis = new RolesDefinis();
		rolesDefinis = rolesDefinisConvert.setRolesDefinisTDO(rolesDefinisTDO);
		rolesDefinis = rolesDefinisRepository.save(rolesDefinis);
		return "Success";
	}

	@Override
	public String updateRolesDefinisTDO(String id, RolesDefinisTDO rolesDefinisTDO) {
		RolesDefinis rolesDefinis = new RolesDefinis();
		rolesDefinis = rolesDefinisRepository.getOneRolesDefinis(id);
		if(rolesDefinis != null) {
			rolesDefinis = rolesDefinisConvert.UpdateRolesDefinis(rolesDefinis, rolesDefinisTDO);
			rolesDefinis = rolesDefinisRepository.save(rolesDefinis);
		}
		return "Updated";
	}

	@Override
	public void saveRolesDefinie(String definies) {
		RolesDefinis rolesDefinis = rolesDefinisRepository.findByAutorisation(definies);
		if(rolesDefinis == null) {
			rolesDefinis = new RolesDefinis();
			rolesDefinis.setUid(uid.getUid());
			rolesDefinis.setAutorisation(definies);
			rolesDefinisRepository.save(rolesDefinis);
		}
	}

}
