package ci.jsi.entites.utilisateur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestService {
	
	/*@Autowired
	private UserRepository userRepository;*/
	@Autowired
	private Iuser iuser;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public List<UserTDO> getAllUser(){
		return iuser.getAllUserTDO();
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public UserTDO getUser(@PathVariable(name="uid")String uid) {
		return iuser.getOneUser(uid);
	}
	@RequestMapping(value="/username/{usename}", method=RequestMethod.GET)
	public UserTDO getUserByUsername(@PathVariable(name="usename")String usename) {
		return iuser.getOneUserByUsername(usename);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public UserTDO saveUser(@RequestBody UserTDO userTDO) {
		return iuser.saveUsersTDO(userTDO);
	}

	@RequestMapping(value="/{uid}",method=RequestMethod.PUT)
	public String updateUser(@PathVariable(name="uid")String uid,@RequestBody UserTDO userTDO) {
		return iuser.updateUserTDO(uid, userTDO);
	}
	
	@RequestMapping(value="{uid}/userAcces",method=RequestMethod.PUT)
	public String updateAcces(@PathVariable(name="uid")String uid,@RequestBody UserAcces userAcces) {
		return iuser.updateUserAcces(uid, userAcces);
	}
	
	@RequestMapping(value="userAcces",method=RequestMethod.POST)
	public String Acces(@RequestBody UserAcces userAcces) {
		return iuser.UserAcces(userAcces);
	}
}
