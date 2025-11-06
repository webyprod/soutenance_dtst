package com.configuration;

//@EnableWebSecurity
public class SecurityConfig  { // extends WebSecurityConfigurerAdapter

	/*
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable().formLogin().loginProcessingUrl("/login").and().httpBasic().and().authorizeRequests()
        .antMatchers("/login").permitAll().anyRequest().authenticated();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
	}
	 */
}
