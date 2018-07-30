package ci.jsi.entites.dataValue;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ci.jsi.entites.instance.Instance;
import ci.jsi.initialisation.ResultatRequete;

public interface IdataValues {
	
	public String saveDataInstanceByElement(String programme,String organisation, String user, List<DataValueTDO> dataValue);
	public String saveDataValueInstance(String instance,String user, String element, String value);
	public String updateDataValue(String instance, String element, String value,int numero);
	public DataValueTDO getDataValueTDO(String instance, String element);
	public DataInstance getDataInstance(String instance);
	public String deleteDataValue(String instance, String element);
	public Page<DataInstance> getDataValueOrganisation(String programme,String organisation,Pageable pageable);
	public ResultatRequete saveDataInstance(DataInstance dataInstance);
	public DataValueTDO saveDataValueTDO (DataValueTDO dataValueTDO);
	public List<DataValueTDO> SearchDataValueTDO(String programme, String organisation,String element, String valeur);
	
	public List<DataInstance> dataAnalysePeriode(List<String> organisation, String programme,String debut, String fin);
	public List<DataInstance> dataAnalysePreview(List<String> organisation, String programme, String fin);
	
	public List<Instance> InstancePeriode(List<String> organisation, String programme,String debut, String fin);
	public List<Instance> InstancePreview(List<String> organisation, String programme, String fin);
	
	public DataValue saveDataValue(DataValue dataValue);
	public List<DataValue> saveAllDataValue(List<DataValue> dataValues);
	
}