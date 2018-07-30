package ci.jsi.entites.critere;

import java.util.List;

public interface Icritere {
	public List<CritereTDO> getAllCritereTDO();
	public CritereTDO getOneCritereTDO(String id);
	public String saveCritereTDO(CritereTDO critereTDO);
	public String updateCritereTDO(String id,CritereTDO critereTDO);
	public String deleteCritereTDO(String id);
}
