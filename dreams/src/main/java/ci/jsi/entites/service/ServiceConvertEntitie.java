package ci.jsi.entites.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.UidEntitie;

@Service
public class ServiceConvertEntitie {

	@Autowired
	ServiceRepository serviceRepository;
	

	public UidEntitie getService(Servicess service) {
		UidEntitie uidEntitie = new UidEntitie();
		uidEntitie.setId(service.getUid());
		return uidEntitie;
	}

	public Servicess setService(UidEntitie uidEntitie) {
		Servicess service = new Servicess();
		if(uidEntitie.getId() != null)
			service = serviceRepository.getOneService(uidEntitie.getId());
		return service;
	}

	public List<UidEntitie> getServices(List<Servicess> services) {
		List<UidEntitie> uidEntities = new ArrayList<UidEntitie>();

		for (int i = 0; i < services.size(); i++) {
			uidEntities.add(getService(services.get(i)));
		}
		return uidEntities;
	}

	public List<Servicess> setServices(List<UidEntitie> uidEntities) {
		List<Servicess> services = new ArrayList<Servicess>();
		for (int i = 0; i < uidEntities.size(); i++) {
			Servicess service = new Servicess();
			service = setService(uidEntities.get(i));
			if(service != null)
				services.add(service);
		}
		return services;
	}
}
