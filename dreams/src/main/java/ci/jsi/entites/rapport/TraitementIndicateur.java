package ci.jsi.entites.rapport;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.beneficiaire.Beneficiaire;
import ci.jsi.entites.beneficiaire.BeneficiaireTDO;
import ci.jsi.entites.beneficiaire.Ibeneficiaire;
import ci.jsi.entites.dataValue.DataInstance;
import ci.jsi.entites.dataValue.IdataValues;
import ci.jsi.entites.element.Element;
import ci.jsi.entites.element.Ielement;
import ci.jsi.entites.instance.Instance;
import ci.jsi.entites.option.Ioption;
import ci.jsi.entites.option.Option;
import ci.jsi.entites.organisation.Iorganisation;
import ci.jsi.entites.organisation.OrganisationTDO;
import ci.jsi.entites.organisationLevel.IorganisationLevel;
import ci.jsi.entites.organisationLevel.OrganisationLevel;
import ci.jsi.entites.programme.Iprogramme;
import ci.jsi.entites.programme.ProgrammeTDO;
import ci.jsi.initialisation.ConvertDate;
import ci.jsi.initialisation.UidEntitie;

@Service
public class TraitementIndicateur {
	
	@Autowired
	IorganisationLevel iorganisationLevel;
	@Autowired
	Iorganisation iorganisation;
	@Autowired
	Iprogramme iprogramme;
	@Autowired
	IdataValues idataValues;
	@Autowired
	Ielement ielement;
	@Autowired
	Irapport irapport;
	@Autowired
	Ibeneficiaire ibeneficiaire;
	@Autowired
	Ioption ioption;
	@Autowired
	ConvertDate convertDate;
	
	String InterventionDossierBeneficiaire [] = {"conceptSexualite", "conceptsGenre", "connaissanceCorpsOrgane", "aspectsNegatifs", "promotionDepistage", "participationActivites", "participationCauseries", "ecouteConseils", "suivi", "referenceVersExperts", "businessPlus", "participationAVEC","appuiScolaire", "fournitures", "uniformes", "autre", "alphabetisation", "utilisationPreservatifs", "distributionPreservatifs", "referencePreservatifs", "referencVersPF", "referenceServices","referenceMedical","referencePsychoSocial","referenceJuridique","referenceAbri","appuiMedicaux","appuiJuridiques","referenceNutritionnel","appuiDocument","sinovoyu","AVEC","educationFinanciere"};
	List<Element> elementsIntervention = new ArrayList<Element>();
	String InterventionPopCible [] = {"conceptSexualite", "conceptsGenre", "connaissanceCorpsOrgane", "aspectsNegatifs", "promotionDepistage","utilisationPreservatifs", "distributionPreservatifs", "referencePreservatifs","referenceServices"};
	List<Element> elementsInterventionPopCible = new ArrayList<Element>();
	String laPeriode = null;
	String dateDebuts = "";
	String dateFins = null;
	List<OrganisationTDO> OrganisationTDOs;
	OrganisationTDO organisationSelect;
	ProgrammeTDO enrolement = null;
	ProgrammeTDO eligibilite = null;
	ProgrammeTDO dossierBeneficiare = null;
	ProgrammeTDO besoinBeneficiare = null;
	ProgrammeTDO vad = null;
	ProgrammeTDO reference = null;
	ProgrammeTDO groupe = null;
	
	int nouveau = 0;
	int ancien = 0;
	int total = 0;
	int nouveau_10_14 = 0;
	int nouveau_15_19 = 0;
	int ancien_10_14 = 0;
	int ancien_15_19 = 0;
	
	List<DataInstance> dataInstances = new ArrayList<DataInstance>();
	List<Instance> instances = new ArrayList<Instance>();
	
	
	
	public void genereRapport() {
		chargeProgramme();
		executionPeriodeMois();
		executionPeriodeTrimestre();
	}
	
	
	private void chargeProgramme() {
		List<ProgrammeTDO> programmeTDOs = new ArrayList<ProgrammeTDO>();
		programmeTDOs = iprogramme.getAllProgrammeTDO();
		for(int i = 0;i<programmeTDOs.size();i++) {
			if(programmeTDOs.get(i).getCode().equals("enrolement")) {
				enrolement = programmeTDOs.get(i);
			}
			if(programmeTDOs.get(i).getCode().equals("eligibilite")) {
				eligibilite = programmeTDOs.get(i);
			}
			if(programmeTDOs.get(i).getCode().equals("dossierBeneficiare")) {
				dossierBeneficiare = programmeTDOs.get(i);
			}
			if(programmeTDOs.get(i).getCode().equals("besoinBeneficiare")) {
				besoinBeneficiare = programmeTDOs.get(i);
			}
			if(programmeTDOs.get(i).getCode().equals("vad")) {
				vad = programmeTDOs.get(i);
			}
			if(programmeTDOs.get(i).getCode().equals("reference")) {
				reference = programmeTDOs.get(i);
			}
			if(programmeTDOs.get(i).getCode().equals("groupe")) {
				groupe = programmeTDOs.get(i);
			}
		}
		
		for(int j = 0;j<InterventionDossierBeneficiaire.length;j++) {
			elementsIntervention.add(ielement.getOneElmentByCode(InterventionDossierBeneficiaire[j]));
		}
		for(int j = 0;j<InterventionPopCible.length;j++) {
			elementsInterventionPopCible.add(ielement.getOneElmentByCode(InterventionPopCible[j]));
		}
		System.err.println("fin execution InterventionDossierBeneficiaire = ");
		
	}
		
	private void executionPeriodeTrimestre() {
		System.err.println("execution Periode Trimestre = ");
		
		LocalDate todaydate = LocalDate.now();
		int intTodaydate = todaydate.getMonthValue();;
		if(intTodaydate <= 3 && intTodaydate >= 1) {
			dateDebuts = todaydate.getYear()+"-01-01";
			dateFins = todaydate.getYear() +"-03-31";
		}else if(intTodaydate >= 4 && intTodaydate <= 6) {
			dateDebuts = todaydate.getYear()+"-04-01";
			dateFins = todaydate.getYear() +"-06-30";
		}else if(intTodaydate >= 7 && intTodaydate <= 9) {
			dateDebuts = todaydate.getYear()+"-07-01";
			dateFins = todaydate.getYear() +"-09-30";
		}else if(intTodaydate >= 10 && intTodaydate <= 12) {
			dateDebuts = todaydate.getYear()+"-10-01";
			dateFins = todaydate.getYear() +"-12-31";
		}
		int nombre = 0;
		String commence = dateDebuts;
		while(!dateDebuts.equals("2018-01-01")) {
			chargeTrimestre(nombre,commence);
			nombre += 3;
			
		}
	}
	
	private void chargeTrimestre(int nbreMois,String commence) {
		
		LocalDate todaydate = LocalDate.parse(commence, DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate newDaydate = todaydate.minusMonths(nbreMois);
		LocalDate lastDaydate = newDaydate.plusMonths(2);
		
		dateDebuts = newDaydate.with(TemporalAdjusters.firstDayOfMonth()).toString();
		dateFins = lastDaydate.with(TemporalAdjusters.lastDayOfMonth()).toString();
		
		laPeriode = newDaydate.getYear() +"T"+ newDaydate.get(IsoFields.QUARTER_OF_YEAR);
		System.out.println("dateDebuts = "+dateDebuts);
		System.out.println("dateFins = "+dateFins);
		System.out.println("laPeriode = "+laPeriode);
		System.out.println();
		gestionOrganisation();
	}
	
	private void executionPeriodeMois() {
		System.err.println("execution Periode Mois = ");
		int nombre = 0;
		while(!dateDebuts.equals("2018-01-01")) {
			chargeMois(nombre);
			nombre++;
			
		}
	}
	
	private void chargeMois(int nbreMois) {
		LocalDate newDaydate = null;
		LocalDate todaydate = LocalDate.now();
		newDaydate = todaydate.minusMonths(nbreMois);
		dateDebuts = newDaydate.with(TemporalAdjusters.firstDayOfMonth()).toString();
		dateFins = newDaydate.with(TemporalAdjusters.lastDayOfMonth()).toString();
		String ladate = newDaydate.toString();
		laPeriode = ladate.substring(0, 4) + ladate.substring(5, 7);
		
		System.out.println("dateDebuts = "+dateDebuts);
		System.out.println("dateFins = "+dateFins);
		System.out.println("laPeriode = "+laPeriode);
		System.out.println();
		gestionOrganisation();
	}
	
	
	private void gestionOrganisation() {
		List<OrganisationLevel> organisationLevels = new ArrayList<OrganisationLevel>();
		OrganisationTDOs = new ArrayList<OrganisationTDO>();
		List<OrganisationTDO> organisations = new ArrayList<OrganisationTDO>();
		organisationLevels = iorganisationLevel.getAllOrganisationLevel();
		int niveau = organisationLevels.get(0).getLevel();
		OrganisationTDOs = iorganisation.getAllOrganisationTDO();
		organisations = getOrganisatioLevelOne(OrganisationTDOs,niveau);
		selectOrganisation(organisations);
	}
	
	private List<OrganisationTDO> getOrganisatioLevelOne(List<OrganisationTDO> organisationTDOs,int niveau){
		List<OrganisationTDO> organisationTDOs2 = new ArrayList<OrganisationTDO>();
		for(int i =0;i<organisationTDOs.size();i++) {
			if(niveau == organisationTDOs.get(i).getLevel()) {
				organisationTDOs2.add(organisationTDOs.get(i));
			}
		}
		return organisationTDOs2;
	}
	
	private void selectOrganisation(List<OrganisationTDO> ListOrganisations) {
		
		for(int i = 0;i<ListOrganisations.size();i++) {
			if(!ListOrganisations.get(i).getChildrens().isEmpty()) {
				List<OrganisationTDO> organisations = new ArrayList<OrganisationTDO>();
				organisations = getOrganisationTDOchildren(ListOrganisations.get(i).getChildrens());
				selectOrganisation(organisations);
			}
			organisationSelect = ListOrganisations.get(i);
			chargeIndicateur();
		}
	}
 
	private List<OrganisationTDO> getOrganisationTDOchildren(List<UidEntitie> childrens) {
		List<OrganisationTDO> organisations = new ArrayList<OrganisationTDO>();
		for(int a = 0;a<childrens.size();a++) {
			for(int i =0;i<this.OrganisationTDOs.size();i++) {
				if(this.OrganisationTDOs.get(i).getId().equals(childrens.get(a).getId())) {
					organisations.add(this.OrganisationTDOs.get(i));
					break;
				}
			}
		}
		
		return organisations;
	}
	
	private void enregistreRapport(String element) {
		RapportTDO rapportTDO = new RapportTDO();
		String combinaison = null;
		rapportTDO.setOrganisation(this.organisationSelect.getId());
		rapportTDO.setPeriode(laPeriode);
		/*nouveau = 0; 
		ancien = 0;
		total = 0;
		nouveau_10_14 = 0;
		nouveau_15_19 = 0;
		ancien_10_14 = 0;
		ancien_15_19 = 0;*/
		
		if(!this.organisationSelect.getChildrens().isEmpty()) {
			for(int comp = 0;comp < this.organisationSelect.getChildrens().size();comp++) {
				List<RapportTDO> rapportTDOs = new ArrayList<RapportTDO>();
				rapportTDOs = irapport.getRapportOptionRapportTDOCodeNull(this.organisationSelect.getChildrens().get(comp).getId(), element, laPeriode);
				if(!rapportTDOs.isEmpty()) {
					for(int rap = 0; rap < rapportTDOs.size();rap++) {
						combinaison = element + ".nouveau_10_14";
						if(rapportTDOs.get(rap).getElement().equals(combinaison)) {
							nouveau_10_14 += rapportTDOs.get(rap).getValeurs();
						}
						combinaison = element + ".nouveau_15_19";
						if(rapportTDOs.get(rap).getElement().equals(combinaison)) {
							nouveau_15_19 += rapportTDOs.get(rap).getValeurs();
						}
						combinaison = element + ".ancien_10_14";
						if(rapportTDOs.get(rap).getElement().equals(combinaison)) {
							ancien_10_14 += rapportTDOs.get(rap).getValeurs();
						}
						combinaison = element + ".ancien_15_19";
						if(rapportTDOs.get(rap).getElement().equals(combinaison)) {
							ancien_15_19 += rapportTDOs.get(rap).getValeurs();
						}
						combinaison = element;
						if(rapportTDOs.get(rap).getElement().equals(combinaison)) {
							total += rapportTDOs.get(rap).getValeurs();
						}
					}
				}
			}
			
		}
		
		//element - Nouveau, 10-14 ans
		combinaison = element + ".nouveau_10_14";
		rapportTDO.setElement(combinaison);
		rapportTDO.setValeurs(nouveau_10_14);
		irapport.saveRapportTDO(rapportTDO);
		
		//element - Nouveau, 15-19 ans
		combinaison = element + ".nouveau_15_19";
		rapportTDO.setElement(combinaison);
		rapportTDO.setValeurs(nouveau_15_19);
		irapport.saveRapportTDO(rapportTDO);
		
		//element - Ancien, 10-14 ans
		combinaison = element + ".ancien_10_14";
		rapportTDO.setElement(combinaison);
		rapportTDO.setValeurs(ancien_10_14);
		irapport.saveRapportTDO(rapportTDO);
		
		//element - Ancien, 15-19 ans
		combinaison = element + ".ancien_15_19";
		rapportTDO.setElement(combinaison);
		rapportTDO.setValeurs(ancien_15_19);
		irapport.saveRapportTDO(rapportTDO);
		
		//element (total)
		combinaison = element;
		rapportTDO.setElement(combinaison);
		rapportTDO.setValeurs(total);
		irapport.saveRapportTDO(rapportTDO);
		
		
	}
	
	
	private void chargeIndicateur() {
		//nbreBenefEnrole();
		//nbreBenefEnroleScolaireExtraScolaire();
		//nbreBenefEnroleScolaireActifs();
		//nbreFilleIssusPopulation();
		//nbreFilleReferePsychoSocial();
		//nbreBeneficiaireCommunicationParentEnfant();
		//nbreParentCommunicationParentEnfant();
		//nbreFilleVAD();
		//nbreFilleBesoinEducation();
	}
	
	private void nbreBenefEnrole() {
		List<String> organisation = new ArrayList<String>();
		nouveau = 0;
		ancien = 0;
		total = 0;
		nouveau_10_14 = 0;
		nouveau_15_19 = 0;
		ancien_10_14 = 0;
		ancien_15_19 = 0;
		organisation.add(this.organisationSelect.getId());
		Element element = ielement.getOneElmentByCode("age_enrol");
		//nbreBenefEnrole NOUVEAU 
		dataInstances = idataValues.dataAnalysePeriode(organisation, enrolement.getId(), dateDebuts, dateFins);
		nouveau = dataInstances.size();
		
		for(int i = 0;i<dataInstances.size();i++) {
			for(int b =0;b<dataInstances.get(i).getDataValue().size();b++) {
				if(dataInstances.get(i).getDataValue().get(b).getElement().equals(element.getUid())) {
					String age_enrol = dataInstances.get(i).getDataValue().get(b).getValue();
					int age = Integer.parseInt(age_enrol);
					if(age < 15) {
						nouveau_10_14++;
					}else {
						nouveau_15_19++;
					}
					break;
				}
			}
		}
		
		//nbreBenefEnrole ANCIEN 
		dataInstances = idataValues.dataAnalysePreview(organisation, enrolement.getId(), dateDebuts);
		ancien = dataInstances.size();
		for(int i = 0;i<dataInstances.size();i++) {
			for(int b =0;b<dataInstances.get(i).getDataValue().size();b++) {
				if(dataInstances.get(i).getDataValue().get(b).getElement().equals(element.getUid())) {
					String age_enrol = dataInstances.get(i).getDataValue().get(b).getValue();
					int age = Integer.parseInt(age_enrol);
					if(age < 15) {
						ancien_10_14++;
					}else {
						ancien_15_19++;
					}
					break;
				}
			}
		}
		
		total = nouveau + ancien;
		enregistreRapport("nbreBenefEnrole");
		
	}

	private void nbreBenefEnroleScolaireExtraScolaire() {
		List<String> organisation = new ArrayList<String>();
		List<BeneficiaireTDO> beneficiaireTDOs = new ArrayList<BeneficiaireTDO>();
		
		organisation.add(this.organisationSelect.getId());
		Element element = ielement.getOneElmentByCode("porteEntree");
		Element ageEnrolElement = ielement.getOneElmentByCode("age_enrol");
		nouveau = 0;
		ancien = 0;
		total = 0;
		nouveau_10_14 = 0;
		nouveau_15_19 = 0;
		ancien_10_14 = 0;
		ancien_15_19 = 0;
		
		int nouveauExtra = 0;
		int ancienExtra = 0;
		int totalExtra = 0;
		int nouveau_10_14Extra = 0;
		int nouveau_15_19Extra = 0;
		int ancien_10_14Extra = 0;
		int ancien_15_19Extra = 0;
		
		beneficiaireTDOs = ibeneficiaire.getBeneficiairePeriode(organisation, dateDebuts, dateFins);
		
		
		
		//nbreBenefEnrole NOUVEAU 
		dataInstances = idataValues.dataAnalysePeriode(organisation, dossierBeneficiare.getId(), dateDebuts, dateFins);
		nouveau = dataInstances.size();
		
		for(int i = 0;i<dataInstances.size();i++) {
			for(int b =0;b<dataInstances.get(i).getDataValue().size();b++) {
				if(dataInstances.get(i).getDataValue().get(b).getElement().equals(element.getUid())) {
					String porteEntree = dataInstances.get(i).getDataValue().get(b).getValue();
					if(porteEntree.equals("ecole")) {
						for(int inter =0;inter<dataInstances.get(i).getDataValue().size();inter++) {
							
						}
						for(int ag =0;ag<dataInstances.get(i).getDataValue().size();ag++) {
							if(dataInstances.get(i).getDataValue().get(ag).getElement().equals(ageEnrolElement.getUid())) {
								int age = Integer.parseInt(dataInstances.get(i).getDataValue().get(ag).getValue());
								if(age < 15) {
									nouveau_10_14++;
								}else {
									nouveau_15_19++;
								}
								break;
							}
						}
					}else {
						for(int ag =0;ag<dataInstances.get(i).getDataValue().size();ag++) {
							if(dataInstances.get(i).getDataValue().get(ag).getElement().equals(ageEnrolElement.getUid())) {
								int age = Integer.parseInt(dataInstances.get(i).getDataValue().get(ag).getValue());
								if(age < 15) {
									nouveau_10_14Extra++;
								}else {
									nouveau_15_19Extra++;
								}
								break;
							}
						}
					}
					break;
				}
			}
		}
		
		
		//nbreBenefEnrole ANCIEN 
		dataInstances = idataValues.dataAnalysePreview(organisation, dossierBeneficiare.getId(), dateDebuts);
		ancien = dataInstances.size();
		for(int i = 0;i<dataInstances.size();i++) {
			for(int b =0;b<dataInstances.get(i).getDataValue().size();b++) {
				if(dataInstances.get(i).getDataValue().get(b).getElement().equals(element.getUid())) {
					String porteEntree = dataInstances.get(i).getDataValue().get(b).getValue();
					if(porteEntree.equals("ecole")) {
						for(int ag =0;ag<dataInstances.get(i).getDataValue().size();ag++) {
							if(dataInstances.get(i).getDataValue().get(ag).getElement().equals(ageEnrolElement.getUid())) {
								int age = Integer.parseInt(dataInstances.get(i).getDataValue().get(ag).getValue());
								if(age < 15) {
									ancien_10_14++;
								}else {
									ancien_15_19++;
								}
								break;
							}
						}
					}else {
						for(int ag =0;ag<dataInstances.get(i).getDataValue().size();ag++) {
							if(dataInstances.get(i).getDataValue().get(ag).getElement().equals(ageEnrolElement.getUid())) {
								int age = Integer.parseInt(dataInstances.get(i).getDataValue().get(ag).getValue());
								if(age < 15) {
									ancien_10_14Extra++;
								}else {
									ancien_15_19Extra++;
								}
								break;
							}
						}
					}
					break;
				}
			}
		}
		total = nouveau + ancien;
		enregistreRapport("nbreBenefEnroleScolaire");
		//nouveau = nouveauExtra;
		//ancien = ancienExtra;
		//total = totalExtra;
		nouveau_10_14 = nouveau_10_14Extra;
		nouveau_15_19 = nouveau_15_19Extra;
		ancien_10_14 = ancien_10_14Extra;
		ancien_15_19 = ancien_15_19Extra;
		enregistreRapport("nbreBenefEnroleExtraScolaire");
		
		
	}

	private void nbreBenefEnroleScolaireExtraScolaireActifs() {
		List<String> organisation = new ArrayList<String>();
		//nouveau = 0;
		//ancien = 0;
		total = 0;
		nouveau_10_14 = 0;
		nouveau_15_19 = 0;
		ancien_10_14 = 0;
		ancien_15_19 = 0;
		
		//int nouveauExtra = 0;
		//int ancienExtra = 0;
		//int totalExtra = 0;
		int nouveau_10_14Extra = 0;
		int nouveau_15_19Extra = 0;
		int ancien_10_14Extra = 0;
		int ancien_15_19Extra = 0;
		
		organisation.add(this.organisationSelect.getId());
		Element element = ielement.getOneElmentByCode("porteEntree");
		Element ageEnrolElement = ielement.getOneElmentByCode("age_enrol");
		//nbreBenefEnrole NOUVEAU
		dataInstances = idataValues.dataAnalysePeriode(organisation, dossierBeneficiare.getId(), dateDebuts, dateFins);
		//nouveau = dataInstances.size();
		
		for(int i = 0;i<dataInstances.size();i++) {
			for(int b =0;b<dataInstances.get(i).getDataValue().size();b++) {
				if(dataInstances.get(i).getDataValue().get(b).getElement().equals(element.getUid())) {
					String porteEntree = dataInstances.get(i).getDataValue().get(b).getValue();
					if(porteEntree.equals("ecole")) {
						for(int interv =0;interv<dataInstances.get(i).getDataValue().size();interv++) {
							for(int eleInter = 0;eleInter <elementsIntervention.size();eleInter++) {
								if(dataInstances.get(i).getDataValue().get(interv).getElement().equals(elementsIntervention.get(eleInter).getUid())) {
									for(int ag =0;ag<dataInstances.get(i).getDataValue().size();ag++) {
										if(dataInstances.get(i).getDataValue().get(ag).getElement().equals(ageEnrolElement.getUid())) {
											int age = Integer.parseInt(dataInstances.get(i).getDataValue().get(ag).getValue());
											if(age < 15) {
												nouveau_10_14++;
											}else {
												nouveau_15_19++;
											}
											break;
										}
									}
								}
							}
						}
						
					}else {
						for(int interv =0;interv<dataInstances.get(i).getDataValue().size();interv++) {
							for(int eleInter = 0;eleInter <elementsIntervention.size();eleInter++) {
								if(dataInstances.get(i).getDataValue().get(interv).getElement().equals(elementsIntervention.get(eleInter).getUid())) {
									for(int ag =0;ag<dataInstances.get(i).getDataValue().size();ag++) {
										if(dataInstances.get(i).getDataValue().get(ag).getElement().equals(ageEnrolElement.getUid())) {
											int age = Integer.parseInt(dataInstances.get(i).getDataValue().get(ag).getValue());
											if(age < 15) {
												nouveau_10_14Extra++;
											}else {
												nouveau_15_19Extra++;
											}
											break;
										}
									}
									break;
								}
							}
						}
					}
					break;
				}
			}
		}
		
		
		//nbreBenefEnrole ANCIEN 
		dataInstances = idataValues.dataAnalysePreview(organisation, dossierBeneficiare.getId(), dateDebuts);
		//ancien = dataInstances.size();
		for(int i = 0;i<dataInstances.size();i++) {
			for(int b =0;b<dataInstances.get(i).getDataValue().size();b++) {
				if(dataInstances.get(i).getDataValue().get(b).getElement().equals(element.getUid())) {
					String porteEntree = dataInstances.get(i).getDataValue().get(b).getValue();
					if(porteEntree.equals("ecole")) {
						for(int interv =0;interv<dataInstances.get(i).getDataValue().size();interv++) {
							for(int eleInter = 0;eleInter <elementsIntervention.size();eleInter++) {
								if(dataInstances.get(i).getDataValue().get(interv).getElement().equals(elementsIntervention.get(eleInter).getUid())) {
									for(int ag =0;ag<dataInstances.get(i).getDataValue().size();ag++) {
										if(dataInstances.get(i).getDataValue().get(ag).getElement().equals(ageEnrolElement.getUid())) {
											int age = Integer.parseInt(dataInstances.get(i).getDataValue().get(ag).getValue());
											if(age < 15) {
												ancien_10_14++;
											}else {
												ancien_15_19++;
											}
											break;
										}
									}
								}
							}
						}
						
					}else {
						for(int interv =0;interv<dataInstances.get(i).getDataValue().size();interv++) {
							for(int eleInter = 0;eleInter <elementsIntervention.size();eleInter++) {
								if(dataInstances.get(i).getDataValue().get(interv).getElement().equals(elementsIntervention.get(eleInter).getUid())) {
									for(int ag =0;ag<dataInstances.get(i).getDataValue().size();ag++) {
										if(dataInstances.get(i).getDataValue().get(ag).getElement().equals(ageEnrolElement.getUid())) {
											int age = Integer.parseInt(dataInstances.get(i).getDataValue().get(ag).getValue());
											if(age < 15) {
												ancien_10_14Extra++;
											}else {
												ancien_15_19Extra++;
											}
											break;
										}
									}
									break;
								}
							}
						}
					}
					break;
				}
			}
		}
	
		total = nouveau_10_14 + nouveau_15_19 + ancien_10_14 + ancien_15_19;
		enregistreRapport("nbreBenefEnroleScolaireActifs");
		//nouveau = nouveauExtra;
		//ancien = ancienExtra;
		//total = totalExtra;
		nouveau_10_14 = nouveau_10_14Extra;
		nouveau_15_19 = nouveau_15_19Extra;
		ancien_10_14 = ancien_10_14Extra;
		ancien_15_19 = ancien_15_19Extra;
		total = nouveau_10_14 + nouveau_15_19 + ancien_10_14 + ancien_15_19;
		enregistreRapport("nbreBenefEnroleExtraScolaireActifs");
		
		
	}

	private void nbreFilleIssusPopulation() {
		List<String> organisation = new ArrayList<String>();
		List<Option> options = new ArrayList<Option>();
		total = 0;
		nouveau_10_14 = 0;
		nouveau_15_19 = 0;
		ancien_10_14 = 0;
		ancien_15_19 = 0;
		
		
		organisation.add(this.organisationSelect.getId());
		Element ageEnrolElement = ielement.getOneElmentByCode("age_enrol");
		//nbreBenefEnrole NOUVEAU
		dataInstances = idataValues.dataAnalysePeriode(organisation, dossierBeneficiare.getId(), dateDebuts, dateFins);
		
		for(int i = 0;i<dataInstances.size();i++) {
			int nbreOption = 0; 
			int nbreObtenu = 0;
			for(int interv =0;interv<elementsInterventionPopCible.size();interv++) {
				options = ioption.OptionOfEnsemble(elementsInterventionPopCible.get(interv).getEnsembleOption().getUid());
				nbreOption += options.size();
				for(int b =0;b<dataInstances.get(i).getDataValue().size();b++) {
					 if(dataInstances.get(i).getDataValue().get(b).getElement().equals(elementsInterventionPopCible.get(interv).getUid())) {
						 String ElementValue = dataInstances.get(i).getDataValue().get(b).getValue();
						 for(int opt = 0; opt<options.size();opt++) {
							 if(ElementValue.indexOf(options.get(opt).getCode()) != -1) {
								 nbreObtenu++;
							 }
						 }
					 }
				}
			}
			for(int ag =0;ag<dataInstances.get(i).getDataValue().size();ag++) {
				if(dataInstances.get(i).getDataValue().get(ag).getElement().equals(ageEnrolElement.getUid())) {
					int age = Integer.parseInt(dataInstances.get(i).getDataValue().get(ag).getValue());
					if(nbreOption == nbreObtenu) {
						if(age < 15) {
							nouveau_10_14++;
						}else {
							nouveau_15_19++;
						}
						break;
					}
				}
			}
		}
		
		
		//BenefEnrole ANCIEN 
		dataInstances = idataValues.dataAnalysePreview(organisation, dossierBeneficiare.getId(), dateDebuts);
		for(int i = 0;i<dataInstances.size();i++) {
			int nbreOption = 0; 
			int nbreObtenu = 0;
			for(int interv =0;interv<elementsInterventionPopCible.size();interv++) {
				options = ioption.OptionOfEnsemble(elementsInterventionPopCible.get(interv).getEnsembleOption().getUid());
				nbreOption += options.size();
				for(int b =0;b<dataInstances.get(i).getDataValue().size();b++) {
					 if(dataInstances.get(i).getDataValue().get(b).getElement().equals(elementsInterventionPopCible.get(interv).getUid())) {
						 String ElementValue = dataInstances.get(i).getDataValue().get(b).getValue();
						 for(int opt = 0; opt<options.size();opt++) {
							 if(ElementValue.indexOf(options.get(opt).getCode()) != -1) {
								 nbreObtenu++;
							 }
						 }
					 }
				}
			}
			for(int ag =0;ag<dataInstances.get(i).getDataValue().size();ag++) {
				if(dataInstances.get(i).getDataValue().get(ag).getElement().equals(ageEnrolElement.getUid())) {
					int age = Integer.parseInt(dataInstances.get(i).getDataValue().get(ag).getValue());
					if(nbreOption == nbreObtenu) {
						if(age < 15) {
							ancien_10_14++;
						}else {
							ancien_15_19++;
						}
						break;
					}
				}
			}
		}
		
	
		total = nouveau_10_14 + nouveau_15_19 + ancien_10_14 + ancien_15_19;
		enregistreRapport("nbreFilleIssusPopulation");
		
		
		
	}

	private void nbreFilleReferePsychoSocial() {
		List<String> organisation = new ArrayList<String>();
		List<Option> options = new ArrayList<Option>();
		total = 0;
		nouveau_10_14 = 0;
		nouveau_15_19 = 0;
		ancien_10_14 = 0;
		ancien_15_19 = 0;
		
		
		organisation.add(this.organisationSelect.getId());
		Element refPsychoElement = ielement.getOneElmentByCode("referencePsychoSocial");
		Element ageEnrolElement = ielement.getOneElmentByCode("age_enrol");
		//nbreBenefEnrole NOUVEAU
		dataInstances = idataValues.dataAnalysePeriode(organisation, dossierBeneficiare.getId(), dateDebuts, dateFins);

		for (int i = 0; i < dataInstances.size(); i++) {
			for (int b = 0; b < dataInstances.get(i).getDataValue().size(); b++) {
				if (dataInstances.get(i).getDataValue().get(b).getElement().equals(refPsychoElement.getUid())) {
					if (dataInstances.get(i).getDataValue().get(b).getValue() != null
							&& !dataInstances.get(i).getDataValue().get(b).getValue().equals("")) {
						for (int ag = 0; ag < dataInstances.get(i).getDataValue().size(); ag++) {
							if (dataInstances.get(i).getDataValue().get(ag).getElement()
									.equals(ageEnrolElement.getUid())) {
								int age = Integer.parseInt(dataInstances.get(i).getDataValue().get(ag).getValue());
								if (age < 15) {
									nouveau_10_14++;
								} else {
									nouveau_15_19++;
								}
								break;

							}
						}
					}
				}
			}
		}
		
		//BenefEnrole ANCIEN 
		dataInstances = idataValues.dataAnalysePreview(organisation, dossierBeneficiare.getId(), dateDebuts);
		for (int i = 0; i < dataInstances.size(); i++) {
			for (int b = 0; b < dataInstances.get(i).getDataValue().size(); b++) {
				if (dataInstances.get(i).getDataValue().get(b).getElement().equals(refPsychoElement.getUid())) {
					if (dataInstances.get(i).getDataValue().get(b).getValue() != null
							&& !dataInstances.get(i).getDataValue().get(b).getValue().equals("")) {
						for (int ag = 0; ag < dataInstances.get(i).getDataValue().size(); ag++) {
							if (dataInstances.get(i).getDataValue().get(ag).getElement()
									.equals(ageEnrolElement.getUid())) {
								int age = Integer.parseInt(dataInstances.get(i).getDataValue().get(ag).getValue());
								if (age < 15) {
									ancien_10_14++;
								} else {
									ancien_15_19++;
								}
								break;

							}
						}
					}
				}
			}
		}
		
		
	
		total = nouveau_10_14 + nouveau_15_19 + ancien_10_14 + ancien_15_19;
		enregistreRapport("nbreFilleReferePsychoSocial");
		
		
		
	}

	private void nbreBeneficiaireCommunicationParentEnfant() {
		List<String> organisation = new ArrayList<String>();
		List<Option> options = new ArrayList<Option>();
		total = 0;
		nouveau_10_14 = 0;
		nouveau_15_19 = 0;
		ancien_10_14 = 0;
		ancien_15_19 = 0;
		
		
		organisation.add(this.organisationSelect.getId());
		Element sinovoyuElement = ielement.getOneElmentByCode("sinovoyu");
		Element ageEnrolElement = ielement.getOneElmentByCode("age_enrol");
		//nbreBenefEnrole NOUVEAU
		dataInstances = idataValues.dataAnalysePeriode(organisation, dossierBeneficiare.getId(), dateDebuts, dateFins);

		for (int i = 0; i < dataInstances.size(); i++) {
			for (int b = 0; b < dataInstances.get(i).getDataValue().size(); b++) {
				if (dataInstances.get(i).getDataValue().get(b).getElement().equals(sinovoyuElement.getUid())) {
					if (dataInstances.get(i).getDataValue().get(b).getValue() != null
							&& !dataInstances.get(i).getDataValue().get(b).getValue().equals("")) {
						for (int ag = 0; ag < dataInstances.get(i).getDataValue().size(); ag++) {
							if (dataInstances.get(i).getDataValue().get(ag).getElement()
									.equals(ageEnrolElement.getUid())) {
								int age = Integer.parseInt(dataInstances.get(i).getDataValue().get(ag).getValue());
								if (age < 15) {
									nouveau_10_14++;
								} else {
									nouveau_15_19++;
								}
								break;

							}
						}
					}
				}
			}
		}
		
		//BenefEnrole ANCIEN 
		dataInstances = idataValues.dataAnalysePreview(organisation, dossierBeneficiare.getId(), dateDebuts);
		for (int i = 0; i < dataInstances.size(); i++) {
			for (int b = 0; b < dataInstances.get(i).getDataValue().size(); b++) {
				if (dataInstances.get(i).getDataValue().get(b).getElement().equals(sinovoyuElement.getUid())) {
					if (dataInstances.get(i).getDataValue().get(b).getValue() != null
							&& !dataInstances.get(i).getDataValue().get(b).getValue().equals("")) {
						for (int ag = 0; ag < dataInstances.get(i).getDataValue().size(); ag++) {
							if (dataInstances.get(i).getDataValue().get(ag).getElement()
									.equals(ageEnrolElement.getUid())) {
								int age = Integer.parseInt(dataInstances.get(i).getDataValue().get(ag).getValue());
								if (age < 15) {
									ancien_10_14++;
								} else {
									ancien_15_19++;
								}
								break;

							}
						}
					}
				}
			}
		}
		
		
	
		total = nouveau_10_14 + nouveau_15_19 + ancien_10_14 + ancien_15_19;
		enregistreRapport("nbreBeneficiaireCommunicationParentEnfant");
		
		
		
	}

	private void nbreParentCommunicationParentEnfant() {
		List<String> organisation = new ArrayList<String>();
		total = 0;
		nouveau_10_14 = 0;
		nouveau_15_19 = 0;
		ancien_10_14 = 0;
		ancien_15_19 = 0;
		
		
		organisation.add(this.organisationSelect.getId());
		Element thematiqueElement = ielement.getOneElmentByCode("thematique");
		Element nomPrenomAutreCibleElement = ielement.getOneElmentByCode("nomPrenomAutreCible");
		//nbreBenefEnrole NOUVEAU
		dataInstances = idataValues.dataAnalysePreview(organisation, groupe.getId(), dateFins);
		for (int i = 0; i < dataInstances.size(); i++) {
			for (int b = 0; b < dataInstances.get(i).getDataValue().size(); b++) {
				if (dataInstances.get(i).getDataValue().get(b).getElement().equals(thematiqueElement.getUid())) {
					
					if (dataInstances.get(i).getDataValue().get(b).getValue().equals("sinovoyu")) {
						for (int pers = 0; pers < dataInstances.get(i).getDataValue().size(); pers++) {
							if(dataInstances.get(i).getDataValue().get(pers).getElement().equals(nomPrenomAutreCibleElement.getUid())) {
								if(dataInstances.get(i).getDataValue().get(pers).getValue() != null && !dataInstances.get(i).getDataValue().get(pers).getValue().equals("")) {
									total++;
								}
							}
						}
					}
				}
			}
		}
			
		enregistreRapport("nbreParentCommunicationParentEnfant");
	}
	
	private void nbreFilleVAD() {
		List<String> organisation = new ArrayList<String>();
		List<BeneficiaireTDO> beneficiaireTDOs = new ArrayList<BeneficiaireTDO>();
		total = 0;
		nouveau_10_14 = 0;
		nouveau_15_19 = 0;
		ancien_10_14 = 0;
		ancien_15_19 = 0;
				
		organisation.add(this.organisationSelect.getId());
		Element idDreamsElement = ielement.getOneElmentByCode("id_dreams");
		
		//BenefEnrole NOUVEAU
		dataInstances = idataValues.dataAnalysePeriode(organisation, vad.getId(), dateDebuts, dateFins);
		beneficiaireTDOs = ibeneficiaire.getBeneficiairePeriode(organisation, dateDebuts, dateFins);
		for (int i = 0; i < dataInstances.size(); i++) {
			for (int b = 0; b < dataInstances.get(i).getDataValue().size(); b++) {
				if (dataInstances.get(i).getDataValue().get(b).getElement().equals(idDreamsElement.getUid())) {
					boolean trouve = false;
					int age = 0;
					for(int benef = 0; benef < beneficiaireTDOs.size(); benef++) {
						if(dataInstances.get(i).getDataValue().get(b).getValue().equals(beneficiaireTDOs.get(benef).getId_dreams())) {
							age = Integer.parseInt(beneficiaireTDOs.get(benef).getAgeEnrolement());
							trouve = true;
						}
					}
					if(trouve) {
						if(age < 15) {
							nouveau_10_14++;
						}else {
							nouveau_15_19++;
						}
					}else {
						if(age < 15) {
							ancien_10_14++;
						}else {
							ancien_15_19++;
						}
					}
					break;
				}
			}
		}
		//BenefEnrole Ancien
		dataInstances = idataValues.dataAnalysePreview(organisation, vad.getId(), dateDebuts);
		beneficiaireTDOs = ibeneficiaire.getBeneficiairePreview(organisation, dateDebuts);
		
		for (int benef = 0; benef < beneficiaireTDOs.size(); benef++) {
			boolean trouve = false;
			int age = 0;
			for (int i = 0; i < dataInstances.size(); i++) {
				for (int b = 0; b < dataInstances.get(i).getDataValue().size(); b++) {
					if (dataInstances.get(i).getDataValue().get(b).getElement().equals(idDreamsElement.getUid())) {
						if (dataInstances.get(i).getDataValue().get(b).getValue()
								.equals(beneficiaireTDOs.get(benef).getId_dreams())) {
							age = Integer.parseInt(beneficiaireTDOs.get(benef).getAgeEnrolement());
							trouve = true;
							break;
						}
						break;
					}
				}
			}
			if (trouve) {
				if (age < 15) {
					ancien_10_14++;
				} else {
					ancien_15_19++;
				}
			}
		}
		total = nouveau_10_14 + nouveau_15_19 + ancien_10_14 + ancien_15_19;
		
		enregistreRapport("nbreFilleVAD");
	}

	private void nbreFilleBesoinEducation() {
		List<String> organisation = new ArrayList<String>();
		List<BeneficiaireTDO> beneficiaireTDOs = new ArrayList<BeneficiaireTDO>();
		total = 0;
		nouveau_10_14 = 0;
		nouveau_15_19 = 0;
		ancien_10_14 = 0;
		ancien_15_19 = 0;
				
		organisation.add(this.organisationSelect.getId());
		Element idDreamsElement = ielement.getOneElmentByCode("id_dreams");
		Element soutienEducatifElement = ielement.getOneElmentByCode("soutienEducatif");
		
		//BenefEnrole NOUVEAU
		dataInstances = idataValues.dataAnalysePeriode(organisation, dossierBeneficiare.getId(), dateDebuts, dateFins);
		beneficiaireTDOs = ibeneficiaire.getBeneficiairePeriode(organisation, dateDebuts, dateFins);
		for (int benef = 0; benef < beneficiaireTDOs.size(); benef++) {
			boolean trouve = false;
			int age = 0;
			for (int i = 0; i < dataInstances.size(); i++) {
				for (int b = 0; b < dataInstances.get(i).getDataValue().size(); b++) {
					if (dataInstances.get(i).getDataValue().get(b).getElement().equals(idDreamsElement.getUid())) {
						if(dataInstances.get(i).getDataValue().get(b).getValue().equals(beneficiaireTDOs.get(benef).getId_dreams())) {
							for (int sout = 0; sout < dataInstances.get(i).getDataValue().size(); sout++) {
								if(dataInstances.get(i).getDataValue().get(sout).getElement().equals(soutienEducatifElement.getUid())) {
									if(dataInstances.get(i).getDataValue().get(sout).getValue() != null && !dataInstances.get(i).getDataValue().get(sout).getValue().equals("")) {
										age = Integer.parseInt(beneficiaireTDOs.get(benef).getAgeEnrolement());
										trouve = true;
									}
								}
							}
							
						}
					}
				}
			}
			if(trouve) {
				if(age < 15) {
					nouveau_10_14++;
				}else {
					nouveau_15_19++;
				}
			}
		}
		//BenefEnrole Ancien
		dataInstances = idataValues.dataAnalysePreview(organisation, dossierBeneficiare.getId(), dateDebuts);
		beneficiaireTDOs = ibeneficiaire.getBeneficiairePreview(organisation, dateDebuts);
		for (int benef = 0; benef < beneficiaireTDOs.size(); benef++) {
			boolean trouve = false;
			int age = 0;
			for (int i = 0; i < dataInstances.size(); i++) {
				for (int b = 0; b < dataInstances.get(i).getDataValue().size(); b++) {
					if (dataInstances.get(i).getDataValue().get(b).getElement().equals(idDreamsElement.getUid())) {
						if(dataInstances.get(i).getDataValue().get(b).getValue().equals(beneficiaireTDOs.get(benef).getId_dreams())) {
							for (int sout = 0; sout < dataInstances.get(i).getDataValue().size(); sout++) {
								if(dataInstances.get(i).getDataValue().get(sout).getElement().equals(soutienEducatifElement.getUid())) {
									if(dataInstances.get(i).getDataValue().get(sout).getValue() != null && !dataInstances.get(i).getDataValue().get(sout).getValue().equals("")) {
										age = Integer.parseInt(beneficiaireTDOs.get(benef).getAgeEnrolement());
										trouve = true;
									}
								}
							}
							
						}
					}
				}
			}
			if(trouve) {
				if (age < 15) {
					ancien_10_14++;
				} else {
					ancien_15_19++;
				}
			}
		}
		
		total = nouveau_10_14 + nouveau_15_19 + ancien_10_14 + ancien_15_19;
		
		enregistreRapport("nbreFilleBesoinEducation");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
