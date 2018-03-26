package com.revature.projecttwo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/employee/me").authenticated().antMatchers("/**")
				.permitAll();

		// http.authorizeRequests().antMatchers("/",
		// "/home").permitAll().anyRequest().authenticated().and().formLogin()
		// .loginPage("/login").permitAll().and().logout().permitAll();
	}

	// User Auth Configure, check user in DB
	// encrypted password as bycrpt
	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception
	// {
	//
	// auth.jdbcAuthentication().dataSource(dataSource)
	// .usersByUsernameQuery("select email, password, true " + " from Resident where
	// email=?")
	// .passwordEncoder(passwordEncoder());
	//
	// }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Bean
	// @Override
	// public UserDetailsService userDetailsService() {
	// UserDetails user =
	// User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
	// .build();
	//
	// return new InMemoryUserDetailsManager(user);
	// }
}
