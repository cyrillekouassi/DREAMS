package ci.jsi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("configure");
		/*auth.inMemoryAuthentication().withUser("admin").password("admin1234").roles("ADMIN","USER");
		auth.inMemoryAuthentication().withUser("irc_dreams").password("dreamsirclike").roles("USER");
		auth.inMemoryAuthentication().withUser("EGPAF_dreams").password("dreams_egpaf").roles("USER");
		auth.inMemoryAuthentication().withUser("Jhpiego_dreams").password("likedreamsjhpiego").roles("USER");
		auth.inMemoryAuthentication().withUser("save_dreams").password("dreamslikesave").roles("USER");
		auth.inMemoryAuthentication().withUser("dreams").password("dreamsjsi").roles("USER");*/
		
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.formLogin().loginPage("/login");
		//http.formLogin();
		http.authorizeRequests().antMatchers("/login/**","/_css/**","/_lib/**","/_img/**","/css/**").permitAll();
		//http.authorizeRequests().antMatchers("/","/programme","/organisation","/dataValue","/element","/api","/enrolement/**","/saisie/**").hasRole("USER");
		http.authorizeRequests().anyRequest().authenticated();
		//http.addFilter(new JWTAuthentificationFilter(authenticationManager()));
	}
}
