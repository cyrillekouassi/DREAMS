package ci.jsi.entites.dataValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ci.jsi.entites.beneficiaire.Beneficiaire;
import ci.jsi.entites.beneficiaire.Ibeneficiaire;
import ci.jsi.entites.beneficiaire.InstanceBeneficiaire;
import ci.jsi.entites.element.Element;
import ci.jsi.entites.element.Ielement;
import ci.jsi.entites.instance.Iinstance;
import ci.jsi.entites.instance.Instance;
import ci.jsi.entites.instance.InstanceTDO;
import ci.jsi.entites.organisation.Iorganisation;
import ci.jsi.entites.organisation.Organisation;
import ci.jsi.entites.programme.Iprogramme;
import ci.jsi.entites.programme.Programme;
import ci.jsi.entites.utilisateur.Iuser;
import ci.jsi.entites.utilisateur.UserApp;
import ci.jsi.initialisation.ConvertDate;

@Service
@Transactional
public class DataValueConvert {

	@Autowired
	Iinstance iinstance;
	@Autowired
	Iuser iuser;
	@Autowired
	Ielement ielement;
	@Autowired
	Iprogramme iprogramme;
	@Autowired
	Iorganisation iorganisation;

	@Autowired
	Ibeneficiaire ibeneficiaire;

	@Autowired
	ConvertDate convertDate;
	@Autowired
	private DataValueRepository dataValueRepository;

	Instance instance = null;
	UserApp user = null;
	Element element = null;
	Programme programme = null;
	Organisation organisation = null;

	public DataValue saveDataValueTDO(DataValueTDO dataValueTDO) {

		DataValue dataValue = new DataValue();
		dataValue.setDateUpdate(new Date());
		dataValue.setDateCreation(new Date());
		dataValue.setValue(dataValueTDO.getValue());

		if (dataValueTDO.getNumero() == (int) dataValueTDO.getNumero())
			dataValue.setNumero(dataValueTDO.getNumero());
		else
			dataValue.setNumero(1);

		if (dataValueTDO.getInstance() != null) {
			instance = iinstance.getOneInstance(dataValueTDO.getInstance());
			if (instance != null) {
				dataValue.setInstance(instance);
			} else
				return null;

		} else
			return null;

		if (dataValueTDO.getUser() != null) {
			user = iuser.getUser(dataValueTDO.getUser());
			dataValue.setUser(user);
		}

		if (dataValueTDO.getElement() != null) {
			element = ielement.getOneElmentById(dataValueTDO.getElement());
			if (element != null)
				dataValue.setElement(element);
			else
				return null;
		} else
			return null;

		return dataValue;
	}

	public DataValue saveDataValue(DataValueTDO dataValueTDO, Instance instance) {
		DataValue dataValue = new DataValue();
		List<DataValue> dataValues = new ArrayList<DataValue>();
		if (instance == null)
			return null;
		if (dataValueTDO.getElement() == null)
			return null;
		element = ielement.getOneElmentById(dataValueTDO.getElement());
		if (element == null)
			return null;
		/*
		 * if (dataValueTDO.getValue() == null) return null;
		 */

		if (dataValueTDO.getNumero() != 0) {
			dataValue = dataValueRepository.findByInstanceUidAndElementUidAndNumero(instance.getUid(),
					dataValueTDO.getElement(), dataValueTDO.getNumero());
			if (dataValue == null) {
				dataValue = new DataValue();
				dataValue.setDateUpdate(new Date());
				dataValue.setDateCreation(new Date());
				dataValue.setNumero(dataValueTDO.getNumero());
			} else {
				dataValue.setDateUpdate(new Date());
			}

		} else {
			dataValues = dataValueRepository.findByInstanceUidAndElementUid(instance.getUid(),
					dataValueTDO.getElement());
			dataValue.setNumero(getNumero(dataValues));
			dataValue.setDateCreation(new Date());
			dataValue.setDateUpdate(new Date());
		}

		if (dataValueTDO.getUser() != null) {
			user = iuser.getUser(dataValueTDO.getUser());
			dataValue.setUser(user);
		}
		dataValue.setInstance(instance);
		dataValue.setElement(element);
		dataValue.setValue(dataValueTDO.getValue());

		return dataValue;
	}

	public List<DataValue> saveDataValues(List<DataValueTDO> dataValueTDOs, Instance instance) {
		List<DataValue> dataValues = new ArrayList<DataValue>();

		for (int i = 0; i < dataValueTDOs.size(); i++) {
			DataValue dataValue = saveDataValue(dataValueTDOs.get(i), instance);
			if (dataValue != null)
				dataValues.add(dataValue);
		}
		//if (dataValues.size() != dataValueTDOs.size())
		//	System.out.println((dataValueTDOs.size() - dataValues.size()) + " valeurs ont été ignoré");
		return dataValues;
	}

	public DataValue contituerDataValue(String instance1, String user1, String element1) {
		System.out.println("Entrer dans contituerDataValue");
		System.out.println("instance1 :" + instance1 + "// user1 :" + user1 + "// element1:" + element1);
		DataValue dataValue = new DataValue();

		instance = iinstance.getOneInstance(instance1);
		if (instance == null) {
			System.out.println("Instance1 :" + instance1 + " non trouvé");
			return null;
		}
		dataValue.setInstance(instance);

		user = iuser.getUser(user1);
		dataValue.setUser(user);

		element = ielement.getOneElmentById(element1);
		if (element == null) {
			System.out.println("element1:" + element1 + " non trouvé");
			return null;
		}
		dataValue.setElement(element);

		return dataValue;
	}

	public DataValue updateDataValue(DataValue dataValueAnc, DataValueTDO dataValueTDO, List<DataValue> dataValueList) {
		DataValue dataValue = new DataValue();
		int numero = 0;
		if (dataValueAnc == null) {
			dataValue.setDateCreation(new Date());
			if (dataValueList.isEmpty()) {
				numero = 1;
			} else {
				List<Integer> listNum = new ArrayList<>();
				for (int i = 0, j = dataValueList.size(); i < j; i++) {
					listNum.add(i);
				}
				Collections.sort(listNum);
				numero = listNum.get(listNum.size() - 1) + 1;
			}
		} else {
			dataValue = dataValueAnc;
		}
		dataValue.setValue(dataValueTDO.getValue());
		dataValue.setDateUpdate(new Date());
		dataValue.setNumero(numero);
		if (dataValueTDO.getUser() != null) {
			user = iuser.getUser(dataValueTDO.getUser());
			if (user != null)
				dataValue.setUser(user);
		}
		if (dataValueAnc == null) {
			if (dataValueTDO.getElement() != null) {
				dataValue.setElement(ielement.getOneElmentById(dataValueTDO.getElement()));
			} else
				return null;
			if (dataValueTDO.getInstance() != null) {
				dataValue.setInstance(iinstance.getOneInstance(dataValueTDO.getInstance()));
			} else
				return null;
		}

		return dataValue;
	}

	public InstanceTDO createInstance(String programme, String organisation, String user) {
		System.out.println(" entrer dans DataValueConvert - createInstance");
		InstanceTDO instanceTDO = new InstanceTDO();
		instanceTDO.setOrganisation(organisation);
		instanceTDO.setUser(user);
		instanceTDO.setProgramme(programme);
		return instanceTDO;

	}

	public Instance createNewInstance(DataInstance dataInstance) {
		InstanceTDO instanceTDO = new InstanceTDO();
		instanceTDO.setProgramme(dataInstance.getProgramme());
		instanceTDO.setOrganisation(dataInstance.getOrganisation());
		instanceTDO.setUser(dataInstance.getUser());
		instanceTDO.setDateActivite(dataInstance.getDateActivite());
		return iinstance.saveInstance(instanceTDO);
	}

	public Beneficiaire createBeneficiaire(DataInstance dataInstance, Instance instance) {
		Beneficiaire beneficiaire = ibeneficiaire.getOneBeneficiaireByIdDreams(dataInstance.getDreamsId());
		if (beneficiaire == null) {
			return null;
		}
		InstanceBeneficiaire instanceBeneficiaire = new InstanceBeneficiaire();
		instanceBeneficiaire.setInstance(instance);
		instanceBeneficiaire.setBeneficiaire(beneficiaire);
		instanceBeneficiaire.setDateAction(instance.getDateActivite());
		if(dataInstance.getOrder() == 0) {
			instanceBeneficiaire.setOrdre(1);
		}else {
			instanceBeneficiaire.setOrdre(dataInstance.getOrder());
		}
		
		beneficiaire.getInstanceBeneficiaires().add(instanceBeneficiaire);
		beneficiaire = ibeneficiaire.updateOneBeneficiaire(beneficiaire);
		return beneficiaire;
	}

	public Beneficiaire updateBeneficiaire(DataInstance dataInstance, Instance instance) {
		boolean trouve = false;
		Date dateActiv;
		Beneficiaire beneficiaire = ibeneficiaire.getOneBeneficiaireByIdDreams(dataInstance.getDreamsId());
		if (beneficiaire == null) {
			return null;
		}
			dateActiv = convertDate.getDateParse(dataInstance.getDateActivite());
			if(dateActiv == null)
				return null;

		for (int i = 0; i < beneficiaire.getInstanceBeneficiaires().size(); i++) {
			if (instance.getUid().equals(beneficiaire.getInstanceBeneficiaires().get(i).getInstance().getUid())) {
				if (beneficiaire.getInstanceBeneficiaires().get(i).getOrdre() == dataInstance.getOrder()) {
					if(dataInstance.getCodeId() == null && beneficiaire.getInstanceBeneficiaires().get(i).getCodeId() == null) {
						trouve = true;
						if (dataInstance.getOrganisation().equals("delete")) {
							beneficiaire.getInstanceBeneficiaires().get(i).setDateAction(null);
						}else {
							beneficiaire.getInstanceBeneficiaires().get(i).setDateAction(dateActiv);
						}
						
					}else {
						if(dataInstance.getCodeId() != null && beneficiaire.getInstanceBeneficiaires().get(i).getCodeId() != null) {
							if(dataInstance.getCodeId().equals(beneficiaire.getInstanceBeneficiaires().get(i).getCodeId())) {
								trouve = true;
								if (dataInstance.getOrganisation().equals("delete")) {
									beneficiaire.getInstanceBeneficiaires().get(i).setDateAction(null);
								}else {
									beneficiaire.getInstanceBeneficiaires().get(i).setDateAction(dateActiv);
								}
							}
						}
						
					}
					
				}
			}
		}

		if (!trouve) {
			InstanceBeneficiaire instanceBeneficiaire = new InstanceBeneficiaire();
			instanceBeneficiaire.setInstance(instance);
			instanceBeneficiaire.setBeneficiaire(beneficiaire);
			instanceBeneficiaire.setDateAction(dateActiv);
			instanceBeneficiaire.setCodeId(dataInstance.getCodeId());
			instanceBeneficiaire.setOrdre(dataInstance.getOrder());
			beneficiaire.getInstanceBeneficiaires().add(instanceBeneficiaire);
		}

		beneficiaire = ibeneficiaire.updateOneBeneficiaire(beneficiaire);
		return beneficiaire;
	}

	public DataValueTDO getDataValueTDO(DataValue dataValue) {
		DataValueTDO dataValueTDO = new DataValueTDO();
		dataValueTDO.setValue(dataValue.getValue());
		dataValueTDO.setNumero(dataValue.getNumero());
		if (dataValue.getDateCreation() != null)
			dataValueTDO.setDateCreation(convertDate.getDateString(dataValue.getDateCreation()));
		if (dataValue.getDateUpdate() != null)
			dataValueTDO.setDateUpdate(convertDate.getDateString(dataValue.getDateUpdate()));
		dataValueTDO.setInstance(dataValue.getInstance().getUid());
		if (dataValue.getUser() != null)
			dataValueTDO.setUser(dataValue.getUser().getUid());
		dataValueTDO.setElement(dataValue.getElement().getUid());
		return dataValueTDO;
	}

	public List<DataValueTDO> getDataValueTDOs(List<DataValue> dataValues) {
		List<DataValueTDO> dataValueTDOs = new ArrayList<DataValueTDO>();
		for (int i = 0; i < dataValues.size(); i++) {
			dataValueTDOs.add(getDataValueTDO(dataValues.get(i)));
		}
		return dataValueTDOs;
	}

	public DataValue getDataValueSepare(String instance, String element) {
		DataValue dataValue = new DataValue();
		Instance instance2 = new Instance();
		Element element2 = new Element();

		dataValue = contituerDataValue(instance, null, element);
		instance2 = dataValue.getInstance();
		element2 = dataValue.getElement();
		dataValue = dataValueRepository.getOneDataValue(instance2, element2);
		return dataValue;
	}

	public DataInstance contituerDataInstance(String instance, List<DataValue> dataValues) {
		DataInstance dataInstance = new DataInstance();
		Instance instance2 = iinstance.getOneInstance(instance);
		List<DataValueTDO> dataValueTDOs = new ArrayList<DataValueTDO>();
		dataValueTDOs = getDataValueTDOs(dataValues);
		dataInstance.setInstance(instance);
		dataInstance.setOrganisation(instance2.getOrganisation().getUid());
		dataInstance.setProgramme(instance2.getProgramme().getUid());
		if (instance2.getUser() != null)
			dataInstance.setUser(instance2.getUser().getUid());
		dataInstance.setDataValue(dataValueTDOs);
		return dataInstance;
	}

	public DataValue checkDataValue(DataValue dataValue) {
		if (dataValue.getElement() == null)
			return null;
		if (dataValue.getInstance() == null)
			return null;
		if (dataValue.getValue() == null) {
			return null;
		}
		if (dataValue.getNumero() != (int) dataValue.getNumero())
			dataValue.setNumero(1);

		dataValue.setDateCreation(new Date());
		dataValue.setDateUpdate(new Date());
		return dataValue;
	}

	private int getNumero(List<DataValue> dataValues) {
		int num = 0;
		for (int i = 0; i < dataValues.size(); i++) {
			if (num < dataValues.get(i).getNumero()) {
				num = dataValues.get(i).getNumero();
			}
		}
		num++;
		return num;
	}

	public Instance updateInstance(Instance instance, DataInstance dataInstance) {
		Date newDate = null;
		newDate = convertDate.getDateParse(dataInstance.getDateActivite());
		if(newDate.equals(instance.getDateActivite())) {
			return instance;
		}
		instance.setDateActivite(newDate);
		return iinstance.saveInstance(instance);
	}

}
