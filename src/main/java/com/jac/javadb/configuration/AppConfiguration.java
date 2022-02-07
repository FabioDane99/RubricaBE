package com.jac.javadb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jac.javadb.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AppConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

		@Autowired
		private UserDetailsService userDetailsService;

//		@Autowired
		//private CustomDateConverter dateConverter;
		
		@Autowired
		private JwtRequestFilter jwtRequestFilter; //estrapola informazioni

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http
				.csrf().disable() // REST API
				.authorizeRequests()
				.antMatchers("/auth", 
						"/generatePassword", 
						"/utente/register", 
						"/init", 
						"/v2/api-docs",
						"/swagger-ui.html", 
						"/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll() // i path sono accessibili a tutti il filtro non intercetterà la richiesta 
				.anyRequest().authenticated();
				// .and().formLogin().permitAll(); // anche la login

				http.cors();
			
	        // Add a filter to validate the tokens with every request
	        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		}

		@Override
		public void addFormatters(FormatterRegistry registry) {
//	        registry.addConverter(dateConverter);
		}

		@Bean
		public BCryptPasswordEncoder bCryptPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
	    @Bean
	    public AuthenticationManager customAuthenticationManager() throws Exception {
	        return authenticationManager();
	    }

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
		}

	
	
}
