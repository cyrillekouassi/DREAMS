package ci.jsi.importExport.traitement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.dataValue.DataInstance;
import ci.jsi.entites.dataValue.DataValue;
import ci.jsi.entites.dataValue.DataValueTDO;
import ci.jsi.entites.dataValue.IdataValues;
import ci.jsi.entites.element.Element;
import ci.jsi.entites.element.Ielement;
import ci.jsi.entites.instance.Iinstance;
import ci.jsi.entites.instance.Instance;
import ci.jsi.entites.instance.InstanceTDO;
import ci.jsi.entites.programme.Iprogramme;
import ci.jsi.entites.programme.Programme;
import ci.jsi.initialisation.ElementValue;

@Service
public class ServicesDreams {
	
	private String[] psychoQuestion = {"_02_amis","_04_person_confiance","_07_trist_angoiss"};
	private String[] psychoReponse = {"NON","NON","OUI"};
	private ElementValue[] psychoUid = new ElementValue[3];
	private String[] psychoVBGQuestion = {"_01_humil_public","_02_menace_nuire","_03_injure","_04_menace_prive","_05_porte_main","_06_viol","_07_abus_sexuel"};
	private String[] psychoVBGReponse = {"OUI","OUI","OUI","OUI","OUI","OUI","OUI"};
	private ElementValue[] psychoVBGUid = new ElementValue[7];
	private String[] psychoAlcDrogQuestion = {"_03_raison_conso_alcool","_03_raison_conso_alcool","_06_raison_conso_drogue","_06_raison_conso_drogue"};
	private String[] psychoAlcDrogReponse = {"SOLITUDE","ANGOISSE","SOLITUDE","ANGOISSE"};
	private ElementValue[] psychoAlcDrogUid = new ElementValue[4]; 
	private String[] educationQuestion = {"_03_soutient_finance"};
	private String[] educationReponse = {"MOI"};
	private ElementValue[] educationUid = new ElementValue[1];
	private String[] educationFraisQuestion = {"_05_raison_non_sco","_06_second_chance"};
	private String[] educationFraisReponse = {"ARGENT","COURS"};
	private ElementValue[] educationFraisUid = new ElementValue[2];
	private String[] preservatifQuestion = {"age_enrol","_04_part_sexuel"};
	private String[] preservatifReponse = {"16","OUI"};
	private ElementValue[] preservatifUid = new ElementValue[2]; 
	private String[] contraceptionQuestion = {"_01_deja_enceinte"};
	private String[] contraceptionReponse = {"OUI"};
	private ElementValue[] contraceptionUid = new ElementValue[1];
	private String[] contraceptionAgeSexeQuestion = {"age_enrol","_04_part_sexuel"};
	private String[] contraceptionAgeSexeReponse = {"16","OUI"}; 
	private ElementValue[] contraceptionAgeSexeUid = new ElementValue[2];
	private String[] contraceptionAgePfQuestion = {"age_enrol","_06_raison_method_pf_non_util"};
	private String[] contraceptionAgePfReponse = {"16","NeSAITouTROUVER"};
	private ElementValue[] contraceptionAgePfUid = new ElementValue[2];
	private String[] conseilsSexeTestQuestion = {"_01_rela_sexuel","_01_test_vih"};
	private String[] conseilsSexeTestReponse = {"OUI","NON"};
	private ElementValue[] conseilsSexeTestUid = new ElementValue[2];
	private String[] conseilsSexederTestQuestion = {"_01_rela_sexuel","_02_dernier_test"};
	private String[] conseilsSexederTestReponse = {"OUI","Plus3MOIS"};
	private ElementValue[] conseilsSexederTestUid = new ElementValue[2]; 
	private String[] soinsVBGQuestion = {"_05_porte_main","_06_viol","_07_abus_sexuel"};
	private String[] soinsVBGReponse = {"OUI","OUI","OUI"};
	private ElementValue[] soinsVBGUid = new ElementValue[3]; 
	private String[] alimentaireQuestion = {"_01_nbre_repas","_02_element_repas"};
	private String[] alimentaireReponse = {"1","AUC"};
	private ElementValue[] alimentaireUid = new ElementValue[2];
	private String[] protectionQuestion = {"_01_doc_att_age"};
	private String[] protectionReponse = {"NON"};
	private ElementValue[] protectionUid = new ElementValue[1];
	private String[] parentingfinanciereQuestion = {"_01_chef_menage","_03_soutient_finance","_05_raison_non_sco","_01_nbre_repas"};
	private String[] parentingfinanciereReponse = {"MOI","MOI","ARGENT","1"};
	private ElementValue[] parentingfinanciereUid = new ElementValue[4];
	private String[] parentingVBGQuestion = {"_05_porte_main","_06_viol","_07_abus_sexuel"};
	private String[] parentingVBGReponse = {"OUI","OUI","OUI"};
	private ElementValue[] parentingVBGUid = new ElementValue[3]; 
	private String[] parentingparentQuestion = {"_05_raison_non_sco","_05_raison_non_sco","_03_raison_conso_alcool","_06_raison_conso_drogue"};
	private String[] parentingparentReponse = {"PRESSIONS","MARIE","DIFFICULTE","DIFFICULTE"};
	private ElementValue[] parentingparentUid = new ElementValue[4];
	
	private String vert = "green";
	private String jaune = "yellow";
	private String rouge = "red";
	private String color;
	
	
	@Autowired
	private Ielement ielement;
	@Autowired
	private Iinstance iinstance;
	@Autowired
	private IdataValues idataValues;
	@Autowired
	private Iprogramme iprogramme;
	
	private Programme programme = null;
	private Element element = null;
	private Instance instance;
	private List<DataValue> dataValues;
	private DataInstance dataInstance = null;
	private List<DataValueTDO> dataValueTDOs;
	
	public ServicesDreams() {
		
	}
	
	private void genererPsycho() {
		System.out.println("Entrer dans ServicesDreams - genererPsycho");
		
		for(int i = 0;i<psychoQuestion.length;i++) {
			ElementValue elementValue= new ElementValue();
			element = ielement.getOneElmentByCode(psychoQuestion[i]);
			elementValue.setId(element.getUid());
			elementValue.setValue(psychoReponse[i]);
			psychoUid[i] = elementValue;
		}
		for(int i = 0;i<psychoVBGQuestion.length;i++) {
			ElementValue elementValue= new ElementValue();
			element = ielement.getOneElmentByCode(psychoVBGQuestion[i]);
			elementValue.setId(element.getUid());
			elementValue.setValue(psychoVBGReponse[i]);
			psychoVBGUid[i] = elementValue;
			
		}  
		for(int i = 0;i<psychoAlcDrogQuestion.length;i++) {
			ElementValue elementValue= new ElementValue();
			element = ielement.getOneElmentByCode(psychoAlcDrogQuestion[i]);
			elementValue.setId(element.getUid());
			elementValue.setValue(psychoAlcDrogReponse[i]);
			psychoAlcDrogUid[i] = elementValue;
			
		}
	}
	     
	private void genererEducation() {
		System.out.println("Entrer dans ServicesDreams - genererEducation");
		
		for(int i = 0;i<educationQuestion.length;i++) {
			ElementValue elementValue= new ElementValue();
			element = ielement.getOneElmentByCode(educationQuestion[i]);
			elementValue.setId(element.getUid());
			elementValue.setValue(educationReponse[i]);
			educationUid[i] = elementValue;
		}
		for(int i = 0;i<educationFraisQuestion.length;i++) {
			ElementValue elementValue= new ElementValue();
			element = ielement.getOneElmentByCode(educationFraisQuestion[i]);
			elementValue.setId(element.getUid());
			elementValue.setValue(educationFraisReponse[i]);
			educationFraisUid[i] = elementValue;
		}
	}
	  
	private void genererPreservatif() {
		System.out.println("Entrer dans ServicesDreams - genererPreservatif");
		
		for(int i = 0;i<preservatifQuestion.length;i++) {
			ElementValue elementValue= new ElementValue();
			element = ielement.getOneElmentByCode(preservatifQuestion[i]);
			elementValue.setId(element.getUid());
			elementValue.setCode(element.getCode());
			elementValue.setValue(preservatifReponse[i]);
			preservatifUid[i] = elementValue;
		}
	}
	
	private void genererContraception() {
		System.out.println("Entrer dans ServicesDreams - genererContraception");
		
		for(int i = 0;i<contraceptionQuestion.length;i++) {
			ElementValue elementValue= new ElementValue();
			element = ielement.getOneElmentByCode(contraceptionQuestion[i]);
			elementValue.setId(element.getUid());
			elementValue.setCode(element.getCode());
			elementValue.setValue(contraceptionReponse[i]);
			contraceptionUid[i] = elementValue;
		}
		for(int i = 0;i<contraceptionAgeSexeQuestion.length;i++) {
			ElementValue elementValue= new ElementValue();
			element = ielement.getOneElmentByCode(contraceptionAgeSexeQuestion[i]);
			elementValue.setId(element.getUid());
			elementValue.setCode(element.getCode());
			elementValue.setValue(contraceptionAgeSexeReponse[i]);
			contraceptionAgeSexeUid[i] = elementValue;
		}
		for(int i = 0;i<contraceptionAgePfQuestion.length;i++) {
			ElementValue elementValue= new ElementValue();
			element = ielement.getOneElmentByCode(contraceptionAgePfQuestion[i]);
			elementValue.setId(element.getUid());
			elementValue.setCode(element.getCode());
			elementValue.setValue(contraceptionAgePfReponse[i]);
			contraceptionAgePfUid[i] = elementValue;
		}
	}
	     
	private void genererConseils() {
		System.out.println("Entrer dans ServicesDreams - genererConseils");
		
		   for(int i = 0;i<conseilsSexeTestQuestion.length;i++) {
				ElementValue elementValue= new ElementValue();
				element = ielement.getOneElmentByCode(conseilsSexeTestQuestion[i]);
				elementValue.setId(element.getUid());
				elementValue.setValue(conseilsSexeTestReponse[i]);
				conseilsSexeTestUid[i] = elementValue;
			}
		   for(int i = 0;i<conseilsSexederTestQuestion.length;i++) {
				ElementValue elementValue= new ElementValue();
				element = ielement.getOneElmentByCode(conseilsSexederTestQuestion[i]);
				elementValue.setId(element.getUid());
				elementValue.setValue(conseilsSexederTestReponse[i]);
				conseilsSexederTestUid[i] = elementValue;
			}
	}
	  
	private void genererSoinsVBG() {
		System.out.println("Entrer dans ServicesDreams - genererSoinsVBG");
		
		for(int i = 0;i<soinsVBGQuestion.length;i++) {
			ElementValue elementValue= new ElementValue();
			element = ielement.getOneElmentByCode(soinsVBGQuestion[i]);
			elementValue.setId(element.getUid());
			elementValue.setValue(soinsVBGReponse[i]);
			soinsVBGUid[i] = elementValue;
		}
	}  
	
	private void genererAlimentaire() {
		System.out.println("Entrer dans ServicesDreams - genererAlimentaire");
		
		for(int i = 0;i<alimentaireQuestion.length;i++) {
			ElementValue elementValue= new ElementValue();
			element = ielement.getOneElmentByCode(alimentaireQuestion[i]);
			elementValue.setId(element.getUid());
			elementValue.setCode(element.getCode());
			elementValue.setValue(alimentaireReponse[i]);
			alimentaireUid[i] = elementValue;
		}
	}
	  
	private void genererProtection() {
		System.out.println("Entrer dans ServicesDreams - genererProtection");
		
		for(int i = 0;i<protectionQuestion.length;i++) {
			ElementValue elementValue= new ElementValue();
			element = ielement.getOneElmentByCode(protectionQuestion[i]);
			elementValue.setId(element.getUid());
			elementValue.setValue(protectionReponse[i]);
			protectionUid[i] = elementValue;
		}
	}
	 
	private void genererParenting() {
		System.out.println("Entrer dans ServicesDreams - genererParenting");
		
		for(int i = 0;i<parentingfinanciereQuestion.length;i++) {
			ElementValue elementValue= new ElementValue();
			element = ielement.getOneElmentByCode(parentingfinanciereQuestion[i]);
			elementValue.setId(element.getUid());
			elementValue.setCode(element.getCode());
			elementValue.setValue(parentingfinanciereReponse[i]);
			parentingfinanciereUid[i] = elementValue;
		}
		for(int i = 0;i<parentingVBGQuestion.length;i++) {
			ElementValue elementValue= new ElementValue();
			element = ielement.getOneElmentByCode(parentingVBGQuestion[i]);
			elementValue.setId(element.getUid());
			elementValue.setValue(parentingVBGReponse[i]);
			parentingVBGUid[i] = elementValue;
		}
		for(int i = 0;i<parentingparentQuestion.length;i++) {
			ElementValue elementValue= new ElementValue();
			element = ielement.getOneElmentByCode(parentingparentQuestion[i]);
			elementValue.setId(element.getUid());
			elementValue.setValue(parentingparentReponse[i]);
			parentingparentUid[i] = elementValue;
		}
	}
	
	public void genererService() {
		//System.out.println("Entrer dans ServicesDreams - genererService");
		genererPsycho();
		genererEducation();
		genererPreservatif();
		genererContraception();
		genererConseils();
		genererSoinsVBG();
		genererAlimentaire();
		genererProtection();
		genererParenting();
		//System.out.println("Sortir de ServicesDreams - genererService");
	}
	
	public Instance evaluerService(Instance instance,String dateEnrol) {
		System.out.println("Entrer dans ServicesDreams - evaluerService");
		dataValues = new ArrayList<DataValue>();
		dataValueTDOs = new ArrayList<DataValueTDO>();
		InstanceTDO instanceTDO = new InstanceTDO();
		programme = iprogramme.getOneProgrammeByCode("besoinBeneficiare");
		
		dataInstance = idataValues.getDataInstance(instance.getUid());
		dataValueTDOs = dataInstance.getDataValue();
		
		instanceTDO.setOrganisation(instance.getOrganisation().getUid());
		instanceTDO.setProgramme(programme.getUid());
		if(instance.getUser() != null)
			instanceTDO.setUser(instance.getUser().getUid());
		instanceTDO.setDateActivite(dateEnrol);
		this.instance = iinstance.saveInstance(instanceTDO);
		
		lesService();
		dataValues = idataValues.saveAllDataValue(dataValues);

		//System.out.println("Sortir de ServicesDreams - evaluerService");
		return this.instance;
	}
	
	private void lesService() {
		System.out.println("Entrer dans ServicesDreams - lesService");
		idBenef();
		nomBenef();
		prenomBenef();
		ServicePsycho();
		ServiceEducation();
		ServicePreservatif();
		ServiceContraception();
		ServiceConseils();
		ServiceSoinsVBG();
		ServiceAlimentaire();
		ServiceProtection();
		ServiceParenting();

		System.out.println("Sortir de ServicesDreams - lesService");
	}
	
	private void idBenef() {
		String id_dreams = "";
		System.out.println("Entrer dans ServicesDreams - idBenef");
		element = ielement.getOneElmentByCode("id_dreams");
		if(element == null)
			return;
		for(int j=0;j<dataValueTDOs.size();j++) {
			if(dataValueTDOs.get(j).getElement().equals(element.getUid())) {
				id_dreams = dataValueTDOs.get(j).getValue();
				break;
			}
		}
		DataValue dataValue = new DataValue();
		dataValue.setInstance(this.instance);
		dataValue.setUser(this.instance.getUser());
		dataValue.setElement(element);
		dataValue.setValue(id_dreams);
		dataValue.setNumero(1);
		dataValue.setDateCreation(new Date());
		dataValue.setDateUpdate(new Date());
		dataValues.add(dataValue);
	}
	
	private void nomBenef() {
		String nomBenef = "";
		System.out.println("Entrer dans ServicesDreams - nomBenef");
		element = ielement.getOneElmentByCode("nom");
		if(element == null)
			return;
		for(int j=0;j<dataValueTDOs.size();j++) {
			if(dataValueTDOs.get(j).getElement().equals(element.getUid())) {
				nomBenef = dataValueTDOs.get(j).getValue();
				break;
			}
		}
		DataValue dataValue = new DataValue();
		dataValue.setInstance(this.instance);
		dataValue.setUser(this.instance.getUser());
		dataValue.setElement(element);
		dataValue.setValue(nomBenef);
		dataValue.setNumero(1);
		dataValue.setDateCreation(new Date());
		dataValue.setDateUpdate(new Date());
		dataValues.add(dataValue);
	}
	
	private void prenomBenef() {
		String prenomBenef = "";
		System.out.println("Entrer dans ServicesDreams - prenomBenef");
		element = ielement.getOneElmentByCode("pren");
		if(element == null)
			return;
		for(int j=0;j<dataValueTDOs.size();j++) {
			if(dataValueTDOs.get(j).getElement().equals(element.getUid())) {
				prenomBenef = dataValueTDOs.get(j).getValue();
				break;
			}
		}
		DataValue dataValue = new DataValue();
		dataValue.setInstance(this.instance);
		dataValue.setUser(this.instance.getUser());
		dataValue.setElement(element);
		dataValue.setValue(prenomBenef);
		dataValue.setNumero(1);
		dataValue.setDateCreation(new Date());
		dataValue.setDateUpdate(new Date());
		dataValues.add(dataValue);
	}
	
	private void ServicePsycho() {
		System.out.println("Entrer dans ServicesDreams - ServicePsycho");
		
		int score = 0;
		element = ielement.getOneElmentByCode("psychoEvDB");
		if(element == null)
			return;
		
		for(int i=0;i<psychoUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(psychoUid[i].getId())) {
					int cont = dataValueTDOs.get(j).getValue().indexOf(psychoUid[i].getValue());
					if(cont != -1) {
						score++;
					}
					break;
				}
			}
		}
		
		Boolean juste = false;
		for(int i=0;i<psychoVBGUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(psychoVBGUid[i].getId())) {
					int cont = dataValueTDOs.get(j).getValue().indexOf(psychoVBGUid[i].getValue());
					if(cont != -1) {
						juste = true;
					}
					break;
				}
				
			}
		}
		
		if(juste) {
			score++;
		}
		
		juste = false;
		for(int i=0;i<psychoAlcDrogUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(psychoAlcDrogUid[i].getId())) {
					int cont = dataValueTDOs.get(j).getValue().indexOf(psychoAlcDrogUid[i].getValue());
					if(cont != -1) {
						juste = true;
					}
					break;
				}
				
			}
		}
		if(juste) {
			score++;
		}
		this.color = this.vert;
		if(score>0)
			this.color = this.jaune;
		if(score>2)
			this.color = this.rouge;
		
		DataValue dataValue = new DataValue();
		dataValue.setInstance(this.instance);
		dataValue.setUser(this.instance.getUser());
		dataValue.setElement(element);
		dataValue.setValue(this.color);
		dataValue.setNumero(score);
		dataValue.setDateCreation(new Date());
		dataValue.setDateUpdate(new Date());
		dataValues.add(dataValue);
		
	}
	
	private void ServiceEducation() {
		System.out.println("Entrer dans ServicesDreams - ServiceEducation");
		
		int score = 0;
		element = ielement.getOneElmentByCode("educationEvDB");
		if(element == null)
			return;
		
		for(int i=0;i<educationUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(educationUid[i].getId())) {
					int cont = dataValueTDOs.get(j).getValue().indexOf(educationUid[i].getValue());
					if(cont != -1) {
						score++;
					}
					break;
				}
			}
		}
		
		Boolean juste = null;
		for(int i=0;i<educationFraisUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(educationFraisUid[i].getId())) {
					int cont = dataValueTDOs.get(j).getValue().indexOf(educationFraisUid[i].getValue());
					Boolean temp = null;
					if(cont != -1) {
						temp = true;
					}else {
						temp = false;
					}
					if(juste == null) {
						juste = temp;
					}else {
						if(juste && temp) {
							juste = true;
						}else {
							juste = false;
						}
					}
					break;
				}
				
			}
		}
		
		if(juste != null) {
			if(juste) {
				score++;
			}
			
		}
		
		this.color = this.vert;
		if(score>0)
			this.color = this.jaune;
		if(score>1)
			this.color = this.rouge;
		
		DataValue dataValue = new DataValue();
		dataValue.setInstance(this.instance);
		dataValue.setUser(this.instance.getUser());
		dataValue.setElement(element);
		dataValue.setValue(this.color);
		dataValue.setNumero(score);
		dataValue.setDateCreation(new Date());
		dataValue.setDateUpdate(new Date());
		dataValues.add(dataValue);
		
	}
	
	private void ServicePreservatif() {
		System.out.println("Entrer dans ServicesDreams - ServicePreservatif");
		
		int score = 0;
		element = ielement.getOneElmentByCode("preservatifEvDB");
		if(element == null)
			return;
		Boolean juste = null;
		for(int i=0;i<preservatifUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(preservatifUid[i].getId())) {
					Boolean temp = null;
					int cont = -1;
					if(preservatifUid[i].getCode().equals("age_enrol")) {
						if(Integer.valueOf(dataValueTDOs.get(j).getValue()).intValue() >= Integer.valueOf(preservatifUid[i].getValue()).intValue()) {
							cont = 2;
						}
					}else {
						cont = dataValueTDOs.get(j).getValue().indexOf(preservatifUid[i].getValue());
					}
										
					if(cont != -1) {
						temp = true;
					}else {
						temp = false;
					}
					if(juste == null) {
						juste = temp;
					}else {
						if(juste && temp) {
							juste = true;
						}else {
							juste = false;
						}
					}
					break;
				}
				
			}
		}
		
		if(juste != null) {
			if(juste) {
				score++;
			}
			
		}
		
		this.color = this.vert;
		if(score>0)
			this.color = this.rouge;
		
		DataValue dataValue = new DataValue();
		dataValue.setInstance(this.instance);
		dataValue.setUser(this.instance.getUser());
		dataValue.setElement(element);
		dataValue.setValue(this.color);
		dataValue.setNumero(score);
		dataValue.setDateCreation(new Date());
		dataValue.setDateUpdate(new Date());
		dataValues.add(dataValue);
		
	}
	
	private void ServiceContraception() {
		System.out.println("Entrer dans ServicesDreams - ServiceContraception");
		
		int score = 0;
		element = ielement.getOneElmentByCode("contraceptionEvDB");
		if(element == null)
			return;
		
		for(int i=0;i<contraceptionUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(contraceptionUid[i].getId())) {
					int cont = dataValueTDOs.get(j).getValue().indexOf(contraceptionUid[i].getValue());
					if(cont != -1) {
						score++;
					}
					break;
				}
			}
		}
		
		Boolean juste = null;
		for(int i=0;i<contraceptionAgeSexeUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(contraceptionAgeSexeUid[i].getId())) {
					Boolean temp = null;
					int cont = -1;
					if(contraceptionAgeSexeUid[i].getCode().equals("age_enrol")) {
						if(Integer.valueOf(dataValueTDOs.get(j).getValue()).intValue() >= Integer.valueOf(contraceptionAgeSexeUid[i].getValue()).intValue()) {
							cont = 2;
						}
					}else {
						cont = dataValueTDOs.get(j).getValue().indexOf(contraceptionAgeSexeUid[i].getValue());
					}
										
					if(cont != -1) {
						temp = true;
					}else {
						temp = false;
					}
					if(juste == null) {
						juste = temp;
					}else {
						if(juste && temp) {
							juste = true;
						}else {
							juste = false;
						}
					}
					break;
				}
				
			}
		}
		
		if(juste != null) {
			if(juste) {
				score++;
			}	
		}
		juste = null;
		for(int i=0;i<contraceptionAgePfUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(contraceptionAgePfUid[i].getId())) {
					Boolean temp = null;
					int cont = -1;
					if(contraceptionAgePfUid[i].getCode().equals("age_enrol")) {
						if(Integer.valueOf(dataValueTDOs.get(j).getValue()).intValue() >= Integer.valueOf(contraceptionAgePfUid[i].getValue()).intValue()) {
							cont = 2;
						}
					}else {
						cont = dataValueTDOs.get(j).getValue().indexOf(contraceptionAgePfUid[i].getValue());
					}
										
					if(cont != -1) {
						temp = true;
					}else {
						temp = false;
					}
					if(juste == null) {
						juste = temp;
					}else {
						if(juste && temp) {
							juste = true;
						}else {
							juste = false;
						}
					}
					break;
				}
				
			}
		}
		
		if(juste != null) {
			if(juste) {
				score++;
			}	
		}
		
		this.color = this.vert;
		if(score>0)
			this.color = this.rouge;
		
		DataValue dataValue = new DataValue();
		dataValue.setInstance(this.instance);
		dataValue.setUser(this.instance.getUser());
		dataValue.setElement(element);
		dataValue.setValue(this.color);
		dataValue.setNumero(score);
		dataValue.setDateCreation(new Date());
		dataValue.setDateUpdate(new Date());
		dataValues.add(dataValue);
		
	}
	
	private void ServiceConseils() {
		System.out.println("Entrer dans ServicesDreams - ServiceConseils");
		
		int score = 0;
		element = ielement.getOneElmentByCode("conseilVIHEvDB");
		if(element == null)
			return;
				
		Boolean juste = null;
		for(int i=0;i<conseilsSexeTestUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(conseilsSexeTestUid[i].getId())) {
					int cont = dataValueTDOs.get(j).getValue().indexOf(conseilsSexeTestUid[i].getValue());
					Boolean temp = null;
					if(cont != -1) {
						temp = true;
					}else {
						temp = false;
					}
					if(juste == null) {
						juste = temp;
					}else {
						if(juste && temp) {
							juste = true;
						}else {
							juste = false;
						}
					}
					break;
				}
				
			}
		}
		
		if(juste != null) {
			if(juste) {
				score++;
			}	
		}
		
		juste = null;
		for(int i=0;i<conseilsSexederTestUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(conseilsSexederTestUid[i].getId())) {
					int cont = dataValueTDOs.get(j).getValue().indexOf(conseilsSexederTestUid[i].getValue());
					Boolean temp = null;
					if(cont != -1) {
						temp = true;
					}else {
						temp = false;
					}
					if(juste == null) {
						juste = temp;
					}else {
						if(juste && temp) {
							juste = true;
						}else {
							juste = false;
						}
					}
					break;
				}
				
			}
		}
		
		if(juste != null) {
			if(juste) {
				score++;
			}	
		}
		
		this.color = this.vert;
		if(score>0)
			this.color = this.rouge;
		
		DataValue dataValue = new DataValue();
		dataValue.setInstance(this.instance);
		dataValue.setUser(this.instance.getUser());
		dataValue.setElement(element);
		dataValue.setValue(this.color);
		dataValue.setNumero(score);
		dataValue.setDateCreation(new Date());
		dataValue.setDateUpdate(new Date());
		dataValues.add(dataValue);
		
	}
	
	private void ServiceSoinsVBG() {
		System.out.println("Entrer dans ServicesDreams - ServiceSoinsVBG");
		
		int score = 0;
		element = ielement.getOneElmentByCode("soinsVBGEvDB");
		if(element == null)
			return;
				
		Boolean juste = false;
		for(int i=0;i<soinsVBGUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(soinsVBGUid[i].getId())) {
					int cont = dataValueTDOs.get(j).getValue().indexOf(soinsVBGUid[i].getValue());
					if(cont != -1) {
						juste = true;
					}
					break;
				}
				
			}
		}
		if(juste) {
			score++;
		}
		this.color = this.vert;
		if(score>0)
			this.color = this.rouge;
		
		DataValue dataValue = new DataValue();
		dataValue.setInstance(this.instance);
		dataValue.setUser(this.instance.getUser());
		dataValue.setElement(element);
		dataValue.setValue(this.color);
		dataValue.setNumero(score);
		dataValue.setDateCreation(new Date());
		dataValue.setDateUpdate(new Date());
		dataValues.add(dataValue);
		
	}
	
	private void ServiceAlimentaire() {
		System.out.println("Entrer dans ServicesDreams - ServiceAlimentaire");
		
		int score = 0;
		element = ielement.getOneElmentByCode("alimentaireEvDB");
		if(element == null)
			return;
		
		for(int i=0;i<alimentaireUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(alimentaireUid[i].getId())) {
					int cont = -1;					
					if(alimentaireUid[i].getCode().equals("_01_nbre_repas")) {
						if(Integer.valueOf(dataValueTDOs.get(j).getValue()).intValue() == Integer.valueOf(alimentaireUid[i].getValue()).intValue()) {
							cont = 2;
						}
					}else {
						cont = dataValueTDOs.get(j).getValue().indexOf(alimentaireUid[i].getValue());
					}
					if(cont != -1) {
						score++;
					}
					break;
				}
			}
		}
		
		
		this.color = this.vert;
		if(score>0)
			this.color = this.rouge;
		
		DataValue dataValue = new DataValue();
		dataValue.setInstance(this.instance);
		dataValue.setUser(this.instance.getUser());
		dataValue.setElement(element);
		dataValue.setValue(this.color);
		dataValue.setNumero(score);
		dataValue.setDateCreation(new Date());
		dataValue.setDateUpdate(new Date());
		dataValues.add(dataValue);
		
	}

	private void ServiceProtection() {
		System.out.println("Entrer dans ServicesDreams - ServiceProtection");
		
		int score = 0;
		element = ielement.getOneElmentByCode("protectionEvDB");
		if(element == null)
			return;
		
		for(int i=0;i<protectionUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(protectionUid[i].getId())) {
					int cont = dataValueTDOs.get(j).getValue().indexOf(protectionUid[i].getValue());
					if(cont != -1) {
						score++;
					}
					break;
				}
			}
		}
		
		
		this.color = this.vert;
		if(score>0)
			this.color = this.rouge;
		
		DataValue dataValue = new DataValue();
		dataValue.setInstance(this.instance);
		dataValue.setUser(this.instance.getUser());
		dataValue.setElement(element);
		dataValue.setValue(this.color);
		dataValue.setNumero(score);
		dataValue.setDateCreation(new Date());
		dataValue.setDateUpdate(new Date());
		dataValues.add(dataValue);
		
	}
	
	private void ServiceParenting() {
		System.out.println("Entrer dans ServicesDreams - ServiceParenting");
		
		int score = 0;
		element = ielement.getOneElmentByCode("parentingEvDB");
		if(element == null)
			return;
		
		Boolean juste = false;
		for(int i=0;i<parentingfinanciereUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(parentingfinanciereUid[i].getId())) {
					int cont = -1;
					if(parentingfinanciereUid[i].getCode().equals("_01_nbre_repas")) {
						if(Integer.valueOf(dataValueTDOs.get(j).getValue()).intValue() == Integer.valueOf(parentingfinanciereUid[i].getValue()).intValue()) {
							cont = 2;
						}
					}else {
						cont = dataValueTDOs.get(j).getValue().indexOf(parentingfinanciereUid[i].getValue());
					}
										
					if(cont != -1) {
						juste = true;
					}
					break;
				}
				
			}
		}
		
		if(juste) {
			score++;
		}
		juste = false;
		for(int i=0;i<parentingVBGUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(parentingVBGUid[i].getId())) {
					int cont = dataValueTDOs.get(j).getValue().indexOf(parentingVBGUid[i].getValue());
					if(cont != -1) {
						juste = true;
					}
					break;
				}
				
			}
		}
		if(juste) {
			score++;
		}
		
		juste = false;
		for(int i=0;i<parentingparentUid.length;i++) {
			for(int j=0;j<dataValueTDOs.size();j++) {
				if(dataValueTDOs.get(j).getElement().equals(parentingparentUid[i].getId())) {
					int cont = dataValueTDOs.get(j).getValue().indexOf(parentingparentUid[i].getValue());
					if(cont != -1) {
						juste = true;
					}
					break;
				}
				
			}
		}
		if(juste) {
			score++;
		}
		
		
		this.color = this.vert;
		if(score>0)
			this.color = this.jaune;
		if(score>1)
			this.color = this.rouge;
		
		DataValue dataValue = new DataValue();
		dataValue.setInstance(this.instance);
		dataValue.setUser(this.instance.getUser());
		dataValue.setElement(element);
		dataValue.setValue(this.color);
		dataValue.setNumero(score);
		dataValue.setDateCreation(new Date());
		dataValue.setDateUpdate(new Date());
		dataValues.add(dataValue);
		
	}
	
	
}




















