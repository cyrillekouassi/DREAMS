package ci.jsi.entites.rolesDefinis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.UidEntitie;
@Service
public class RolesDefinisConvertEntitie {

	@Autowired
	RolesDefinisRepository rolesDefinisRepository;
	

	public UidEntitie getRolesDefini(RolesDefinis rolesDefinis) {
		UidEntitie uidEntitie = new UidEntitie();
		uidEntitie.setId(rolesDefinis.getUid());
		return uidEntitie;
	}

	public RolesDefinis setRolesDefini(UidEntitie uidEntitie) {
		RolesDefinis rolesDefinis = new RolesDefinis();
		if(uidEntitie.getId() != null)
			rolesDefinis = rolesDefinisRepository.getOneRolesDefinis(uidEntitie.getId());
		return rolesDefinis;
	}

	public List<UidEntitie> getRolesDefinis(List<RolesDefinis> rolesDefinis) {
		List<UidEntitie> uidEntities = new ArrayList<UidEntitie>();

		for (int i = 0; i < rolesDefinis.size(); i++) {
			uidEntities.add(getRolesDefini(rolesDefinis.get(i)));
		}
		return uidEntities;
	}

	public List<RolesDefinis> setRolesDefinis(List<UidEntitie> uidEntities) {
		List<RolesDefinis> rolesDefinis = new ArrayList<RolesDefinis>();
		for (int i = 0; i < uidEntities.size(); i++) {
			RolesDefinis rolesDefini = new RolesDefinis();
			rolesDefini = setRolesDefini(uidEntities.get(i));
			if(rolesDefini != null)
				rolesDefinis.add(rolesDefini);
		}
		return rolesDefinis;
	}
}
