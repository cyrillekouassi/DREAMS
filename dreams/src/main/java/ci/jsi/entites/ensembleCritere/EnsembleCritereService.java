package ci.jsi.entites.ensembleCritere;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnsembleCritereService implements IensembleCritere {
	@Autowired
	EnsembleCritereConvert ensembleCritereConvert;
	@Autowired
	EnsembleCritereRepository ensembleCritereRepository;

	@Override
	public List<EnsembleCritereTDO> getAllEnsembleCritereTDO() {

		return ensembleCritereConvert.getEnsembleCritereTDOs(ensembleCritereRepository.findAll());
	}

	@Override
	public EnsembleCritereTDO getOneEnsembleCritereTDO(String id) {
		EnsembleCritereTDO ensembleCritereTDO = new EnsembleCritereTDO();
		EnsembleCritere ensembleCritere = new EnsembleCritere();

		ensembleCritere = ensembleCritereRepository.getOneEnsembleCritere(id);
		if (ensembleCritere != null) {
			ensembleCritereTDO = ensembleCritereConvert.getEnsembleCritereTDO(ensembleCritere);
		}

		return ensembleCritereTDO;
	}

	@Override
	public String saveEnsembleCritereTDO(EnsembleCritereTDO ensembleCritereTDO) {
		EnsembleCritere ensembleCritere = new EnsembleCritere();
		ensembleCritere = ensembleCritereConvert.saveEnsembleCritereTDO(ensembleCritereTDO);
		ensembleCritere = ensembleCritereRepository.save(ensembleCritere);
		return "Saved id: "+ensembleCritere.getUid();
	}

	@Override
	public String updateEnsembleCritereTDO(String id, EnsembleCritereTDO ensembleCritereTDO) {
		//EnsembleCritereTDO ensembleCritereTDO = new EnsembleCritereTDO();
		EnsembleCritere ensembleCritere = new EnsembleCritere();
		ensembleCritere = ensembleCritereRepository.getOneEnsembleCritere(id);
		if (ensembleCritere != null) {
			ensembleCritere = ensembleCritereConvert.updateEnsembleCritereTDO(ensembleCritere, ensembleCritereTDO);
			ensembleCritere = ensembleCritereRepository.save(ensembleCritere);
		}
		return "Updated id: "+ensembleCritere.getUid();
	}

	@Override
	public String deleteEnsembleCritereTDO(String id) {
		EnsembleCritere ensembleCritere = new EnsembleCritere();
		ensembleCritere = ensembleCritereRepository.getOneEnsembleCritere(id);
		if (ensembleCritere != null) {
			ensembleCritereRepository.delete(ensembleCritere);
		}
		return "Success deleted";
	}

}
