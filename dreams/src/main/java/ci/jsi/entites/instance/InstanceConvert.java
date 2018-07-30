package ci.jsi.entites.instance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.beneficiaire.InstanceBeneficiaireConvert;
import ci.jsi.entites.organisation.Iorganisation;
import ci.jsi.entites.organisation.Organisation;
import ci.jsi.entites.programme.Iprogramme;
import ci.jsi.entites.programme.Programme;
import ci.jsi.entites.utilisateur.Iuser;
import ci.jsi.entites.utilisateur.UserApp;
import ci.jsi.initialisation.ConvertDate;
import ci.jsi.initialisation.Uid;


@Service
public class InstanceConvert {

	/*
	@Autowired
	OrganisationConvertEntitie organisationConvertEntitie;
	@Autowired
	UserConvertEntitie userConvertEntitie;
	@Autowired
	ProgrammeConvertEntite programmeConvertEntite;*/
	
	@Autowired
	Iorganisation iorganisation;
	@Autowired
	Iuser iuser;
	@Autowired
	Iprogramme iprogramme;
	
	@Autowired
	ConvertDate convertDate;
	@Autowired
	Uid uid;
	@Autowired
	InstanceBeneficiaireConvert instanceBeneficiaireConvert;
	
	
	Organisation organisation = null;
	UserApp user = null;
	Programme programme = null;
	

	public InstanceTDO getInstanceTDO(Instance instance) {
		InstanceTDO instanceTDO = new InstanceTDO();
		instanceTDO.setId(instance.getUid());
		if (instance.getDateCreation() != null)
			instanceTDO.setDateCreation(convertDate.getDateString(instance.getDateCreation()));
		if (instance.getOrganisation() != null) {
			instanceTDO.setOrganisation(instance.getOrganisation().getUid());
		}
			
		if (instance.getUser() != null) {
			instanceTDO.setUser(instance.getUser().getUid());
		}
			
		if (instance.getProgramme() != null)
			instanceTDO.setProgramme(instance.getProgramme().getUid());
		if(instance.getDateActivite() != null) {
			instanceTDO.setDateCreation(convertDate.getDateString(instance.getDateActivite()));
		}

		return instanceTDO;
	}

	public List<InstanceTDO> getInstanceTDOs(List<Instance> instances) {
		List<InstanceTDO> instanceTDOs = new ArrayList<InstanceTDO>();

		for (int i = 0; i < instances.size(); i++) {
			instanceTDOs.add(getInstanceTDO(instances.get(i)));
		}
		
		return instanceTDOs;
	}

	public Instance saveInstanceTDO(InstanceTDO instanceTDO) {
		//System.out.println("Entrer dans InstanceConvert - saveInstanceTDO");
		Date active = null;
		Instance instance = new Instance();
		instance.setUid(uid.getUid());
		instance.setDateCreation(new Date());
		instance.setDeleted(false);
		if (instanceTDO.getOrganisation() != null) {
			organisation = iorganisation.getOneOrganisationById(instanceTDO.getOrganisation());
			if(organisation == null)
				return null;
			instance.setOrganisation(organisation);
		}else
			return null;
		if (instanceTDO.getUser() != null) {
			user = iuser.getUser(instanceTDO.getUser());
			instance.setUser(user);
		}
			
		if (instanceTDO.getProgramme() != null) {
			programme = iprogramme.getOneProgramme(instanceTDO.getProgramme());
			if(programme == null)
				return null;
			instance.setProgramme(programme);
		}else
			return null;
		
		if(instanceTDO.getDateActivite() != null) {
			
				active = convertDate.getDateParse(instanceTDO.getDateActivite());
				if(active != null) {
					instance.setDateActivite(active);
				}else
					return null;
		}else {
			return null;
		}
		if(!instanceTDO.getBeneficiaires().isEmpty()) {
			instance.setInstanceBeneficiaires(instanceBeneficiaireConvert.getBeneficiaires(instanceTDO.getBeneficiaires()));
		}
			
		return instance;
	}
	
	
	
	public Instance CheckInstance(Instance instance) {
		
		if(instance.getInstanceBeneficiaires().isEmpty())
			return null;
		if(instance.getOrganisation() == null)
			return null;
		if(instance.getProgramme() == null)
			return null;
		instance.setDateCreation(new Date());
		instance.setUid(uid.getUid());
		instance.setDeleted(false);
		if(instance.getDateActivite() == null)
			return null;
		
		return instance;
	}
	
	public Instance deleteInstance(Instance instance) {
		instance.setProgramme(null);
		instance.setOrganisation(null);
		instance.setUser(null);
		instance.setInstanceBeneficiaires(null);
		return instance;
	}
	
	
	
	
	
}
