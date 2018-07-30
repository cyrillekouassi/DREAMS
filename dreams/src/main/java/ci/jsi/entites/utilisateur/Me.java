package ci.jsi.entites.utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Me {

	@Autowired
	Iuser iuser;
	
	
	@RequestMapping(value="/me")
	public UserInfo getMe() {
		return iuser.getMeInfo();
	}
}
