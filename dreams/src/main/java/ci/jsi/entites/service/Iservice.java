package ci.jsi.entites.service;

import java.util.List;

public interface Iservice {

	public List<ServiceTDO> getAllServiceTDO();
	public ServiceTDO getOneServiceTDO(String id);
	public String saveServiceTDO(ServiceTDO serviceTDO);
	public String updateServiceTDO(String id, ServiceTDO serviceTDO);
	public String deleteServiceTDO(String id);
}
