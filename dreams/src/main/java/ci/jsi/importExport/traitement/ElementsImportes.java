package ci.jsi.importExport.traitement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

import ci.jsi.entites.element.Element;
import ci.jsi.entites.element.Ielement;
import ci.jsi.entites.ensembleOption.EnsembleOption;
import ci.jsi.entites.ensembleOption.IensembleOption;
import ci.jsi.entites.option.Ioption;
import ci.jsi.entites.option.Option;
import ci.jsi.entites.programme.Iprogramme;
import ci.jsi.entites.programme.Programme;
import ci.jsi.entites.programme.ProgrammeElement;
import ci.jsi.initialisation.ResultatRequete;
import ci.jsi.initialisation.Uid;

@Service
public class ElementsImportes {

	@Autowired
	Ielement ielement;
	@Autowired
	IensembleOption iensembleOption;
	@Autowired
	Ioption ioption;
	@Autowired
	Iprogramme iprogramme; 
	@Autowired
	Uid uid;

	//Element element;
	

	private String contitEntete[] = {"elementname","elementuid","elementcode","elementdescription","elementtypeValeur","programmeuid","ensembleOptionname","ensembleOptionuid","ensembleOptiontypeValeur","ensembleOptioncode","ensembleOptionmultiple","optionname","optionuid","optioncode"};
	private List<String[]> lesElements;
	private List<String[]> myEntries;
	
	ResultatRequete resultatRequete;

	@SuppressWarnings("deprecation")
	public ResultatRequete lireCSV(String fileName) {
		resultatRequete = new ResultatRequete();
		String chemin = File.separator+"DREAMS"+File.separator+"upload-dir"+File.separator;
		String file = chemin+fileName;

		CSVReader csvReader = null;

		try {
			csvReader = new CSVReader(new FileReader(file), ';');
			// String[] ligne;

			// List<String[]> myEntries = csvReader.readAll();
			myEntries = csvReader.readAll();
			if (controleEntete(myEntries.get(0))) {
				listeElement();
				resultatRequete.setStatus("succes");
			}
			else{
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

	public boolean controleEntete(String[] ligne) {
		System.err.println("//// Entrer dans controleEntete");
		for (int i = 0; i < ligne.length; i++) {
			System.out.println(ligne[i]+" = "+contitEntete[i]);
			if (!ligne[i].equals(contitEntete[i])) {
				System.err.println(ligne[i].length()+" = "+ligne[i]);
				System.err.println(contitEntete[i].length()+" = "+contitEntete[i]);
				System.err.println(ligne[i]+" = "+contitEntete[i]);
				return false;
			}
				
		}
		myEntries.remove(0);
		return true;
	}

	public void listeElement() {
		System.out.println("////// entrer dans listeElement");
		lesElements = new ArrayList<String[]>();
		if(!myEntries.isEmpty()) {
			String[] ligne = myEntries.get(0);
			myEntries.remove(0);
			lesElements.add(ligne);
			if (ligne[0] != null && !ligne[0].isEmpty()) {
				gestionLigne(ligne[0]);
				gestionElement();
			} else {
				resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
				resultatRequete.getRaisonNonImport().add("Ligne sans nom");
			}           
			listeElement();
		}
		
	}

	public void gestionLigne(String elementName) {
		System.out.println("////// entrer dans gestionLigne");
		int i = 0; 
		while (i<myEntries.size()) {
			if (myEntries.get(i)[0].equals(elementName)) {
				lesElements.add(myEntries.get(i));
				myEntries.remove(i);
				i--;
			}
			i++;
		}
	}

	private void gestionElement() {
		System.out.println("////// entrer dans gestionElement");
		Element elementName = null;
		Element elementCode = null;
		elementName = ielement.getOneElmentByName(lesElements.get(0)[0]);
		if (lesElements.get(0)[2] != null && !lesElements.get(0)[2].isEmpty()) {
			elementCode = ielement.getOneElmentByCode(lesElements.get(0)[2]);
		}
		if (elementName == null && elementCode == null) {
			if (ensembleOptionUnique()) {
				EnsembleOption ensembleOption = createEnsembleOption();
				if(ensembleOption != null) {
					if(ensembleOption.getUid() != null) {
						createElement(ensembleOption);
					}else {
						createElement();
					}
					resultatRequete.setImporte(resultatRequete.getImporte() + 1);
				}else{
					resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
					resultatRequete.getRaisonNonImport().add("Enembleoption exite dans la base, ensembleName:"+ lesElements.get(0)[6]);
				}
			}else{
				resultatRequete.setIgnore(resultatRequete.getIgnore() + 1);
				resultatRequete.getRaisonNonImport().add("Enembleoption en double dans le fichier, ensembleName:"+ lesElements.get(0)[6]);
			}
				
		}else {
			//System.err.println("element = "+lesElements.get(0)[0]+" existe dans la base");
			resultatRequete.getRaisonNonImport().add("Element exite dans la base, element:"+lesElements.get(0)[0]);

			if (elementName == elementCode) {
				addProgramme(elementName);
			}
			
			
		}
			
	}

	private void addProgramme(Element element) {
		System.out.println("////// entrer dans addProgramme");
		if(lesElements.get(0)[5] != null && !lesElements.get(0)[5].isEmpty()) {
			//System.out.println("Programme = "+lesElements.get(0)[5]);
			ProgrammeElement programmeElement = new ProgrammeElement();
			Programme programme = iprogramme.getOneProgramme(lesElements.get(0)[5]);
			if(programme != null) {
				boolean trouve = false;
				for(int i = 0;i<programme.getProgrammeElements().size();i++) {
					if(programme.getProgrammeElements().get(i).getElement().getName().equals(element.getName())) {
						trouve = true;
						break;
					}
				}
				if(!trouve) {
					programmeElement.setElement(element);
					programmeElement.setProgramme(programme);
					programme.getProgrammeElements().add(programmeElement);
					programme = iprogramme.updateOneProgramme(programme);
					resultatRequete.setUpdate(resultatRequete.getUpdate() + 1);
				}
				
			}
		}
		
		
	}

	private void createElement(EnsembleOption ensembleOption) {
		//System.out.println("////// entrer dans createElement ensembleOption");
		Element element = new Element();
		element = createElement();
		//System.out.println("-2");
		element.setEnsembleOption(ensembleOption);
		//System.out.println("-3");
		ielement.saveOneElment(element);
	}
	
	private Element createElement() {
		//System.out.println("////// entrer dans createElement simple");
		Element element = new Element();
		element.setName(lesElements.get(0)[0]);
		//System.out.println("+1 createElement simple");
		element.setUid(uid.getUid());
		//System.out.println("+2 createElement simple");
		element.setCode(lesElements.get(0)[2]);
		//System.out.println("+3 createElement simple");
		element.setDescription(lesElements.get(0)[3]);
		//System.out.println("+4 createElement simple");
		if(lesElements.get(0)[4] != null && !lesElements.get(0)[4].isEmpty()) {
			//System.out.println("+5 createElement simple");
			element.setTypeValeur(lesElements.get(0)[4]);
		}else {
			//System.out.println("+6");
			element.setTypeValeur("text");
		}
			
		
		if(lesElements.get(0)[5] != null && !lesElements.get(0)[5].isEmpty()) {
			//System.out.println("+7 createElement simple");
			ProgrammeElement programmeElement = leProgramme(lesElements.get(0)[5]);
			//System.out.println("+8 createElement simple");
			if(programmeElement != null) {
				//System.out.println("+9 createElement simple");
				programmeElement.setElement(element);
				element.getProgrammeElements().add(programmeElement);
				//System.out.println("+10 createElement simple");
			}
		}
		//System.out.println("+11 saveOneElment(element);");
		element = ielement.saveOneElment(element);
		//System.out.println("+12 saveOneElment(element)");
		return element;
		
	}
	
	private ProgrammeElement leProgramme(String id) {
		System.out.println("////// entrer dans leProgramme");
		Programme programme = iprogramme.getOneProgramme(id);
		//System.out.println("*1 leProgramme");
		ProgrammeElement programmeElement = new ProgrammeElement();
		//System.out.println("*2 leProgramme");
		if(programme != null) {
			//System.out.println("*3 leProgramme");
			programmeElement.setProgramme(programme);
			//System.out.println("*4 leProgramme");
			return programmeElement;
		}
		//System.out.println("*5 leProgramme");
		return null;
	}
	
	
	

	private boolean ensembleOptionUnique() {
		//System.out.println("////// entrer dans ensembleOptionUnique");
		
		String ensembleName = lesElements.get(0)[6];
		String ensembleid = lesElements.get(0)[7];
		for (int i = 0; i < lesElements.size(); i++) {
			if (!lesElements.get(i)[6].equals(ensembleName))
				return false;
			if (!lesElements.get(i)[7].equals(ensembleid))
				return false;
		}
		return true;
	}

	private EnsembleOption createEnsembleOption() {
		System.out.println("////// entrer dans createEnsembleOption");
		
		EnsembleOption ensemble = new EnsembleOption();
		EnsembleOption ensembleOptionName = null;
		EnsembleOption ensembleOptionId = null;
		
		
		if (lesElements.get(0)[6] != null && !lesElements.get(0)[6].isEmpty()) {
			ensembleOptionName = iensembleOption.getOneEnsembleOptionByName(lesElements.get(0)[6]);
		}
		if (lesElements.get(0)[7] != null && !lesElements.get(0)[7].isEmpty()) {
			ensembleOptionId = iensembleOption.getOneEnsembleOption(lesElements.get(0)[7]);
		}

		if (ensembleOptionName != null && ensembleOptionId != null) {
			if (ensembleOptionName == ensembleOptionId)
				return ensembleOptionId;
			else
				return null;
		} else if (ensembleOptionName != null || ensembleOptionId != null) {
			if (ensembleOptionName != null)
				return ensembleOptionName;
			if (ensembleOptionId != null)
				return ensembleOptionId;
		} else {
			if (lesElements.get(0)[6] != null && !lesElements.get(0)[6].isEmpty()) {
				
				ensemble = new EnsembleOption();
				ensemble.setName(lesElements.get(0)[6]);
				ensemble.setUid(uid.getUid());
				if (lesElements.get(0)[8] != null && !lesElements.get(0)[8].isEmpty())
					ensemble.setTypeValeur(lesElements.get(0)[8]);
				else
					ensemble.setTypeValeur("text");
				if (lesElements.get(0)[9] != null && !lesElements.get(0)[9].isEmpty())
					ensemble.setCode(lesElements.get(0)[9]);
				if (lesElements.get(0)[10] != null && !lesElements.get(0)[10].isEmpty()) {
					Boolean isboolean = Boolean.valueOf(lesElements.get(0)[10]);
					ensemble.setMultiple(isboolean);
				}
				
				ensemble = iensembleOption.saveEnsembleOption(ensemble);
				createOption(ensemble);
				
			}
		}
		
		return ensemble;
	}

	private void createOption(EnsembleOption ensemble) {
		System.out.println("////// entrer dans createOption");
		for(int i=0;i<lesElements.size();i++) {
			Option option = new Option();
			if (lesElements.get(i)[11] != null && !lesElements.get(i)[11].isEmpty()) {
				option.setName(lesElements.get(i)[11]);
				if(lesElements.get(i)[13] != null && !lesElements.get(i)[13].isEmpty()) {
					option.setCode(lesElements.get(i)[13]);
					option.setUid(uid.getUid());
					option.setEnsembleOption(ensemble);
					option = ioption.saveOption(option);
					
				}	
			}
			
		}
		
	}

}
