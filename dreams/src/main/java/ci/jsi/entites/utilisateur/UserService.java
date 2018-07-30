package ci.jsi.entites.utilisateur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ci.jsi.security.SecurityController;

@Service
@Transactional
public class UserService implements Iuser {

	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConvert userConvert;
	
	@Autowired
	private SecurityController secu;

	@Override
	public List<UserTDO> getAllUserTDO() {

		return userConvert.getListUserTDO(userRepository.findAll());
	}

	@Override
	public UserTDO getOneUser(String id) {
		System.out.println("Entrer dans getOneUser");
		UserTDO userTDO = null;
		UserApp user = null;
		user = userRepository.getOneUser(id);
		if(user != null) {
			userTDO = userConvert.getOneUserTDO(user);
		}
		return userTDO;
	}

	@Override
	public UserTDO saveUsersTDO(UserTDO userTDO) {
		UserApp user = new UserApp();
		user = userConvert.getOneUser(userTDO);
		if(user == null) {
			return null;
		}
		user = userRepository.save(user);
		userTDO = userConvert.getOneUserTDO(user);
		return userTDO;
	}

	@Override
	public String updateUserTDO(String id, UserTDO userTDO) {
		UserApp user = new UserApp();
		user = userRepository.getOneUser(id);
		if(user == null) {
			return "fail";
		}
		user = userConvert.updateUser(user, userTDO);
		user = userRepository.save(user);
		return "Succes";
	}

	@Override
	public String deleteUser(String id) {
		UserApp user = new UserApp();
		user = userRepository.getOneUser(id);
		if (user != null) {
			userRepository.delete(user);
			return "deleted";
		}
		return "fail";
	}

	@Override
	public String updateUserAcces(String id, UserAcces userAcces) {
		UserApp user = new UserApp();
		user = userRepository.getOneUser(id);
		if (user != null) {
			user = userConvert.updateAcces(user, userAcces);
			userRepository.save(user);
			return "Acces updated";
		}
		return "fail";
	}

	@Override
	public String UserAcces(UserAcces userAcces) {
		UserApp user = new UserApp();
		user = userRepository.getOneUsername(userAcces.getUsername());
		if (user != null) {
			user = userConvert.updateAcces(user, userAcces);
			userRepository.save(user);
			return user.getUid();
		}
		return "fail";
	}

	@Override
	public UserApp getUser(String id) {
		UserApp user = null;
		user = userRepository.getOneUser(id);
		if(user == null) {
			return null;
		}
		return user;
	}

	@Override
	public UserTDO getOneUserByUsername(String username) {
		//System.out.println("Entrer dans getOneUser");
		UserTDO userTDO = null;
		UserApp user = null;
		user = userRepository.findByUsername(username);
		if(user != null) {
			userTDO = userConvert.getOneUserTDO(user);
		}
		return userTDO;
	}

	@Override
	public UserApp getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserInfo getMeInfo() {
		String username = secu.infoMe();
		UserInfo userInfo = null;
		UserApp user = null;
		user = userRepository.findByUsername(username);
		if(user != null) {
			userInfo = userConvert.getMe(user);
		}
		return userInfo;
	}

}
