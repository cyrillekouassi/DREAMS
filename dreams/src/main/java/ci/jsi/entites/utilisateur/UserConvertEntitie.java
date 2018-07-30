package ci.jsi.entites.utilisateur;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ci.jsi.initialisation.UidEntitie;


@Service
public class UserConvertEntitie {

	
	private List<UidEntitie> uidEntities;
	private List<UserApp> lesUsers;
	
	private UserRepository userRepository;
	
	public UidEntitie getUser(UserApp user) {
		UidEntitie uidEntitie = new UidEntitie();
		uidEntitie.setId(user.getUid());
		return uidEntitie;
	}
	
	public List<UidEntitie> getUsers(List<UserApp> users) {

		uidEntities = new ArrayList<UidEntitie>();

		for (int i = 0; i < users.size(); i++) {
			uidEntities.add(getUser(users.get(i)));
		}
		
		return uidEntities;
	}
	
	public UserApp setUser(UidEntitie UidEntitie){
		UserApp user = new UserApp();
		user = userRepository.getOneUser(UidEntitie.getId());
		return user;
	}
	
	
	public List<UserApp> setUsers(List<UidEntitie> UidEntities){
		lesUsers = new ArrayList<UserApp>();
		for(int i = 0; i<UidEntities.size();i++) {
			lesUsers.add(setUser(UidEntities.get(i)));
		}
		
		return lesUsers;
	}
	
	
}
