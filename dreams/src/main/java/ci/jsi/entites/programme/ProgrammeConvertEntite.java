package ci.jsi.entites.programme;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.UidEntitie;

@Service
public class ProgrammeConvertEntite {

	@Autowired
	ProgrammeRepository programmeRepository;
	

	public UidEntitie getProgramme(Programme programme) {
		UidEntitie uidEntitie = new UidEntitie();
		uidEntitie.setId(programme.getUid());
		return uidEntitie;
	}

	public Programme setProgramme(UidEntitie uidEntitie) {
		Programme programme = new Programme();
		if(uidEntitie.getId() != null)
			programme = programmeRepository.getOneProgramme(uidEntitie.getId());
		return programme;
	}

	public List<UidEntitie> getProgrammes(List<Programme> programmes) {
		List<UidEntitie> uidEntities = new ArrayList<UidEntitie>();

		for (int i = 0; i < programmes.size(); i++) {
			uidEntities.add(getProgramme(programmes.get(i)));
		}
		return uidEntities;
	}

	public List<Programme> setProgrammes(List<UidEntitie> uidEntities) {
		List<Programme> programmes = new ArrayList<Programme>();
		for (int i = 0; i < uidEntities.size(); i++) {
			Programme programme = new Programme();
			programme = setProgramme(uidEntities.get(i));
			if(programme != null)
				programmes.add(programme);
		}
		return programmes;
	}
}
