package ci.jsi.entites.ensembleOption;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnsembleOptionService implements IensembleOption {
	
	@Autowired
	EnsembleOptionRepository ensembleOptionRepository;
	@Autowired
	EnsembleOptionConvert ensembleOptionConvert;

	@Override
	public List<EnsembleOptionTDO> getAllEnsembleOptionTDO() {
		//List<EnsembleOptionTDO> ensembleOptionTDOs = new ArrayList<EnsembleOptionTDO>();
		
		return ensembleOptionConvert.getEnsembleOptionTDOs(ensembleOptionRepository.findAll());
	}

	@Override
	public EnsembleOptionTDO getOneEnsembleOptionTDO(String id) {
		EnsembleOptionTDO ensembleOptionTDO = new EnsembleOptionTDO();
		EnsembleOption ensembleOption = new EnsembleOption();
		ensembleOption = ensembleOptionRepository.getOneEnsembleOption(id);
		if(ensembleOption != null)
			ensembleOptionTDO = ensembleOptionConvert.getEnsembleOptionTDO(ensembleOption);
		return ensembleOptionTDO;
	}

	@Override
	public String saveEnsembleOptionTDO(EnsembleOptionTDO ensembleOptionTDO) {
		EnsembleOption ensembleOption = new EnsembleOption();
		ensembleOption = ensembleOptionConvert.saveEnsembleOptionTDO(ensembleOptionTDO);
		ensembleOption = ensembleOptionRepository.save(ensembleOption);
		return "Saved id: "+ensembleOption.getUid();
	}

	@Override
	public String updateEnsembleOptionTDO(String id, EnsembleOptionTDO ensembleOptionTDO) {
		EnsembleOption ensembleOption = new EnsembleOption();
		ensembleOption = ensembleOptionRepository.getOneEnsembleOption(id);
		if(ensembleOption != null) {
			ensembleOption = ensembleOptionConvert.updateEnsembleOptionTDO(ensembleOption, ensembleOptionTDO);
			ensembleOption = ensembleOptionRepository.save(ensembleOption);
		}
			
		return "Updated id: "+ensembleOption.getUid();
	}

	@Override
	public String deleteEnsembleOptionTDO(String id) {
		EnsembleOption ensembleOption = new EnsembleOption();
		ensembleOption = ensembleOptionRepository.getOneEnsembleOption(id);
		if(ensembleOption != null) {
			ensembleOptionRepository.delete(ensembleOption);
		}
		return "Succes Deleted";
	}

	@Override
	public EnsembleOption getOneEnsembleOptionByName(String name) {
		return ensembleOptionRepository.getOneEnsembleOptionByName(name);
	}

	@Override
	public EnsembleOption getOneEnsembleOption(String uid) {
		return ensembleOptionRepository.getOneEnsembleOption(uid);
	}

	@Override
	public EnsembleOption saveEnsembleOption(EnsembleOption ensembleOption) {

		return ensembleOptionRepository.save(ensembleOption);
	}

	/*@Override
	public List<EnsembleOptionTDO> getAllEnsembleOptionTDODetails() {
		// TODO Auto-generated method stub
		return null;
	}*/
	

}
