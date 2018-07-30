package ci.jsi.entites.ensembleCritere;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.critere.CritereConvertEntitie;
import ci.jsi.entites.service.ServiceConvertEntitie;
import ci.jsi.initialisation.Uid;

@Service
public class EnsembleCritereConvert {

	@Autowired
	CritereConvertEntitie critereConvertEntitie;
	@Autowired
	ServiceConvertEntitie serviceConvertEntitie;
	@Autowired
	Uid uid;

	public EnsembleCritereTDO getEnsembleCritereTDO(EnsembleCritere ensembleCritere) {

		EnsembleCritereTDO ensembleCritereTDO = new EnsembleCritereTDO();
		ensembleCritereTDO.setId(ensembleCritere.getUid());
		ensembleCritereTDO.setName(ensembleCritere.getName());
		ensembleCritereTDO.setCode(ensembleCritere.getCode());
		ensembleCritereTDO.setCombinaison(ensembleCritere.getCombinaison());
		ensembleCritereTDO.setCriteres(critereConvertEntitie.getCriteres(ensembleCritere.getCriteres()));
		ensembleCritereTDO.setServices(serviceConvertEntitie.getServices(ensembleCritere.getServices()));

		return ensembleCritereTDO;
	}

	public List<EnsembleCritereTDO> getEnsembleCritereTDOs(List<EnsembleCritere> ensembleCriteres) {
		List<EnsembleCritereTDO> ensembleCritereTDOs = new ArrayList<EnsembleCritereTDO>();

		for (int i = 0; i < ensembleCriteres.size(); i++) {
			ensembleCritereTDOs.add(getEnsembleCritereTDO(ensembleCriteres.get(i)));
		}
		return ensembleCritereTDOs;
	}

	public EnsembleCritere saveEnsembleCritereTDO(EnsembleCritereTDO ensembleCritereTDO) {
		EnsembleCritere ensembleCritere = new EnsembleCritere();
		ensembleCritere.setUid(uid.getUid());
		ensembleCritere.setName(ensembleCritereTDO.getName());
		ensembleCritere.setCode(ensembleCritereTDO.getCode());
		ensembleCritere.setCombinaison(ensembleCritere.getCombinaison());
		ensembleCritere.setCriteres(critereConvertEntitie.setCriteres(ensembleCritereTDO.getCriteres()));
		ensembleCritere.setServices(serviceConvertEntitie.setServices(ensembleCritereTDO.getServices()));
		return ensembleCritere;
	}

	public EnsembleCritere updateEnsembleCritereTDO(EnsembleCritere ensembleCritere,EnsembleCritereTDO ensembleCritereTDO) {
		if (ensembleCritereTDO.getName() != null)
			ensembleCritere.setName(ensembleCritereTDO.getName());
		if (ensembleCritereTDO.getCode() != null)
			ensembleCritere.setCode(ensembleCritereTDO.getCode());
		if (ensembleCritere.getCombinaison() != null)
			ensembleCritere.setCombinaison(ensembleCritere.getCombinaison());
		if(ensembleCritereTDO.getCriteres().size() != 0)
			ensembleCritere.setCriteres(critereConvertEntitie.setCriteres(ensembleCritereTDO.getCriteres()));
		if(ensembleCritereTDO.getServices().size() != 0)
			ensembleCritere.setServices(serviceConvertEntitie.setServices(ensembleCritereTDO.getServices()));
		return ensembleCritere;
	}

}
