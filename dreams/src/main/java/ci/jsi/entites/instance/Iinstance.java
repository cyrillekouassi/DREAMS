package ci.jsi.entites.instance;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Iinstance {

	public List<InstanceTDO> getAllInstanceTDO();
	public InstanceTDO getOneInstanceTDO(String id);
	public String saveInstanceTDO(InstanceTDO instanceTDO);
	public String deleteInstanceTDO(String id);
	
	public Instance saveInstance(InstanceTDO instanceTDO);
	public Instance getOneInstance(String id);
	
	public Instance saveInstance(Instance instance);
	public Page<Instance> getInstanceselectByProgrammeAndOrganisation(String programmeuid,String organisationuid,Pageable pageable);
	public List<Instance> getInstanceAnalysePeriode(List<String> organisation, String programme,Date debut, Date fin);
	public List<Instance> getInstanceAnalysePreview(List<String> organisation, String programme, Date dateFin);
	public void deleteInstance(Instance instance);
}
