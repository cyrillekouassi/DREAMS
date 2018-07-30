package ci.jsi.entites.beneficiaire;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.instance.Iinstance;
import ci.jsi.entites.instance.Instance;
import ci.jsi.initialisation.ConvertDate;
import ci.jsi.initialisation.UidBeneficiaire;
import ci.jsi.initialisation.UidInstance;

@Service
public class InstanceBeneficiaireConvert {
	
	@Autowired
	ConvertDate convertDate;
	
	@Autowired
	Iinstance iinstance;
	@Autowired
	Ibeneficiaire ibeneficiaire;

	public UidInstance getInstanceBeneficiaire(InstanceBeneficiaire instanceBeneficiaire) {
		UidInstance uidInstanceBeneficiaire = new UidInstance();
		uidInstanceBeneficiaire.setInstance(instanceBeneficiaire.getInstance().getUid());
		if(instanceBeneficiaire.getDateAction() != null) {
			uidInstanceBeneficiaire.setDateAction(convertDate.getDateString(instanceBeneficiaire.getDateAction()));	
		}
		uidInstanceBeneficiaire.setOrdre(instanceBeneficiaire.getOrdre());
		return uidInstanceBeneficiaire;
	}
	
	
	public UidBeneficiaire getBeneficiaireInstance(InstanceBeneficiaire instanceBeneficiaire) {
		UidBeneficiaire uidBeneficiaireInstance = new UidBeneficiaire();
		uidBeneficiaireInstance.setBeneficiaire(instanceBeneficiaire.getBeneficiaire().getUid());
		if(instanceBeneficiaire.getDateAction() != null) {
			uidBeneficiaireInstance.setDateAction(convertDate.getDateString(instanceBeneficiaire.getDateAction()));
		}
		uidBeneficiaireInstance.setOrdre(instanceBeneficiaire.getOrdre());
		return uidBeneficiaireInstance;
	}
	
	public List<UidInstance> getInstanceBeneficiaires(List<InstanceBeneficiaire> instanceBeneficiaires){
		List<UidInstance> uidInstanceBeneficiaires = new ArrayList<UidInstance>();
		for(int i = 0;i < instanceBeneficiaires.size();i++) {
			uidInstanceBeneficiaires.add(getInstanceBeneficiaire(instanceBeneficiaires.get(i)));
		}
		return uidInstanceBeneficiaires;
	}
	
	public List<UidBeneficiaire> getBeneficiaireInstances(List<InstanceBeneficiaire> instanceBeneficiaires){
		List<UidBeneficiaire> beneficiaireInstances = new ArrayList<UidBeneficiaire>();
		for(int i = 0; i < instanceBeneficiaires.size();i++) {
			beneficiaireInstances.add(getBeneficiaireInstance(instanceBeneficiaires.get(i)));
		}
		return beneficiaireInstances;
	}
	
	public InstanceBeneficiaire getInstance(UidInstance uidInstanceBeneficiaire) {
		InstanceBeneficiaire instanceBeneficiaire = new InstanceBeneficiaire();
		
		Instance instance = iinstance.getOneInstance(uidInstanceBeneficiaire.getInstance());
		if(instance == null)
			return null;
		instanceBeneficiaire.setInstance(instance);
		if(uidInstanceBeneficiaire.getDateAction() != null) {
			
				instanceBeneficiaire.setDateAction(convertDate.getDateParse(uidInstanceBeneficiaire.getDateAction()));
			
		}
		
		return instanceBeneficiaire;
	}
	
	public List<InstanceBeneficiaire> getInstances(List<UidInstance> uidInstanceBeneficiaires) {
		List<InstanceBeneficiaire> instanceBeneficiaires = new ArrayList<InstanceBeneficiaire>();
		for(int i = 0;i<uidInstanceBeneficiaires.size();i++) {
			InstanceBeneficiaire instanceBeneficiaire = getInstance(uidInstanceBeneficiaires.get(i));
			if(instanceBeneficiaire != null) {
				instanceBeneficiaires.add(instanceBeneficiaire);
			}
		}
		return instanceBeneficiaires;
	}
	
	public InstanceBeneficiaire getBeneficiaire(UidBeneficiaire uidBeneficiaire) {
		InstanceBeneficiaire instanceBeneficiaire = new InstanceBeneficiaire();
		
		Beneficiaire Beneficiaire = ibeneficiaire.getOneBeneficiaireByUid(uidBeneficiaire.getBeneficiaire());
		if(Beneficiaire == null)
			return null;
		instanceBeneficiaire.setBeneficiaire(Beneficiaire);
		if(uidBeneficiaire.getDateAction() != null) {
				instanceBeneficiaire.setDateAction(convertDate.getDateParse(uidBeneficiaire.getDateAction()));
			
		}
		
		return instanceBeneficiaire;
	}
	
	public List<InstanceBeneficiaire> getBeneficiaires(List<UidBeneficiaire> uidBeneficiaires) {
		List<InstanceBeneficiaire> instanceBeneficiaires = new ArrayList<InstanceBeneficiaire>();
		for(int i = 0;i<uidBeneficiaires.size();i++) {
			InstanceBeneficiaire instanceBeneficiaire = getBeneficiaire(uidBeneficiaires.get(i));
			if(instanceBeneficiaire != null) {
				instanceBeneficiaires.add(instanceBeneficiaire);
			}
		}
		return instanceBeneficiaires;
	}
}



















