package cnam.glg204.quiz.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import cnam.glg204.quiz.common.enums.AccountType;
import cnam.glg204.quiz.server.security.RestAccessDeniedHandler;
import cnam.glg204.quiz.server.security.RestAuthenticationFailureHandler;
import cnam.glg204.quiz.server.security.RestAuthenticationSuccessHandler;
import cnam.glg204.quiz.server.security.RestUnauthorizedEntryPoint;

/**
 * 
 * Configuration de l'authentification et de l'attribution des droits.
 * @see <a href="https://samerabdelkafi.wordpress.com/2016/01/25/secure-angularjs-application-with-spring-security/#id11">spring-security</a>
 * @author Pierre Faraco 
 * 
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages ="cnam.glg204")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String REMEMBER_ME_KEY = "rememberme_key";
	 	
	 @Autowired
     private UserDetailsService userDetailsService;
	
	 @Autowired
	 private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;
	 
	 @Autowired
	 private RestAuthenticationSuccessHandler  restAuthenticationSuccessHandler;

	 @Autowired
	 RestAuthenticationFailureHandler restAuthenticationFailureHandler;
	 
	 @Autowired
	 RestAccessDeniedHandler  restAccessDeniedHandler;
	 
	 public SecurityConfig() {
	        super();
	        
	    }
	  
	 @Autowired
	 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(userDetailsService);
	 }
	 
	 /**
	  * 
	  * Configuraton de l'acces aux resourcese html
	  * 
	  */
	 
	 @Override
	 public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers( "/index.html","/indexAuditor.html","/indexProfessor.html", "/app/**","/app/view/forms/**","/css/**",
	                "/fonts/**", "/node_modules/**","/forms/**","/all/**","/messageModal.html","/sessionExpired.html");
	 }
	 
	 /**
	  * 
	  * Configuraton de l'acces aux Web services en fonction des droits utilisateurs.</br>
	  * Configuration du mechanisme qui permet de s'authentifier et de se délogger.</br>
	  *
	  */
	 
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
		
		 
	  http
	    .headers().disable()
	    .csrf().disable()
	    .authorizeRequests()
	    .antMatchers("/all/**").hasAnyAuthority(AccountType.AUDITOR.name(),AccountType.PROFESSOR.name()) 
	    .antMatchers("/professor/**").hasAnyAuthority(AccountType.PROFESSOR.name()) 
	    .antMatchers("/authenticate").permitAll()	
	    .antMatchers("/unsecured/**").permitAll()	
	    .anyRequest().authenticated()
	    .and()
	    .exceptionHandling()
	    .authenticationEntryPoint(restAuthenticationEntryPoint)
	    .accessDeniedHandler(restAccessDeniedHandler)
	    .and()
	    .formLogin()
	    .loginProcessingUrl("/authenticate")
	    .successHandler(restAuthenticationSuccessHandler)
	    .failureHandler(restAuthenticationFailureHandler)
	    .usernameParameter("username")
	    .passwordParameter("password")
	    .permitAll()
	    .and()
	    .logout()
	    .logoutUrl("/logout")
	    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
	    .deleteCookies("JSESSIONID")
	    .permitAll()
	    .and()
	    .sessionManagement()
        .maximumSessions(200)
        .expiredUrl("/sessionExpired.html")
	;
	  	
	 }

	}

