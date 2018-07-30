package ci.jsi.entites.instance;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class InstanceService implements Iinstance {
	@Autowired
	InstanceConvert instanceConvert;
	@Autowired
	InstanceRepository instanceRepository;
	
	Instance instance = null;
	InstanceTDO instanceTDO = null;

	@Override
	public List<InstanceTDO> getAllInstanceTDO() {
		
		return instanceConvert.getInstanceTDOs(instanceRepository.getAllInstance());
	}

	@Override
	public InstanceTDO getOneInstanceTDO(String id) {
		instanceTDO = null;
		instance = instanceRepository.getOneInstance(id);
		if(instance != null) {
			instanceTDO = instanceConvert.getInstanceTDO(instance);
		}

		return instanceTDO;
	}

	@Override
	public String saveInstanceTDO(InstanceTDO instanceTDO) {
		instance = instanceConvert.saveInstanceTDO(instanceTDO);
		if(instance != null) {
			instance = instanceRepository.save(instance);
			return instance.getUid();
		}
		
		return "fail";
	}
	@Override
	public Instance saveInstance(InstanceTDO instanceTDO) {
		instance = instanceConvert.saveInstanceTDO(instanceTDO);
		if(instance != null) {
			instance = instanceRepository.save(instance);
		}
		
		return instance;
	}

	@Override
	public String deleteInstanceTDO(String id) {
		instance = instanceRepository.getOneInstance(id);
		if(instance != null) {
			instance.setDeleted(true);
			instance = instanceRepository.save(instance);
			return "Success Deleted";
		}
		return "fail";
	}

	@Override
	public Instance getOneInstance(String id) {
		/*instance = instanceRepository.getOneInstance(id);
		if(instance != null) {
			return instance;
		}*/
	
		return instanceRepository.getOneInstance(id);
	}

	
	@Override
	public Page<Instance> getInstanceselectByProgrammeAndOrganisation(String programmeuid, String organisationuid,Pageable pageable) {
		
		return instanceRepository.findAllByDeletedIsFalseAndProgrammeUidAndOrganisationUid(programmeuid, organisationuid,pageable);
	}
	@Override
	public Instance saveInstance(Instance instance) {
		instance = instanceConvert.CheckInstance(instance);
		if(instance == null) {
			return null;
		}
		instance = instanceRepository.save(instance);
		return instance;
	}

	@Override
	public void deleteInstance(Instance instance) {
		instance = instanceRepository.findOne(instance.getInstanceid());
		if(instance != null) {
			instance = instanceConvert.deleteInstance(instance);
			//instance = instanceRepository.save(instance);
			instanceRepository.delete(instance);
		}
	}

	@Override
	public List<Instance> getInstanceAnalysePeriode(List<String> organisation, String programme, Date debut, Date fin) {
		return instanceRepository.findAllByDeletedIsFalseAndOrganisationUidInAndProgrammeUidAndDateActiviteGreaterThanEqualAndDateActiviteLessThanEqual(organisation, programme, debut, fin);
	}

	@Override
	public List<Instance> getInstanceAnalysePreview(List<String> organisation, String programme, Date dateFin) {
		return instanceRepository.findAllByDeletedIsFalseAndOrganisationUidInAndProgrammeUidAndDateActiviteLessThan(organisation, programme, dateFin);

	}

	

	
	
}
