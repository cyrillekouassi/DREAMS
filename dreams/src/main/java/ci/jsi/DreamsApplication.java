package ci.jsi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ci.jsi.entites.element.ElementRepository;
import ci.jsi.entites.organisation.OrganisationRepository;
import ci.jsi.entites.organisationLevel.OrganisationLevelRepository;
import ci.jsi.entites.programme.Iprogramme;
import ci.jsi.entites.programme.ProgrammeRepository;
import ci.jsi.entites.rapport.TraitementIndicateur;
import ci.jsi.entites.roleUser.RoleUser;
import ci.jsi.entites.roleUser.RoleUserRepository;
import ci.jsi.entites.rolesDefinis.IrolesDefinis;
import ci.jsi.entites.rolesDefinis.RolesDefinis;
import ci.jsi.entites.rolesDefinis.RolesDefinisRepository;
import ci.jsi.entites.section.SectionRepository;
import ci.jsi.entites.utilisateur.UserApp;
import ci.jsi.entites.utilisateur.UserRepository;
import ci.jsi.importExport.controller.StorageService;
import ci.jsi.initialisation.Uid;

@SpringBootApplication
public class DreamsApplication implements CommandLineRunner {

	@Autowired
	RolesDefinisRepository rolesDefinieRepository;
	@Autowired
	RoleUserRepository roleUserRepository;
	@Autowired
	SectionRepository sectionRepository;
	@Autowired
	ElementRepository elementRepository;
	@Autowired
	ProgrammeRepository programmeRepository;
	//@Autowired
	//ProgrammeElementRepository programmeElementRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	OrganisationLevelRepository organisationLevelRepository;
	@Autowired
	OrganisationRepository organisationRepository;
	@Autowired
	Uid uid;
	@Autowired
	IrolesDefinis irolesDefinis;
	@Autowired
	Iprogramme iprogramme;
	@Autowired
	TraitementIndicateur traitementIndicateur;
	//@Resource
	//StorageService storageService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DreamsApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		//storageService.deleteAll();
		//storageService.init();
			
		System.out.println("Bonjour le monde");
		
		iprogramme.saveProgramme("Eligibilité des participants", "eligibilite");
		iprogramme.saveProgramme("Dossier d'enrolement du bénéficiaire", "enrolement");
		iprogramme.saveProgramme("Dossier du bénéficiaire", "dossierBeneficiare");
		iprogramme.saveProgramme("Besoins des bénéficiaires", "besoinBeneficiare");
		iprogramme.saveProgramme("Visite à domicile", "vad");
		iprogramme.saveProgramme("Reférence et Contre reférence", "reference");
		iprogramme.saveProgramme("Activité de groupe/individuel", "groupe");
		iprogramme.saveProgramme("Indicateur du rapport", "rapport");
		
		
		irolesDefinis.saveRolesDefinie("affiche_configuration");
		irolesDefinis.saveRolesDefinie("affiche_saisie");
		irolesDefinis.saveRolesDefinie("affiche_analyse");
		irolesDefinis.saveRolesDefinie("creer_modifier_organisation");
		irolesDefinis.saveRolesDefinie("creer_modifier_utilisateur");
		irolesDefinis.saveRolesDefinie("creer_modifier_roles");
		irolesDefinis.saveRolesDefinie("creer_modifier_programme");
		irolesDefinis.saveRolesDefinie("creer_modifier_element");
		irolesDefinis.saveRolesDefinie("creer_modifier_ensembleOption");
		irolesDefinis.saveRolesDefinie("importer_elements_donnees");
		irolesDefinis.saveRolesDefinie("creer_modifier_element");
		irolesDefinis.saveRolesDefinie("creer_modifier_element");
		irolesDefinis.saveRolesDefinie("creer_modifier_element");
		irolesDefinis.saveRolesDefinie("creer_modifier_element");
		irolesDefinis.saveRolesDefinie("creer_modifier_element");
		
		RoleUser roles = roleUserRepository.findByName("superUser");
		if(roles == null) {
			roles = new RoleUser();
			roles.setName("superUser");
			roles.setUid(uid.getUid());
			roles.setDateCreation(new Date());
			roles.setDateUpdate(new Date());
		}
		List<RolesDefinis> roleDefinis = new ArrayList<RolesDefinis>();
		roleDefinis = rolesDefinieRepository.findAll();
		roles.setRoledefinie(roleDefinis);
		roles = roleUserRepository.save(roles);
		
		
		UserApp user = userRepository.findByUsername("admin");
		if(user == null) {
			user = new UserApp();
			user.setUid(uid.getUid());
			user.setUsername("admin");
			user.setDateCreation(new Date());
			user.setDateUpdate(new Date());
		}
		String hashPW = bCryptPasswordEncoder.encode("admin123");
		user.setPassword(hashPW);
		user.setName("admin");
		//user.getRoleUsers().add(roles);
		List<RoleUser> rol = new ArrayList<RoleUser>();
		rol.add(roles);
		user.setRoleUsers(rol);
		user = userRepository.save(user);
		
		
		
		
		traitementIndicateur.genereRapport();
		/*
		RolesDefinis rd1 = new RolesDefinis();
		rd1.setUid(uid.getUid());
		rd1.setAutorisation("supprimer");
		RolesDefinis rd2 = new RolesDefinis();
		rd2.setUid(uid.getUid());
		rd2.setAutorisation("modifier");
		RolesDefinis rd3 = new RolesDefinis();
		rd3.setUid(uid.getUid());
		rd3.setAutorisation("editer");
		
		
		RoleUser admi = new RoleUser();
		admi.setName("admin");
		admi.setUid(uid.getUid());
		admi.setDateCreation(new Date());
		admi.setDateUpdate(new Date());
		admi.getRoledefinie().add(rd1);
		admi.getRoledefinie().add(rd2);
		admi.getRoledefinie().add(rd3);
		
		RoleUser GBD = new RoleUser();
		GBD.setName("GBD");
		GBD.setUid(uid.getUid());
		GBD.setDateCreation(new Date());
		GBD.setDateUpdate(new Date());
		GBD.getRoledefinie().add(rd3);
		
		RoleUser ME = new RoleUser();
		ME.setName("M&E");
		ME.setUid(uid.getUid());
		ME.setDateCreation(new Date());
		ME.setDateUpdate(new Date());
		ME.getRoledefinie().add(rd2);
		ME.getRoledefinie().add(rd3);
		
		rolesDefinieRepository.save(rd1);
		rolesDefinieRepository.save(rd2);
		rolesDefinieRepository.save(rd3);
		roleUserRepository.save(admi);
		roleUserRepository.save(GBD);
		roleUserRepository.save(ME);
		
		String hashPW;
		UserApp suer = new UserApp();
		suer.setUsername("cyrilleUsername1");
		hashPW = bCryptPasswordEncoder.encode("12353pass");
		suer.setPassword(hashPW);
		suer.setDateCreationPassword(new Date());
		suer.setDatePasswordUpdate(new Date());
		suer.setUid(uid.getUid());
		suer.setName("Kouassi");
		suer.setFirtName("cyrille");
		suer.setCode("1234");
		suer.setDateCreation(new Date());
		suer.setDateUpdate(new Date());
		suer.setEmail("cyrillekouassi2002@gmail.com");
		suer.setTelephone("08092496");
		suer.setFonction("Inform");
		suer.setEmployeur("MEASURE");
		suer.setDateNaissance("1986-03-15");
		suer.getRoleUsers().add(admi);
		userRepository.save(suer);
		System.err.println("Fin sauvegarde user");
		
		UserApp gd = new UserApp();
		gd.setUsername("cyrilleGD");
		hashPW = bCryptPasswordEncoder.encode("12353pass");
		gd.setPassword(hashPW);
		gd.setDateCreationPassword(new Date());
		gd.setDatePasswordUpdate(new Date());
		gd.setUid(uid.getUid());
		gd.setName("Kouassi");
		gd.setFirtName("cyrille");
		gd.setCode("123");
		gd.setDateCreation(new Date());
		gd.setDateUpdate(new Date());
		gd.setEmail("cyrillekouassi2002@gmail.com");
		gd.setTelephone("08092496");
		gd.setFonction("Inform");
		gd.setEmployeur("MEASURE");
		gd.setDateNaissance("1986-03-15");
		gd.getRoleUsers().add(GBD);
		userRepository.save(gd);
		System.err.println("Fin sauvegarde gd");*/
		
		/*OrganisationLevel orgLevel = new OrganisationLevel();
		orgLevel.setName("niveau 1");
		orgLevel.setCode("01");
		orgLevel.setLevel(1);
		orgLevel.setDescription("test");
		organisationLevelRepository.save(orgLevel);
		System.err.println("Fin sauvegarde OrganisationLevel");
		
		Organisation ci = new Organisation();
		ci.setUid(uid.getUid());
		ci.setName("Cote D'ivoire");
		ci.setCode("ci");
		ci.setDateCreation(new Date());
		ci.setDateUpdate(new Date());
		ci.setDescription("Niveau superieur");
		ci.setDateCreation(new Date());
		ci.setEmail("ci@ci.org");
		ci.setOrganisationLevels(orgLevel);
		organisationRepository.save(ci);
		System.err.println("Fin sauvegarde Organisation");
		
		Organisation PAbobo = new Organisation();
		PAbobo.setUid(uid.getUid());
		PAbobo.setName("ABOBO");
		PAbobo.setCode("001");
		PAbobo.setDateCreation(new Date());
		PAbobo.setDateUpdate(new Date());
		PAbobo.setDescription("PlateForme ABOBO");
		PAbobo.setDateCreation(new Date());
		PAbobo.setParent(ci);
		organisationRepository.save(PAbobo);
		
		Organisation CSABOBO = new Organisation();
		CSABOBO.setUid(uid.getUid());
		CSABOBO.setName("CS ABOBO");
		CSABOBO.setCode("ABB");
		CSABOBO.setDateCreation(new Date());
		CSABOBO.setDateUpdate(new Date());
		CSABOBO.setDescription("CENTRE SOCIAL ABOBO");
		CSABOBO.setDateCreation(new Date());
		CSABOBO.setParent(PAbobo);
		organisationRepository.save(CSABOBO);
		
		Organisation OngOGRADIE = new Organisation();
		OngOGRADIE.setUid(uid.getUid());
		OngOGRADIE.setName("OGRADIE");
		OngOGRADIE.setCode("263");
		OngOGRADIE.setDateCreation(new Date());
		OngOGRADIE.setDateUpdate(new Date());
		OngOGRADIE.setDescription("ONG OGRADIE");
		OngOGRADIE.setDateCreation(new Date());
		OngOGRADIE.setParent(CSABOBO);
		organisationRepository.save(OngOGRADIE);
		//System.err.println("Fin sauvegarde Organisation");
		
		Organisation PCOCODY = new Organisation();
		PCOCODY.setUid(uid.getUid());
		PCOCODY.setName("COCODY");
		PCOCODY.setCode("004");
		PCOCODY.setDateCreation(new Date());
		PCOCODY.setDateUpdate(new Date());
		PCOCODY.setDescription("PlateForme COCODY");
		PCOCODY.setDateCreation(new Date());
		PCOCODY.setParent(ci);
		organisationRepository.save(PCOCODY);
		
		Organisation CSANONO = new Organisation();
		CSANONO.setUid(uid.getUid());
		CSANONO.setName("CS ANONO");
		CSANONO.setCode("ANN");
		CSANONO.setDateCreation(new Date());
		CSANONO.setDateUpdate(new Date());
		CSANONO.setDescription("CENTRE SOCIAL ANONO");
		CSANONO.setDateCreation(new Date());
		CSANONO.setParent(PCOCODY);
		organisationRepository.save(CSANONO);
		
		Organisation OngMANASSE = new Organisation();
		OngMANASSE.setUid(uid.getUid());
		OngMANASSE.setName("ONG MANASSE");
		OngMANASSE.setCode("287");
		OngMANASSE.setDateCreation(new Date());
		OngMANASSE.setDateUpdate(new Date());
		OngMANASSE.setDescription("ONG MANASSE");
		OngMANASSE.setDateCreation(new Date());
		OngMANASSE.setParent(CSANONO);
		organisationRepository.save(OngMANASSE);
		
		Organisation CSCOCODY = new Organisation();
		CSCOCODY.setUid(uid.getUid());
		CSCOCODY.setName("CS COCODY");
		CSCOCODY.setCode("COD");
		CSCOCODY.setDateCreation(new Date());
		CSCOCODY.setDateUpdate(new Date());
		CSCOCODY.setDescription("CENTRE SOCIAL COCODY");
		CSCOCODY.setDateCreation(new Date());
		CSCOCODY.setParent(PCOCODY);
		organisationRepository.save(CSCOCODY);
		//System.err.println("Fin sauvegarde Organisation");
		
		Organisation PDALOA = new Organisation();
		PDALOA.setUid(uid.getUid());
		PDALOA.setName("DALOA");
		PDALOA.setCode("024");
		PDALOA.setDateCreation(new Date());
		PDALOA.setDateUpdate(new Date());
		PDALOA.setDescription("PlateForme DALOA");
		PDALOA.setDateCreation(new Date());
		PDALOA.setParent(ci);
		organisationRepository.save(PDALOA);
		
		Organisation CSDALOACENTRE = new Organisation();
		CSDALOACENTRE.setUid(uid.getUid());
		CSDALOACENTRE.setName("CS DALOA CENTRE");
		CSDALOACENTRE.setCode("DAC");
		CSDALOACENTRE.setDateCreation(new Date());
		CSDALOACENTRE.setDateUpdate(new Date());
		CSDALOACENTRE.setDescription("CENTRE SOCIAL DALOA CENTRE");
		CSDALOACENTRE.setDateCreation(new Date());
		CSDALOACENTRE.setParent(PDALOA);
		organisationRepository.save(CSDALOACENTRE);
		
		Organisation OngVIF = new Organisation();
		OngVIF.setUid(uid.getUid());
		OngVIF.setName("ONG VIF");
		OngVIF.setCode("349");
		OngVIF.setDateCreation(new Date());
		OngVIF.setDateUpdate(new Date());
		OngVIF.setDescription("ONG VIF");
		OngVIF.setDateCreation(new Date());
		OngVIF.setParent(CSDALOACENTRE);
		organisationRepository.save(OngVIF);
		
		Organisation CSDALOAGARAGE = new Organisation();
		CSDALOAGARAGE.setUid(uid.getUid());
		CSDALOAGARAGE.setName("CS DALOA QUARTIER GARAGE");
		CSDALOAGARAGE.setCode("DQG");
		CSDALOAGARAGE.setDateCreation(new Date());
		CSDALOAGARAGE.setDateUpdate(new Date());
		CSDALOAGARAGE.setDescription("CENTRE SOCIAL DALOA QUARTIER GARAGE");
		CSDALOAGARAGE.setDateCreation(new Date());
		CSDALOAGARAGE.setParent(PDALOA);
		organisationRepository.save(CSDALOAGARAGE);
		//System.err.println("Fin sauvegarde Organisation DALOA");
		
		Organisation PMAN = new Organisation();
		PMAN.setUid(uid.getUid());
		PMAN.setName("MAN");
		PMAN.setCode("040");
		PMAN.setDateCreation(new Date());
		PMAN.setDateUpdate(new Date());
		PMAN.setDescription("PlateForme MAN");
		PMAN.setDateCreation(new Date());
		PMAN.setParent(ci);
		organisationRepository.save(PMAN);
		
		Organisation CSGRANDGBAPLEU = new Organisation();
		CSGRANDGBAPLEU.setUid(uid.getUid());
		CSGRANDGBAPLEU.setName("CS GRAND GBAPLEU");
		CSGRANDGBAPLEU.setCode("GGP");
		CSGRANDGBAPLEU.setDateCreation(new Date());
		CSGRANDGBAPLEU.setDateUpdate(new Date());
		CSGRANDGBAPLEU.setDescription("CENTRE SOCIAL GRAND GBAPLEU");
		CSGRANDGBAPLEU.setDateCreation(new Date());
		CSGRANDGBAPLEU.setParent(PMAN);
		organisationRepository.save(CSGRANDGBAPLEU);
		
		Organisation OngIDE = new Organisation();
		OngIDE.setUid(uid.getUid());
		OngIDE.setName("IDE AFRIQUE MAN");
		OngIDE.setCode("219");
		OngIDE.setDateCreation(new Date());
		OngIDE.setDateUpdate(new Date());
		OngIDE.setDescription("ONG IDE AFRIQUE MAN");
		OngIDE.setDateCreation(new Date());
		OngIDE.setParent(CSGRANDGBAPLEU);
		organisationRepository.save(OngIDE);
		
		Organisation CSMAN = new Organisation();
		CSMAN.setUid(uid.getUid());
		CSMAN.setName("CS MAN");
		CSMAN.setCode("MNA");
		CSMAN.setDateCreation(new Date());
		CSMAN.setDateUpdate(new Date());
		CSMAN.setDescription("CENTRE SOCIAL MAN");
		CSMAN.setDateCreation(new Date());
		CSMAN.setParent(PMAN);
		organisationRepository.save(CSMAN);
		System.err.println("Fin sauvegarde Organisation");
		
		Programme besoin = new Programme();
		besoin.setUid(uid.getUid());
		besoin.setName("Evaluation des besoins");
		besoin.setCode("EvDB");
		programmeRepository.save(besoin);
		
		Programme enrol = new Programme();
		enrol.setUid(uid.getUid());
		enrol.setName("Enrolement Dreams");
		enrol.setCode("enrolement");
		programmeRepository.save(enrol);
		
		System.err.println("Fin initialisation Programme");
		
		UserApp admin = new UserApp();
		admin.setUsername("admin");
		hashPW = bCryptPasswordEncoder.encode("admin1234");
		admin.setPassword(hashPW);
		admin.setDateCreationPassword(new Date());
		admin.setDatePasswordUpdate(new Date());
		admin.setUid(uid.getUid());
		admin.setName("PN-OEV");
		admin.setFirtName("PN-OEV");
		admin.setCode("PN-OEV");
		admin.setDateCreation(new Date());
		admin.setDateUpdate(new Date());
		admin.setEmployeur("PN-OEV");
		admin.getOrganisations().add(ci);
		admin.getRoleUsers().add(admi);
		userRepository.save(admin);
		
		UserApp irc = new UserApp();
		irc.setUsername("irc_dreams");
		hashPW = bCryptPasswordEncoder.encode("dreamsirclike");
		irc.setPassword(hashPW);
		irc.setDateCreationPassword(new Date());
		irc.setDatePasswordUpdate(new Date());
		irc.setUid(uid.getUid());
		irc.setName("IRC");
		irc.setFirtName("IRC");
		irc.setCode("irc_dreams");
		irc.setDateCreation(new Date());
		irc.setDateUpdate(new Date());
		irc.setEmployeur("IRC");
		irc.getOrganisations().add(OngIDE);
		userRepository.save(irc);
		
		UserApp save = new UserApp();
		save.setUsername("save_dreams");
		hashPW = bCryptPasswordEncoder.encode("dreamslikesave");
		save.setPassword(hashPW);
		save.setDateCreationPassword(new Date());
		save.setDatePasswordUpdate(new Date());
		save.setUid(uid.getUid());
		save.setName("Save");
		save.setFirtName("the children");
		save.setCode("save_dreams");
		save.setDateCreation(new Date());
		save.setDateUpdate(new Date());
		save.setEmployeur("Save the children");
		save.getOrganisations().add(OngOGRADIE);
		userRepository.save(save);
		
		UserApp EGPAF = new UserApp();
		EGPAF.setUsername("EGPAF_dreams");
		hashPW = bCryptPasswordEncoder.encode("dreams_egpaf");
		EGPAF.setPassword(hashPW);
		EGPAF.setDateCreationPassword(new Date());
		EGPAF.setDatePasswordUpdate(new Date());
		EGPAF.setUid(uid.getUid());
		EGPAF.setName("EGPAF");
		EGPAF.setFirtName("EGPAF");
		EGPAF.setCode("dreams_egpaf");
		EGPAF.setDateCreation(new Date());
		EGPAF.setDateUpdate(new Date());
		EGPAF.setEmployeur("EGPAF");
		EGPAF.getOrganisations().add(OngMANASSE);
		userRepository.save(EGPAF);
		
		UserApp Jhpiego = new UserApp();
		Jhpiego.setUsername("Jhpiego_dreams");
		hashPW = bCryptPasswordEncoder.encode("likedreamsjhpiego");
		Jhpiego.setPassword(hashPW);
		Jhpiego.setDateCreationPassword(new Date());
		Jhpiego.setDatePasswordUpdate(new Date());
		Jhpiego.setUid(uid.getUid());
		Jhpiego.setName("Jhpiego");
		Jhpiego.setFirtName("Jhpiego");
		Jhpiego.setCode("Jhpiego_dreams");
		Jhpiego.setDateCreation(new Date());
		Jhpiego.setDateUpdate(new Date());
		Jhpiego.setEmployeur("Jhpiego");
		Jhpiego.getOrganisations().add(OngVIF);
		userRepository.save(Jhpiego);
		System.err.println("Fin sauvegarde user");
		
		
		
		
		
		
		
		/*RolesDefinis rd1 = new RolesDefinis("aaaaaa", "aide");
		RolesDefinis rd2 = new RolesDefinis("bbbbbb", "doux");
		RolesDefinis rd3 = new RolesDefinis("cccccc", "bon");

		RoleUser ru1 = new RoleUser("bbaacc", "cse", null, null, new Date(), new Date());

		rd1.getRolesUsers().add(ru1);
		ru1.getRoledefinie().add(rd1);
		rolesDefinieRepository.save(rd1);
		rolesDefinieRepository.save(rd2);
		rolesDefinieRepository.save(rd3);
		roleUserRepository.save(ru1);

		RolesDefinis cp = rolesDefinieRepository.findOne((long) 1);
		System.out.println(cp.getAutorisation());
		System.err.println("Fin ManyToMany. +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Element elmt1 = new Element("aaaaa", "nom1", "n1", new Date(), new Date(), null,"text");
		Element elmt2 = new Element("bbbbb", "nom2", "n2", new Date(), new Date(), null,"text");
		Element elmt3 = new Element("ccccc", "nom3", "n3", new Date(), new Date(), null,"text");
		Element elmt4 = new Element("ddddd", "nom4", "n4", new 
				Date(), new Date(), null,"text");
		Element elmt5 = new Element("eeeee", "nom5", "n5", new Date(), new Date(), null,"text");
		Element elmt6 = new Element("fffff", "nom6", "n6", new Date(), new Date(), null,"text");
		Element elmt7 = new Element("ggggg", "nom7", "n7", new Date(), new Date(), null,"text");
		System.err.println("Fin initialisation Elements");
		
		Programme prog = new Programme("ppp", "test1", "tres");
		Programme prog2 = new Programme("p1", "test2", "tres2");
		System.err.println("Fin initialisation Programme");
		
		ProgrammeElement pgelmt1 = new ProgrammeElement(true);
		pgelmt1.setElement(elmt1);
		pgelmt1.setProgramme(prog);
		prog.getProgrammeElements().add(pgelmt1);
		System.err.println("Fin initialisation ProgrammeElement 1");
		
		ProgrammeElement pgelmt2 = new ProgrammeElement(true);
		pgelmt2.setElement(elmt2);
		pgelmt2.setProgramme(prog);
		prog.getProgrammeElements().add(pgelmt2);
		System.err.println("Fin initialisation ProgrammeElement 2");
		
		ProgrammeElement pgelmt3 = new ProgrammeElement(false);
		pgelmt3.setElement(elmt3);
		pgelmt3.setProgramme(prog);
		prog.getProgrammeElements().add(pgelmt3);
		System.err.println("Fin initialisation ProgrammeElement 3");
		
		
		ProgrammeElement pgelmt4 = new ProgrammeElement(false);
		pgelmt4.setElement(elmt1);
		pgelmt4.setProgramme(prog);
		prog.getProgrammeElements().add(pgelmt4);
		System.err.println("Fin initialisation ProgrammeElement 4");
		
		elementRepository.save(elmt1);
		elementRepository.save(elmt2);
		elementRepository.save(elmt3);
		elementRepository.save(elmt4);
		elementRepository.save(elmt5);
		elementRepository.save(elmt6);
		elementRepository.save(elmt7);
		System.err.println("Fin savegarde Element");
		
		programmeRepository.save(prog);
		programmeRepository.save(prog2);
		System.err.println("Fin savegarde Programme");
		
		
		User suer = new User("cyrilleUsername", "12353pass", new Date(), new Date());
		suer.setUid(uid.getUid());
		suer.setName("Kouassi");
		suer.setFirtName("cyrille");
		suer.setUid("cccca");
		suer.setCode("123");
		suer.setDateCreation(new Date());
		suer.setDateUpdate(new Date());
		suer.setEmail("cyrillekouassi2002@gmail.com");
		suer.setTelephone("08092496");
		suer.setFonction("Inform");
		suer.setEmployeur("MEASURE");
		suer.setDateNaissance("1986-03-15");
		userRepository.save(suer);
		System.err.println("Fin sauvegarde user");
		
		OrganisationLevel orgLevel = new OrganisationLevel();
		orgLevel.setName("niveau 1");
		orgLevel.setCode("01");
		orgLevel.setLevel(1);
		orgLevel.setDescription("test");
		organisationLevelRepository.save(orgLevel);
		System.err.println("Fin sauvegarde OrganisationLevel");
		
		Organisation org = new Organisation();
		org.setUid(uid.getUid());
		org.setName("Cote D'ivoire");
		org.setCode("ci");
		org.setDateCreation(new Date());
		org.setDateUpdate(new Date());
		org.setDescription("Niveau superieur");
		org.setDateCreation(new Date());
		org.setEmail("ci@ci.org");
		org.setOrganisationLevels(orgLevel);
		organisationRepository.save(org);
		System.err.println("Fin sauvegarde Organisation");
		
		Uid uid = new Uid();
		uid.getUid();
		
		*/
	}
}
