package ci.jsi.entites.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServiceService implements Iservice {
	@Autowired
	ServiceConvert serviceConvert;
	@Autowired
	ServiceRepository serviceRepository;

	@Override
	public List<ServiceTDO> getAllServiceTDO() {
		
		return serviceConvert.getServiceTDO(serviceRepository.findAll());
	}

	@Override
	public ServiceTDO getOneServiceTDO(String id) {
		Servicess services = new Servicess();
		ServiceTDO serviceTDO = new ServiceTDO();
		
		
		services = serviceRepository.getOneService(id);
		if(services != null)
			serviceTDO = serviceConvert.getServiceTDO(services);
		return serviceTDO;
	}

	@Override
	public String saveServiceTDO(ServiceTDO serviceTDO) {
		Servicess services = new Servicess();
		services = serviceConvert.saveServiceTDO(serviceTDO);
		services = serviceRepository.save(services);
		return "Saved id: "+services.getUid();
	}

	@Override
	public String updateServiceTDO(String id, ServiceTDO serviceTDO) {
		Servicess services = new Servicess();		
		services = serviceRepository.getOneService(id);
		if(services != null) {
			services = serviceConvert.updateServiceTDO(services, serviceTDO);
			services = serviceRepository.save(services);
		}
		return "Update id: "+services.getUid();
	}

	@Override
	public String deleteServiceTDO(String id) {
		Servicess services = new Servicess();		
		services = serviceRepository.getOneService(id);
		if(services != null)
			serviceRepository.delete(services);
		return "Success deleted";
	}

}
