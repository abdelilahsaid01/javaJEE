package com.sid.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity

public class SecutityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dateSource;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
//		auth.inMemoryAuthentication()
//	.withUser("admin").password("{noop}1234").roles("USER","ADMIN").
//	and()
//	.withUser("user").password("{noop}1234").roles("USER");
//		
//
//	}	
		
		auth.jdbcAuthentication()                
        .dataSource(dateSource)                
        .usersByUsernameQuery("select username as principal, password as credentials, enabled from users where username = ?")               
        .authoritiesByUsernameQuery("select username as principal, authority as role from authorities where username = ?")
        .passwordEncoder(new Md5PasswordEncoder())
        .rolePrefix("ROLE_");
	} 
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			
	http
		.formLogin()
			.loginPage("/login")
			.failureUrl("/login?error")
			.permitAll()
		.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login?logout")
			.permitAll();
	
	http
		.csrf().disable()
		.authorizeRequests().antMatchers("/user/*").hasRole("USER").and()
		.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN").and()
		.exceptionHandling().accessDeniedPage("/403");
	
	}
	
	@SuppressWarnings("deprecation")
	public class Md5PasswordEncoder extends MessageDigestPasswordEncoder {

	    public Md5PasswordEncoder() {
	        super("MD5");
	    }
	
}
}	
//	http.authorizeRequests().antMatchers("/index").hasRole("USER");
//	http.authorizeRequests().antMatchers
//	("/form","/save","/edit","/delete").hasRole("ADMIN");
	
//	 http
//     .authorizeRequests()
//         .anyRequest()
//         .fullyAuthenticated()
//         .and()
//     .formLogin()
//         .loginPage("/login")
//         .failureUrl("/login?error")
//         .permitAll()
//         .and()
//     .logout()
//         .logoutUrl("/logout")
//         .logoutSuccessUrl("/login?logout")
//         .permitAll()
//         .and()
//     .csrf();	
