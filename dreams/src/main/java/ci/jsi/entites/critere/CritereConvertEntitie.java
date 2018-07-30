package ci.jsi.entites.critere;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.UidEntitie;

@Service
public class CritereConvertEntitie {
	
	@Autowired
	CritereRepository critereRepository;

	public UidEntitie getCritere(Critere critere) {
		UidEntitie uidEntitie = new UidEntitie();
		uidEntitie.setId(critere.getUid());
		return uidEntitie;
	}
	
	public Critere setCritere(UidEntitie uidEntitie) {
		Critere critere = new Critere();
		critere = critereRepository.getOneCritere(uidEntitie.getId());
		return critere;
	}
	
	
	
	public List<UidEntitie> getCriteres(List<Critere> criteres){
		List<UidEntitie> uidEntities = new ArrayList<UidEntitie>();
		
		for(int i=0; i<criteres.size();i++) {
			uidEntities.add(getCritere(criteres.get(i)));
		}
		return uidEntities;
	}
	
	public List<Critere> setCriteres(List<UidEntitie> uidEntities){
		List<Critere> criteres = new ArrayList<Critere>();
		for(int i=0; i<uidEntities.size();i++) {
			criteres.add(setCritere(uidEntities.get(i)));
		}
		return criteres;
	}
}
