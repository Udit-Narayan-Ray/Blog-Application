package com.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // prepost is enabled to enable pre-authorize for role specific
													// access
public class SecurityConfig {// extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtFilter jwtFilter;

	/*
	 * @Override public void configure(WebSecurity web) throws Exception { web
	 * .ignoring() .antMatchers(HttpMethod.POST,"/blog/logins/jwt"); //completely
	 * bypass the Spring Security Filter Chain. }
	 */

	/*
	 * // This configuration is done when extended the WebSecurityConfigurerAdapter
	 * Class
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http // for disable cross site request forgery .csrf().disable()
	 * 
	 * //disable cors .cors().disable()
	 * 
	 * // to authorized any HTTP request .authorizeHttpRequests()
	 * 
	 * // set the URL which will have permit at first and after that every URL will
	 * be permitted .antMatchers("/logins/jwt").permitAll()
	 * .antMatchers("/users/post").permitAll()
	 * 
	 * // to authenticate user request with basic authentication
	 * .anyRequest().authenticated() //.and().httpBasic()
	 * 
	 * // configure for authentication entry point
	 * .and().exceptionHandling().authenticationEntryPoint(
	 * jwtAuthenticationEntryPoint)
	 * 
	 * // making session STATELESS
	 * .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.
	 * STATELESS);
	 * 
	 * http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	 * 
	 * }
	 */

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
				// for disable cross site request forgery
				.csrf().disable()

				// disable cors
				.cors().disable()

				// to authorized any HTTP request
				.authorizeHttpRequests()

				// set the URL which will have permit at first and after that every URL will be
				// permitted
				.antMatchers("/logins/jwt").permitAll().antMatchers("/users/post").permitAll()

				// to authenticate user request with basic authentication
				.anyRequest().authenticated() // .and().httpBasic()

				// configure for authentication entry point
				.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)

				// making session STATELESS
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		http.authenticationProvider(daoAuthenticationProvider());

		DefaultSecurityFilterChain securityFilterChain = http.build();

		return securityFilterChain;
	}

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * 
	 * auth.userDetailsService(customUserDetailService).passwordEncoder(
	 * passwordEncoder());
	 * 
	 * }
	 */

	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		provider.setUserDetailsService(this.customUserDetailService);
		provider.setPasswordEncoder(passwordEncoder());

		return provider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

	/*
	 * @Bean
	 * 
	 * @Override public AuthenticationManager authenticationManagerBean() throws
	 * Exception { return super.authenticationManagerBean(); }
	 */

	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager();

	}
}
