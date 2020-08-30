package com.cleitons.silvar.consultaCepApi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cleitons.silvar.consultaCepApi.service.impl.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
		auth.userDetailsService(usuarioService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure( HttpSecurity http ) throws Exception {
		http.
		csrf().disable()
		.authorizeRequests()
		.antMatchers("/endereco/**")
			.hasAnyRole("USER")
		.antMatchers(HttpMethod.POST, "/usuarios/**")
            .permitAll()
        .anyRequest().authenticated()
		.and()
			.httpBasic();
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
        		"/v2/api-docs",
        		"/configuration/ui",
        		"/swagger-resources/**",
        		"/configuration/security",
        		"/swagger-ui.html",
        		"/webjars/**");
    }
}
