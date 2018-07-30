package ci.jsi.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Service
public class SecurityController {
	
	
	@RequestMapping(value="/login")
	public String Login() {
		return "login";
	}
	
	@RequestMapping(value="/")
	public String Home() {
		return "redirect:/saisie/index.html";
		
	}
	
	public String infoMe() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
		username = ((UserDetails)principal).getUsername();
		} else {
		username = principal.toString();
		System.out.println("principal.toString: username = "+username);
		}
		return username;
		
		
	}
}
