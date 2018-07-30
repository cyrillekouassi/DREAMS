package ci.jsi.entites.utilisateur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ci.jsi.entites.organisation.OrganisationConvertEntitie;
import ci.jsi.entites.roleUser.IroleUser;
import ci.jsi.entites.roleUser.RoleUserConvertEntitie;
import ci.jsi.initialisation.ConvertDate;
import ci.jsi.initialisation.Uid;

@Service
@Transactional
public class UserConvert {
	
	@Autowired
	private ConvertDate convertDate;
	@Autowired
	private RoleUserConvertEntitie roleUserConvertEntitie;
	@Autowired
	private OrganisationConvertEntitie organisationConvertEntitie;
	@Autowired
	private Uid uid;
	@Autowired
	UserRepository userRepository;
	@Autowired
	IroleUser iroleUser;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserTDO getOneUserTDO (UserApp user) {
		//System.out.println("Entrer dans UserConvert - getOneUserTDO");
		UserTDO userTDO = new UserTDO();
		userTDO.setId(user.getUid());
		userTDO.setUsername(user.getUsername());
		userTDO.setName(user.getName());
		userTDO.setFirtName(user.getFirtName());
		if(user.getDateCreation() != null)
			userTDO.setDateCreation(convertDate.getDateString(user.getDateCreation()));
		if(user.getDateUpdate() != null)
			userTDO.setDateUpdate(convertDate.getDateString(user.getDateUpdate()));
		userTDO.setEmail(user.getEmail());
		userTDO.setTelephone(user.getTelephone());
		userTDO.setFonction(user.getFonction());
		userTDO.setEmployeur(user.getEmployeur());
		userTDO.setDateNaissance(user.getDateNaissance());
		userTDO.setRoleUsers(roleUserConvertEntitie.getUsers(user.getRoleUsers()));
		userTDO.setOrganisations(organisationConvertEntitie.getOrganisations(user.getOrganisations()));
		
		return userTDO;
	}
	
	public List<UserTDO> getListUserTDO(List<UserApp> users){
		List<UserTDO> userTDOs = new ArrayList<UserTDO>();
		for(int i =0; i<users.size(); i++) {
			userTDOs.add(getOneUserTDO(users.get(i)));
		}
		return userTDOs;
		
	}
	
	public UserApp getOneUser(UserTDO userTDO) {
		UserApp user = new UserApp();
		user.setUid(uid.getUid());
		if(userTDO.getUsername() != null) {
			UserApp userExiste = new UserApp();
			userExiste = userRepository.findByUsername(userTDO.getUsername());
			if(userExiste == null) {
				user.setUsername(userTDO.getUsername());
			}else
				return null;
		}else
			return null;
		
		if(userTDO.getPassword() != null) {
			String hashPW = bCryptPasswordEncoder.encode(userTDO.getPassword());
			user.setPassword(hashPW);
			//user.setPassword(userTDO.getPassword());
			user.setDateCreationPassword(new Date());
			user.setDatePasswordUpdate(new Date());
		}else
			return null;
		
		if(userTDO.getName() != null) {
			user.setName(userTDO.getName());
		}else
			return null;
		
		user.setFirtName(userTDO.getFirtName());
		user.setCode(userTDO.getCode());
		user.setDateCreation(new Date());
		user.setDateUpdate(new Date());
		user.setEmail(userTDO.getEmail());
		user.setTelephone(userTDO.getTelephone());
		user.setFonction(userTDO.getFonction());
		user.setEmployeur(userTDO.getEmployeur());
		user.setDateNaissance(userTDO.getDateNaissance());
		
		//user.setRoleUsers(roleUserConvertEntitie.setRoleUser(userTDO.getRoleUsers()));
		user.setRoleUsers(iroleUser.getRolesUsers(userTDO.getRoleUsers()));
		user.setOrganisations(organisationConvertEntitie.setOrganisations(userTDO.getOrganisations()));
		//user.setInstances(instanceConvertEntitie.setInstances(userTDO.getInstances()));
		
		
		return user;
	}
	
	public UserApp updateUser(UserApp user, UserTDO userTDO) {
		
		
		if(userTDO.getUsername() == null) {
			return null;
		}
		if(userTDO.getPassword() != null) {
			String hashPW = bCryptPasswordEncoder.encode(userTDO.getPassword());
			user.setPassword(hashPW);
			//user.setPassword(userTDO.getPassword());
			user.setDatePasswordUpdate(new Date());
		}
		if(user.getDateCreationPassword() == null) {
			user.setDateCreationPassword(new Date());
		}
			
		//user.setUsername(userTDO.getUsername());
		if(userTDO.getName() == null) {
			return null;
		}
		user.setName(userTDO.getName());
			user.setFirtName(userTDO.getFirtName());
			user.setCode(userTDO.getCode());
		user.setDateUpdate(new Date());
			user.setEmail(userTDO.getEmail());
			user.setTelephone(userTDO.getTelephone());
			user.setFonction(userTDO.getFonction());
			user.setEmployeur(userTDO.getEmployeur());
			user.setDateNaissance(userTDO.getDateNaissance());
		if(!userTDO.getRoleUsers().isEmpty()) {
			user.setRoleUsers(roleUserConvertEntitie.setRoleUser(userTDO.getRoleUsers()));
		}else {
			user.setRoleUsers(null);
		}
		if(!userTDO.getOrganisations().isEmpty()) {
			user.setOrganisations(organisationConvertEntitie.setOrganisations(userTDO.getOrganisations()));
		}else {
			user.setOrganisations(null);
		}
		
		return user;
	}

	public UserApp updateAcces(UserApp user, UserAcces userAcces) {
		//String hashPW =	bCryptPasswordEncoder.encode(userAcces.getPassword());
		if(user.getPassword() != null)
			user.setDateCreationPassword(new Date());
		//user.setPassword(hashPW);
		user.setDatePasswordUpdate(new Date());
		return user;
	}
	
	public UserInfo getMe(UserApp user) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(user.getUsername());
		userInfo.setCode(user.getCode());
		userInfo.setFirtName(user.getFirtName());
		userInfo.setId(user.getUid());
		userInfo.setName(user.getName());
		userInfo.setOrganisations(organisationConvertEntitie.getOrganisations(user.getOrganisations()));
		userInfo.setRoleUsers(roleUserConvertEntitie.getUsers(user.getRoleUsers()));
		user.getRoleUsers().forEach(r ->{
			r.getRoledefinie().forEach(rd ->{
				userInfo.getRoleDefinis().add(rd.getAutorisation());
			});
		});
		
		return userInfo;

	}
	
	
}
