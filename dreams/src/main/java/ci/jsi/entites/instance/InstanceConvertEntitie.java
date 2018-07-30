package ci.jsi.entites.instance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.UidEntitie;

@Service
public class InstanceConvertEntitie {

	private List<UidEntitie> uidEntities;
	@Autowired
	private InstanceRepository instanceRepository;

	public UidEntitie getInstance(Instance instances) {
		return new UidEntitie(instances.getUid());
	}

	public Instance setInstance(UidEntitie uidEntitie) {
		System.out.println("uidEntitie.getId() :"+uidEntitie.getId()+":");
		return instanceRepository.getOneInstance(uidEntitie.getId());
	}

	public List<UidEntitie> getInstances(List<Instance> instances) {

		uidEntities = new ArrayList<UidEntitie>();

		for (int i = 0; i < instances.size(); i++) {
			uidEntities.add(getInstance(instances.get(i)));
		}

		return uidEntities;
	}

	public List<Instance> setInstances(List<UidEntitie> uidEntities) {

		List<Instance> lesInstances = new ArrayList<Instance>();

		for (int i = 0; i < uidEntities.size(); i++) {
			Instance instance = new Instance();
			instance = setInstance(uidEntities.get(i));
			if (instance != null)
				lesInstances.add(instance);
		}
		return lesInstances;
	}
}
