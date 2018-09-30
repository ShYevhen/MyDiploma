package net.ukr.shyevhen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	

	@Autowired
	private UserDetailsService uds;
	
	@Autowired
	public void registerAuthentication(AuthenticationManagerBuilder amb) throws Exception{
		amb.userDetailsService(uds).passwordEncoder(bcryptPasswordEncoder());

	}
	
	private BCryptPasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/profile","/profile/orders**", "/profile/favorite**").hasAnyRole("ADMIN", "OPERATOR", "USER")
		.antMatchers(HttpMethod.POST,"/profile/orders**","/books/**","/author**").hasAnyRole("ADMIN", "OPERATOR")
		.antMatchers("/profile/operators**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/author**").hasAnyRole("ADMIN", "OPERATOR")
		.antMatchers("/**").permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/norolepage")
		.and()
		.formLogin()
		.loginPage("/login")
		.failureUrl("/login?error")
		.usernameParameter("user_name")
		.passwordParameter("user_password")
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true);
	}
}
