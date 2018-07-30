package ci.jsi.entites.ensembleOption;

import java.util.List;

public interface IensembleOption {

	public List<EnsembleOptionTDO> getAllEnsembleOptionTDO();
	//public List<EnsembleOptionTDO> getAllEnsembleOptionTDODetails();
	public EnsembleOptionTDO getOneEnsembleOptionTDO(String id);
	public String saveEnsembleOptionTDO(EnsembleOptionTDO ensembleOptionTDO);
	public String updateEnsembleOptionTDO(String id,EnsembleOptionTDO ensembleOptionTDO);
	public String deleteEnsembleOptionTDO(String id);
	
	public EnsembleOption getOneEnsembleOptionByName(String name);
	public EnsembleOption getOneEnsembleOption(String uid);
	public EnsembleOption saveEnsembleOption(EnsembleOption ensembleOption);
}
