package com.bci.ejercicio.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtEntryPoint jwtEntryPoint;

	@Autowired
	JwtTokenFilter jwtTokenFilter;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.addFilterAfter(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/userApiRestFul/saveUser").permitAll()
		.antMatchers(HttpMethod.POST, "/userApiRestFul/createRol").permitAll()
		.antMatchers(HttpMethod.POST, "/userApiRestFul/login").permitAll()
		.anyRequest().authenticated();
	}

	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity
				.ignoring()
				.antMatchers("/h2-console/**/**");
	}
	
	
}
