package ci.jsi.entites.ensembleCritere;

import java.util.List;

public interface IensembleCritere {
	public List<EnsembleCritereTDO> getAllEnsembleCritereTDO();
	public EnsembleCritereTDO getOneEnsembleCritereTDO(String id);
	public String saveEnsembleCritereTDO(EnsembleCritereTDO ensembleCritereTDO);
	public String updateEnsembleCritereTDO(String id,EnsembleCritereTDO ensembleCritereTDO);
	public String deleteEnsembleCritereTDO(String id);
}
