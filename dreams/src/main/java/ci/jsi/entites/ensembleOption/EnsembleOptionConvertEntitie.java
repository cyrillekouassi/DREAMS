package ci.jsi.entites.ensembleOption;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.UidEntitie;

@Service
public class EnsembleOptionConvertEntitie {
	@Autowired
	EnsembleOptionRepository ensembleOptionRepository;
	

	public UidEntitie getEnsembleOption(EnsembleOption ensembleOption) {
		UidEntitie uidEntitie = new UidEntitie();
		uidEntitie.setId(ensembleOption.getUid());
		return uidEntitie;
	}

	public EnsembleOption setEnsembleOption(UidEntitie uidEntitie) {
		EnsembleOption ensembleOption = new EnsembleOption();
		if(uidEntitie.getId() != null)
			ensembleOption = ensembleOptionRepository.getOneEnsembleOption(uidEntitie.getId());
		return ensembleOption;
	}

	public List<UidEntitie> getEnsembleOptions(List<EnsembleOption> ensembleOptions) {
		List<UidEntitie> uidEntities = new ArrayList<UidEntitie>();

		for (int i = 0; i < ensembleOptions.size(); i++) {
			uidEntities.add(getEnsembleOption(ensembleOptions.get(i)));
		}
		return uidEntities;
	}

	public List<EnsembleOption> setEnsembleOptions(List<UidEntitie> uidEntities) {
		List<EnsembleOption> ensembleOptions = new ArrayList<EnsembleOption>();
		for (int i = 0; i < uidEntities.size(); i++) {
			EnsembleOption ensembleOption = new EnsembleOption();
			ensembleOption = setEnsembleOption(uidEntities.get(i));
			if(ensembleOption != null)
				ensembleOptions.add(ensembleOption);
		}
		return ensembleOptions;
	}
}
