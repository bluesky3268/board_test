package com.example.demo.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
  	protected void configure(HttpSecurity http) throws Exception {
  		
//  		http.headers().frameOptions().disable();
//  		http.cors().disable();
//  		http.httpBasic();
  		http.csrf().disable()
  			.authorizeRequests()
	  			.antMatchers("/", "/static/**", "/login", "/css/**", "/js/**", "/error", "/*.ico",
	  					"/joinForm", "/join", "/loginForm", "/login", "/board/**", "/api/**", "/users")
	  			.permitAll()
  			.anyRequest()
  			.authenticated();
//  		.and()
//  			.formLogin()
//	  		.loginPage("/loginForm")
//	  		.loginProcessingUrl("/login")
//	  		.usernameParameter("id")
//	  		.passwordParameter("password")
//	  		.successHandler(new LoginSuccessHandler());

  }
	
//	@Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("*"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

	
}
