package ci.jsi.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ci.jsi.entites.utilisateur.Iuser;
import ci.jsi.entites.utilisateur.UserApp;

@Service
public class UserDetailsServicesImpl implements UserDetailsService {

	@Autowired
	Iuser iuser;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserApp user = iuser.getUserByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoleUsers().forEach(r ->{
			authorities.add(new SimpleGrantedAuthority(r.getName()));
		});
		
		return new User(user.getUsername(),user.getPassword(),authorities);
	}

}
