package ci.jsi.entites.critere;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CritereService implements Icritere {

	@Autowired
	CritereConvert critereConvert;
	@Autowired
	CritereRepository critereRepository;

	@Override
	public List<CritereTDO> getAllCritereTDO() {

		return critereConvert.getCriteres(critereRepository.findAll());
	}

	@Override
	public CritereTDO getOneCritereTDO(String id) {
		Critere critere = new Critere();
		CritereTDO critereTDO = new CritereTDO();

		critere = critereRepository.getOneCritere(id);
		if (critere != null)
			critereTDO = critereConvert.getCritere(critere);
		return critereTDO;
	}

	@Override
	public String saveCritereTDO(CritereTDO critereTDO) {
		Critere critere = new Critere();
		critere = critereConvert.saveCritere(critereTDO);
		critere = critereRepository.save(critere);
		return "Saved id: " + critere.getUid();
	}

	@Override
	public String updateCritereTDO(String id, CritereTDO critereTDO) {
		Critere critere = new Critere();

		critere = critereRepository.getOneCritere(id);
		if (critere != null) {
			critere = critereConvert.updateCritere(critere, critereTDO);
			critere = critereRepository.save(critere);
		}

		return "Updated id: " + critere.getUid();
	}

	@Override
	public String deleteCritereTDO(String id) {
		Critere critere = new Critere();

		critere = critereRepository.getOneCritere(id);
		if (critere != null)
			critereRepository.delete(critere);

		return "Success deleted";
	}

}
