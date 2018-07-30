package ci.jsi.importExport.traitement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

import ci.jsi.entites.beneficiaire.Beneficiaire;
import ci.jsi.entites.beneficiaire.BeneficiaireTDO;
import ci.jsi.entites.beneficiaire.Ibeneficiaire;
import ci.jsi.entites.beneficiaire.InstanceBeneficiaire;
import ci.jsi.entites.dataValue.DataValue;
import ci.jsi.entites.dataValue.IdataValues;
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
import ci.jsi.initialisation.ResultatRequete;
import ci.jsi.initialisation.UidEntitie;

@Service
public class DataValuesImport {
	
	@Autowired
	Ielement ielement;
	@Autowired
	Iprogramme iprogramme;
	@Autowired
	Iinstance iinstance;
	@Autowired
	Iorganisation iorganisation;
	@Autowired
	Iuser iuser;
	@Autowired
	IdataValues idataValues;
	@Autowired
	ServicesDreams servicesDreams = new ServicesDreams();
	@Autowired
	ConvertDate convertDate;
	@Autowired
	Ibeneficiaire ibeneficiaire;
	
	ResultatRequete resultatRequete;
	
	
	
	//private String contitEntete[] = { "programmeid", "instanceid", "organisationid", "organisationcode", "userid","numeroElement" };
	private List<String[]> myEntries;
	private List<Element>  listElement = new ArrayList<Element>();
	private List<Integer>  listIndex = new ArrayList<Integer>();
	private List<String> noElement = new ArrayList<String>();
	
	private String progId = null;
	/*private int orgid = -1;
	private int orgcode = -1;*/
	private int codeSafespace = -1;
	private int codeCentreSociale = -1;
	private int userid = -1;
	private int dat_enrol = -1;
	private int nom = -1;
	private int pren = -1;
	private int dat_nais = -1;
	private int age_enrol = -1;
	private int id_dreams = -1;
	private int tel = -1;
	
	@SuppressWarnings("deprecation")
	public ResultatRequete lireCSV(String fileName) {
		System.out.println("////// entrer DataValuesImport lireCSV");
		resultatRequete = new ResultatRequete();
		String chemin = File.separator+"DREAMS"+File.separator+"upload-dir"+File.separator;
		String file = chemin+fileName;
		//String file = "DREAMS/upload-dir/" + fileName;
		servicesDreams.genererService();
		progId = getProgramme();
		if(progId == null) {
			resultatRequete.setStatus("Progrmme Enrolement inexistant");
			resultatRequete.setIgnore(myEntries.size());
			return resultatRequete;
		}
		
		CSVReader csvReader = null;

		try {
			csvReader = new CSVReader(new FileReader(file), ';');
			// String[] ligne;

			// List<String[]> myEntries = csvReader.readAll();
			myEntries = csvReader.readAll();
			if (controleEntete(myEntries.get(0))) {
				seachElement(myEntries.get(0));
				if(!listElement.isEmpty()) {
					//System.err.println("nbre d'element trouvé: "+listElement.size());
					myEntries.remove(0);
					insertData();
					resultatRequete.setStatus("succes");
				}else {
					resultatRequete.setStatus("Aucun Code Element trouvé");
					resultatRequete.setIgnore(myEntries.size());
				}
			}
			else {
				resultatRequete.setStatus("Entete invalide");
				resultatRequete.setIgnore(myEntries.size());
			}
		} catch (FileNotFoundException e) {
			resultatRequete.setStatus("Fichier non trouvé");
			resultatRequete.setIgnore(myEntries.size());
		} catch (IOException io) {
			resultatRequete.setStatus("Fichier illisible");
			resultatRequete.setIgnore(myEntries.size());
		}
		
		return resultatRequete;
	}
	
	private String getProgramme() {
		Programme programme = iprogramme.getOneProgrammeByCode("enrolement");
		if(programme == null) {
			return null;
		}
		return programme.getUid();
	}
	
	public boolean controleEntete(String[] ligne) {
		System.out.println("Entrer dans DataValuesImport - controleEntete");
		
		for (int i = 0; i < ligne.length; i++) {
			
			if (ligne[i].equals("codeSafespace")) {
				codeSafespace = i;
			}
			if (ligne[i].equals("userid")) {
				userid = i;
			}
			if (ligne[i].equals("centre_social")) {
				codeCentreSociale = i;
			}
			
			if (ligne[i].equals("dat_enrol")) {
				dat_enrol = i;
			}
			if (ligne[i].equals("nom")) {
				nom = i;
			}
			if (ligne[i].equals("pren")) {
				pren = i;
			}
			if (ligne[i].equals("dat_nais")) {
				dat_nais = i;
			}
			if (ligne[i].equals("age_enrol")) {
				age_enrol = i;
			}
			if (ligne[i].equals("id_dreams")) {
				id_dreams = i;
			}
			if (ligne[i].equals("tel")) {
				tel = i;
			}

		}
		
		if(codeCentreSociale == -1 || codeSafespace == -1 || dat_enrol == -1 || nom == -1 || dat_nais == -1 || age_enrol == -1 || id_dreams == -1) {
			return false;
		}
		
		return true;
	}

	public void seachElement(String[] ligne) {
		System.out.println("Entrer dans DataValuesImport - seachElement");
		
		
		for (int i = 0; i < ligne.length; i++) {
			if(i != codeCentreSociale &&  i != codeSafespace &&  i != userid) {
				ajoutElement(ligne[i],i);
			}
		}
	}

	private void ajoutElement(String code,int i) {
		System.out.println("Entrer dans DataValuesImport - ajoutElement");
		
		//System.out.println("code Element: "+code+" // colonne: "+i);
		
		Element element = new Element();
		element = ielement.getOneElmentByCode(code);
		
		if(element != null) {
			//System.out.println("ok");
			//System.out.println("Element: "+element.getName()+" // trouvée");
			listElement.add(element);
			listIndex.add(i);
		}else {
			System.err.println("erreur");
			//System.out.println("Element: "+element.getName()+" // non trouver");
			noElement.add(code);
			resultatRequete.getRaisonAutreEchec().add("code Element non trouvé: "+code);
		}
	}
	
	
	private void insertData() {
		
		if(!myEntries.isEmpty()) {
			String[] data = myEntries.get(0);
			myEntries.remove(0);
			searchMeta(data);
			insertData();
		}else {
			resultatRequete.setStatus("succes");
		}
		
	}


	private void searchMeta(String[] data) {
		System.out.println("Entrer dans DataValuesImport - searchMeta");
		
		Instance instance = createInstance(data);
		Beneficiaire beneficiaire = new Beneficiaire();
				
		beneficiaire = createBeneficiaire(data,instance.getOrganisation().getUid());
		if(beneficiaire == null) {
			iinstance.deleteInstance(instance);
			resultatRequete.getRaisonNonImport().add("information beneficiaire absent");
			resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
			return;
		}
		
		createBeneficiareInstance(instance,beneficiaire,data);
		//ajouterValeur(instance,beneficiaire,data);
		
	}
	
	public Instance createInstance(String[] data) {
		InstanceTDO instanceTDO = new InstanceTDO();
		instanceTDO.setProgramme(progId);		
		if(!data[codeSafespace].isEmpty() && data[codeSafespace] != null && !data[codeCentreSociale].isEmpty() && data[codeCentreSociale] != null) {
			Organisation organisation = getOrganisationId(data[codeCentreSociale],data[codeSafespace]);
			if(organisation != null) {
				instanceTDO.setOrganisation(organisation.getUid());
			}else {
				resultatRequete.getRaisonNonImport().add("organisationcode ou organisationid non renseigné");
				resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
				return null;
			}
		}else {
			resultatRequete.getRaisonNonImport().add("organisationcode ou organisationid non renseigné");
			resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
			return null;
		}
		
		if(!data[userid].isEmpty() && data[userid] != null) {
			instanceTDO.setUser(data[userid]);
		}
		if(!data[dat_enrol].isEmpty() && data[dat_enrol] != null) {
			instanceTDO.setDateActivite(data[dat_enrol]);
		}else {
			resultatRequete.getRaisonNonImport().add("dat_enrol non renseigné");
			resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
			return null;
		}
		
		return iinstance.saveInstance(instanceTDO);
		
	}
	
	public Organisation getOrganisationId(String codeCentreSocial,String codeSafeSpace) {
		Organisation organisation = iorganisation.getOneOrganisationByCode(codeCentreSocial);
		if(organisation == null) {
			return null;
		}
		if(codeSafeSpace.length() == 1) {
			codeSafeSpace = "0"+codeSafeSpace;
		}
		
		for(int i = 0;i<organisation.getChildrens().size();i++) {
			if(organisation.getChildrens().get(i).getCode().equals(codeSafeSpace)) {
				return organisation.getChildrens().get(i);
			}
		}
		return null;
	}
	
	public Beneficiaire createBeneficiaire(String[] data,String organisation) {
		BeneficiaireTDO beneficiaireTDO = new BeneficiaireTDO();
				
		if(!data[nom].isEmpty() && data[nom] != null) {
			beneficiaireTDO.setName(data[nom]);
		}else {
			resultatRequete.getRaisonNonImport().add("nom beneficiaire non renseingé");
			resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
			return null;
		}
		if(!data[pren].isEmpty() && data[pren] != null) {
			beneficiaireTDO.setFirstName(data[pren]);
		}else {
			resultatRequete.getRaisonNonImport().add("pren beneficiaire non renseingé");
			resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
			return null;
		}
		if(!data[id_dreams].isEmpty() && data[id_dreams] != null) {
			beneficiaireTDO.setId_dreams(data[id_dreams]);
		}else {
			resultatRequete.getRaisonNonImport().add("id_dreams beneficiaire non renseingé");
			resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
			return null;
		}
		if(!data[tel].isEmpty() && data[tel] != null) {
			beneficiaireTDO.setTelephone(data[tel]);
		}
		
		if(!data[dat_nais].isEmpty() && data[dat_nais] != null) {
			beneficiaireTDO.setDateNaissance(data[dat_nais]);
		}else {
			resultatRequete.getRaisonNonImport().add("dat_nais beneficiaire non renseingé");
			resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
			return null;
		}
		
		if(!data[dat_enrol].isEmpty() && data[dat_enrol] != null) {
			beneficiaireTDO.setDateEnrolement(data[dat_enrol]);
		}else {
			resultatRequete.getRaisonNonImport().add("dat_enrol beneficiaire non renseingé");
			resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
			return null;
		}
		
		if(!data[age_enrol].isEmpty() && data[age_enrol] != null) {
			beneficiaireTDO.setAgeEnrolement(data[age_enrol]);
		}else {
			resultatRequete.getRaisonNonImport().add("age_enrol beneficiaire non renseingé");
			resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
			return null;
		}
		
		if(organisation != null) {
			beneficiaireTDO.setOrganisation(new UidEntitie(organisation));
		}
		
		return ibeneficiaire.convertBeneficiaireTDO(beneficiaireTDO);
	}
	
	public Programme seachProgramme(String prog) {
		System.out.println("Entrer dans DataValuesImport - seachProgramme");
		
		Programme programme = new Programme();
		if(!prog.isEmpty() && prog != null) {
			programme = iprogramme.getOneProgramme(prog);
			if(programme == null) {
				//igoree++;
				//System.out.println("l225 Progrmme non trouver: "+prog);
				return null;
			}
		}else {
			//igoree++;
			//System.out.println("Programme non trouver-----");
			return null;
		}
		return programme;
	}

	public Instance seachInstance(String inst) {
		System.out.println("Entrer dans DataValuesImport - seachInstance");
		
		Instance instance = new Instance();
		instance = iinstance.getOneInstance(inst);
		if(instance == null) {
			//igoree++;
			//System.err.println("Instance non trouver: "+inst);
			return null;
		}
		return instance;
	}
	
	public Organisation seachOrganisation(String id,String code) {
		System.out.println("Entrer dans DataValuesImport - seachOrganisation");
		
		Organisation organisation = new Organisation();
		if(!id.isEmpty() && id != null) {
			organisation = iorganisation.getOneOrganisationById(id);
			if(organisation == null) {
				//igoree++;
				//System.err.println("Id Organisation non trouver: "+id);
				return null;
			}
		}else if(!code.isEmpty() && code != null) {
			organisation = iorganisation.getOneOrganisationByCode(code);
			if(organisation == null) {
				//igoree++;
				//System.err.println("Code Organisation non trouver: "+code);
				return null;
			}
		}else {
			//igoree++;
			//System.err.println("Organisation non trouver");
			return null;
		}
		
		
		return organisation;
	}
	
	public UserApp seachUser(String user) {
		System.out.println("Entrer dans DataValuesImport - seachUser");
		
		UserApp user1 = new UserApp();
		user1 = iuser.getUser(user);
		if(user1 == null) {
			System.err.println("User Organisation non trouver: "+user);
			return null;
		}
		
		return user1;
	}
	
	private void createBeneficiareInstance(Instance instance,Beneficiaire beneficiaire,String[] data) {
		InstanceBeneficiaire instanceBeneficiaire = new InstanceBeneficiaire();
		Date dateActivite;
		
		if(!data[dat_enrol].isEmpty() && data[dat_enrol] != null) {
			
				dateActivite = convertDate.getDateParse(data[dat_enrol]);
				if(dateActivite == null) {
					resultatRequete.getRaisonNonImport().add("dat_enrol pas en format Date, dat_enrol"+data[dat_enrol]);
					resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
					return;
				}
			
		}else {
			resultatRequete.getRaisonNonImport().add("dat_enrol non renseigné");
			resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
			return;
		}
		instanceBeneficiaire.setInstance(instance);
		instanceBeneficiaire.setBeneficiaire(beneficiaire);
		instanceBeneficiaire.setDateAction(dateActivite);
		instanceBeneficiaire.setOrdre(1);
		
		beneficiaire.getInstanceBeneficiaires().add(instanceBeneficiaire);
		beneficiaire = ibeneficiaire.saveOneBeneficiaire(beneficiaire);
		if(beneficiaire != null) {
			ajouterValeur(instance,beneficiaire,data);
		}else {
			iinstance.deleteInstance(instance);
			resultatRequete.getRaisonNonImport().add("Echec de creation de la beneficiare id_dreams pourrait déjà existé, id_dreams: "+data[id_dreams]);
			resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
		}
			
	}
	
	private void ajouterValeur(Instance instance,Beneficiaire beneficiaire,String[] data) {
		System.out.println("Entrer dans DataValuesImport - ajouterValeur");
		
		List<DataValue> listDataValue = new ArrayList<DataValue>();
		
		for(int i =0; i<listElement.size();i++) {
			
			if(!data[listIndex.get(i)].isEmpty() && data[listIndex.get(i)] != null) {
				//System.out.println("Element :"+listElement.get(i).getName()+" // Element id:"+listElement.get(i).getUid());
				DataValue dataValue = new DataValue();
				dataValue.setInstance(instance);
				dataValue.setUser(instance.getUser());
				dataValue.setElement(listElement.get(i));
				dataValue.setValue(data[listIndex.get(i)]);
				dataValue.setNumero(1);
				dataValue.setDateCreation(new Date());
				dataValue.setDateUpdate(new Date());
				listDataValue.add(dataValue);
				
			}
		}
		
		if(!listDataValue.isEmpty()) {
			listDataValue = idataValues.saveAllDataValue(listDataValue);
			resultatRequete.setImporte(resultatRequete.getImporte() + 1);
			Instance serviceInstance = servicesDreams.evaluerService(instance,data[dat_enrol]);
			serviceBeneficiaire(serviceInstance,beneficiaire);
		}
	}
	
	private void serviceBeneficiaire(Instance instance,Beneficiaire beneficiaire) {
		InstanceBeneficiaire instanceBeneficiaire = new InstanceBeneficiaire();
		instanceBeneficiaire.setInstance(instance);
		instanceBeneficiaire.setBeneficiaire(beneficiaire);
		instanceBeneficiaire.setDateAction(instance.getDateActivite());
		instanceBeneficiaire.setOrdre(1);
		
		beneficiaire.getInstanceBeneficiaires().add(instanceBeneficiaire);
		beneficiaire = ibeneficiaire.updateOneBeneficiaire(beneficiaire);
	}
	
}























