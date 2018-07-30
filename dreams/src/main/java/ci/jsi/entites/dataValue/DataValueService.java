package ci.jsi.entites.dataValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ci.jsi.entites.beneficiaire.Beneficiaire;
import ci.jsi.entites.instance.Iinstance;
import ci.jsi.entites.instance.Instance;
import ci.jsi.entites.organisation.Iorganisation;
import ci.jsi.entites.organisation.Organisation;
import ci.jsi.entites.programme.Iprogramme;
import ci.jsi.entites.programme.Programme;
import ci.jsi.entites.utilisateur.UserConvertEntitie;
import ci.jsi.initialisation.ConvertDate;
import ci.jsi.initialisation.ResultatRequete;
import ci.jsi.initialisation.UidEntitie;

@Service
@Transactional
public class DataValueService implements IdataValues {

	@Autowired
	private Iprogramme iprogramme;
	@Autowired
	private Iorganisation iorganisation;
	@Autowired
	Iinstance iinstance;
	@Autowired
	DataValueConvert dataValueConvert;
	@Autowired
	private DataValueRepository dataValueRepository;
	@Autowired
	private UserConvertEntitie userConvertEntitie;
	@Autowired
	ConvertDate convertDate;

	private DataValue dataValue;
	private Instance instance;
	private Beneficiaire beneficiaire;

	@Override
	public String saveDataInstanceByElement(String programme, String organisation, String user, List<DataValueTDO> dataValue) {
		System.out.println("Entrer dans DataValueService - saveDataInstance");
		List<DataValue> dataValues = new ArrayList<DataValue>();
		instance = iinstance.saveInstance(dataValueConvert.createInstance(programme, organisation, user));
		dataValues = dataValueConvert.saveDataValues(dataValue, instance);
		dataValueRepository.save(dataValues);
		return "Succes";
	}

	@Override
	public String saveDataValueInstance(String instance, String user, String element, String value) {
		System.out.println("Entrer dans DataValueService - saveDataValueInstance");
		dataValue = dataValueConvert.contituerDataValue(instance, user, element);
		dataValue = dataValueRepository.getOneDataValue(dataValue.getInstance(),
				dataValue.getElement());
		if (dataValue != null) {
			dataValue.setValue(value);
			dataValue.setDateUpdate(new Date());
			if (user != null)
				dataValue.setUser(userConvertEntitie.setUser(new UidEntitie(user)));
			dataValue = dataValueRepository.save(dataValue);
		}
		return null;
	}

	@Override
	public String updateDataValue(String instance, String element, String value,int numero) {
		
		return null;
	}

	@Override
	public DataValueTDO getDataValueTDO(String instance, String element) {
		System.out.println("Entrer dans DataValueService - getDataValue");
		//DataValueTDO dataValueTDO = new DataValueTDO();
		DataValueTDO dataValueTDO = null;
		DataValue dataValue = new DataValue();
		
		dataValue = dataValueConvert.getDataValueSepare(instance, element);
		if (dataValue != null) {
			dataValueTDO = dataValueConvert.getDataValueTDO(dataValue);
		}
		return dataValueTDO;
	}

	@Override
	public DataInstance getDataInstance(String instance) {
		System.out.println("Entrer dans DataValueService - getDataValue");
		DataInstance dataInstance = new DataInstance();		
		List<DataValue> dataValues = new ArrayList<DataValue>();
		dataValues = dataValueRepository.findByInstanceUid(instance);
		if(!dataValues.isEmpty()) {
			dataInstance = dataValueConvert.contituerDataInstance(instance, dataValues);
		}
		
		return dataInstance;
	}

	@Override
	public String deleteDataValue(String instance, String element) {
		System.out.println("Entrer dans DataValueService - deleteDataValue");
		dataValue = dataValueConvert.getDataValueSepare(instance, element);
		if (dataValue != null) {
			dataValueRepository.delete(dataValue);
		}
		return "deleted";
	}

	@Override
	public Page<DataInstance> getDataValueOrganisation(String programme, String organisation,Pageable pageable) {
		System.out.println("Entrer dans DataValueService - getDataValueOrganisation");
		List<Instance> instancesSeclect = new ArrayList<Instance>();
		List<DataInstance> dataInstances = new ArrayList<DataInstance>();
		Page<Instance> instances = iinstance.getInstanceselectByProgrammeAndOrganisation(programme, organisation,pageable);
		instancesSeclect = instances.getContent();
		if (!instancesSeclect.isEmpty()) {
			for (int i = 0; i < instancesSeclect.size(); i++) {
				dataInstances.add(getDataInstance(instancesSeclect.get(i).getUid()));
			}
		}

		Page<DataInstance> page = new PageImpl<DataInstance>(dataInstances,
                new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()),
                instances.getTotalPages());

		return page;
	}

	public ResultatRequete saveDataInstance(DataInstance dataInstance) {
		System.out.println("Entrer dans DataValueService - saveDataInstance");
		ResultatRequete resultatRequete = new ResultatRequete();
		List<DataValue> dataValues = new ArrayList<DataValue>();
		if(dataInstance.getInstance() != null) {
			instance = iinstance.getOneInstance(dataInstance.getInstance());
			instance = dataValueConvert.updateInstance(instance,dataInstance);
			dataValueConvert.updateBeneficiaire(dataInstance,instance);
		}else {
			instance = dataValueConvert.createNewInstance(dataInstance);
			if(instance != null && dataInstance.getDreamsId() != null) {
				beneficiaire = dataValueConvert.createBeneficiaire(dataInstance, instance);
				if(beneficiaire == null) {
					iinstance.deleteInstance(instance);
					resultatRequete.setStatus("fail");
					resultatRequete.setIgnore(1);
					return resultatRequete;
				}
			}
		}
		
		if(instance != null) {
			dataValues = dataValueConvert.saveDataValues(dataInstance.getDataValue(), instance);
			if(!dataValues.isEmpty())
				dataValues = dataValueRepository.save(dataValues);
			dataInstance.setDataValue(dataValueConvert.getDataValueTDOs(dataValues));
			dataInstance.setInstance(instance.getUid());
			resultatRequete.setStatus("ok");
			resultatRequete.setId(instance.getUid());
			resultatRequete.setImporte(dataValues.size());
			return resultatRequete;
		}
		resultatRequete.setStatus("fail");
		resultatRequete.setIgnore(1);
		return resultatRequete;

	}

	@Override
	public DataValueTDO saveDataValueTDO(DataValueTDO dataValueTDO) {
		System.out.println("Entrer dans DataValueService - saveDataValueTDO");
		List<DataValue> dataValuesList = new ArrayList<DataValue>();
		DataValue dataValue = new DataValue();
		dataValue = dataValueRepository.findByInstanceUidAndElementUidAndNumero(dataValueTDO.getInstance(), dataValueTDO.getElement(),dataValueTDO.getNumero());
		if(dataValue != null) {
			dataValuesList = dataValueRepository.findByInstanceUidAndElementUid(dataValueTDO.getInstance(), dataValueTDO.getElement());
		}
		dataValue = dataValueConvert.updateDataValue(dataValue,dataValueTDO,dataValuesList);
		if(dataValue == null)
			return null;	
		dataValue = dataValueRepository.save(dataValue);
		dataValueTDO = dataValueConvert.getDataValueTDO(dataValue);
		return dataValueTDO;
	}

	@Override
	public DataValue saveDataValue(DataValue dataValue) {
		System.out.println("Entrer dans DataValueService - saveDataValue");
		dataValue = dataValueConvert.checkDataValue(dataValue);
		if(dataValue == null)
			return null;
		dataValue = dataValueRepository.save(dataValue);
		return dataValue;
	}

	@Override
	public List<DataValue> saveAllDataValue(List<DataValue> dataValues) {
		
		return dataValueRepository.save(dataValues);
	}

	@Override
	public List<DataValueTDO> SearchDataValueTDO(String programme, String organisation, String element, String valeur) {
		List<DataValue> dataValues = new ArrayList<DataValue>();
		List<DataValueTDO> DataValueTDOs = new ArrayList<DataValueTDO>();
		
		Programme prog = iprogramme.getOneProgramme(programme);
		Organisation org = iorganisation.getOneOrganisationById(organisation);
		dataValues = dataValueRepository.findByInstanceProgrammeAndInstanceOrganisationAndElementUidAndValueContaining(prog, org, element, valeur);
		if(dataValues.isEmpty())
			return DataValueTDOs;
		DataValueTDOs = dataValueConvert.getDataValueTDOs(dataValues);
		return DataValueTDOs;
	}

	@Override
	public List<DataInstance> dataAnalysePeriode(List<String> organisation, String programme, String debut, String fin) {
		List<Instance> instances = new ArrayList<Instance>();
		List<DataInstance> dataInstances = new ArrayList<DataInstance>();
		Date dateDebut = null;
		Date dateFin = null;
		dateDebut = convertDate.getDateParse(debut);
		dateFin = convertDate.getDateParse(fin);
		instances = iinstance.getInstanceAnalysePeriode(organisation, programme, dateDebut, dateFin);
		if (!instances.isEmpty()) {
			for (int i = 0; i < instances.size(); i++) {
				dataInstances.add(getDataInstance(instances.get(i).getUid()));
			}
		}
		return dataInstances;
	}

	@Override
	public List<DataInstance> dataAnalysePreview(List<String> organisation, String programme, String fin) {
		List<Instance> instances = new ArrayList<Instance>();
		List<DataInstance> dataInstances = new ArrayList<DataInstance>();
		Date dateFin = null;
		dateFin = convertDate.getDateParse(fin);
		instances = iinstance.getInstanceAnalysePreview(organisation, programme, dateFin);
		if (!instances.isEmpty()) {
			for (int i = 0; i < instances.size(); i++) {
				dataInstances.add(getDataInstance(instances.get(i).getUid()));
			}
		}
		return dataInstances;
	}

	@Override
	public List<Instance> InstancePeriode(List<String> organisation, String programme, String debut, String fin) {
		List<Instance> instances = new ArrayList<Instance>();
		Date dateDebut = null;
		Date dateFin = null;
		dateDebut = convertDate.getDateParse(debut);
		dateFin = convertDate.getDateParse(fin);
		instances = iinstance.getInstanceAnalysePeriode(organisation, programme, dateDebut, dateFin);
		
		return instances;
	}

	@Override
	public List<Instance> InstancePreview(List<String> organisation, String programme, String fin) {
		List<Instance> instances = new ArrayList<Instance>();
		Date dateFin = null;
		dateFin = convertDate.getDateParse(fin);
		instances = iinstance.getInstanceAnalysePreview(organisation, programme, dateFin);
		return instances;
	}

}
