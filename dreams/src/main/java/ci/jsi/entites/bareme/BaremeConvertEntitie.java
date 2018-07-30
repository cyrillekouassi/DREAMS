package ci.jsi.entites.bareme;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.UidEntitie;

@Service
public class BaremeConvertEntitie {

	@Autowired
	BaremeRepository baremeRepository;
	

	public UidEntitie getBareme(Bareme bareme) {
		UidEntitie uidEntitie = new UidEntitie();
		uidEntitie.setId(bareme.getUid());
		return uidEntitie;
	}

	public Bareme setBareme(UidEntitie uidEntitie) {
		Bareme bareme = new Bareme();
		if(uidEntitie.getId() != null)
			bareme = baremeRepository.getOneBareme(uidEntitie.getId());
		return bareme;
	}

	public List<UidEntitie> getBaremes(List<Bareme> baremes) {
		List<UidEntitie> uidEntities = new ArrayList<UidEntitie>();

		for (int i = 0; i < baremes.size(); i++) {
			uidEntities.add(getBareme(baremes.get(i)));
		}
		return uidEntities;
	}

	public List<Bareme> setBaremes(List<UidEntitie> uidEntities) {
		List<Bareme> baremes = new ArrayList<Bareme>();
		for (int i = 0; i < uidEntities.size(); i++) {
			Bareme bareme = new Bareme();
			bareme = setBareme(uidEntities.get(i));
			if(bareme != null)
				baremes.add(bareme);
		}
		return baremes;
	}
}
