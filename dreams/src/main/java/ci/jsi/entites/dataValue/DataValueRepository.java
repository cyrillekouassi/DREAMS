package ci.jsi.entites.dataValue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ci.jsi.entites.element.Element;
import ci.jsi.entites.instance.Instance;
import ci.jsi.entites.organisation.Organisation;
import ci.jsi.entites.programme.Programme;

public interface DataValueRepository extends JpaRepository<DataValue, Long> {
	
	@Query("select dv from DataValue dv where dv.instance=:instance and dv.element=:element")
	public DataValue getOneDataValue(@Param("instance")Instance instance,@Param("element")Element element);
	
	@Query("select dv from DataValue dv where dv.instance=:instance")
	public List<DataValue> getDataValueOneInstance(@Param("instance")Instance instance);
	
	public List<DataValue> findByInstanceUid(String instanceUid);
	
	public DataValue findByInstanceUidAndElementUidAndNumero(String instanceUid, String elementUid,int numero);
	
	public List<DataValue> findByInstanceUidAndElementUid(String instanceUid, String elementUid);
	
	public List<DataValue> findByInstanceProgrammeAndInstanceOrganisationAndElementUidAndValueContaining(Programme programme,Organisation organisation,String elementUid,String value);
}
