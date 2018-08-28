package ci.jsi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		
	}
}
