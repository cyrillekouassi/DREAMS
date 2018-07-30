package ci.jsi.entites.ensembleCritere;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.UidEntitie;

@Service
public class EnsembleCritereConvertEntitie {

	@Autowired
	EnsembleCritereRepository ensembleCritereRepository;
	

	public UidEntitie getEnsembleCritere(EnsembleCritere ensembleCritere) {
		UidEntitie uidEntitie = new UidEntitie();
		uidEntitie.setId(ensembleCritere.getUid());
		return uidEntitie;
	}

	public EnsembleCritere setEnsembleCritere(UidEntitie uidEntitie) {
		EnsembleCritere ensembleCritere = new EnsembleCritere();
		if(uidEntitie.getId() != null)
			ensembleCritere = ensembleCritereRepository.getOneEnsembleCritere(uidEntitie.getId());
		return ensembleCritere;
	}

	public List<UidEntitie> getEnsembleCriteres(List<EnsembleCritere> ensembleCriteres) {
		List<UidEntitie> uidEntities = new ArrayList<UidEntitie>();

		for (int i = 0; i < ensembleCriteres.size(); i++) {
			uidEntities.add(getEnsembleCritere(ensembleCriteres.get(i)));
		}
		return uidEntities;
	}

	public List<EnsembleCritere> setEnsembleCritere(List<UidEntitie> uidEntities) {
		List<EnsembleCritere> ensembleCriteres = new ArrayList<EnsembleCritere>();
		for (int i = 0; i < uidEntities.size(); i++) {
			EnsembleCritere ensembleCritere = new EnsembleCritere();
			ensembleCritere = setEnsembleCritere(uidEntities.get(i));
			if(ensembleCritere != null)
				ensembleCriteres.add(ensembleCritere);
		}
		return ensembleCriteres;
	}
}
