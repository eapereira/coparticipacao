package br.com.spread.qualicorp.coparticipacao.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = LogManager.getLogger(SecurityConfig.class);

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		LOGGER.info("BEGIN");

		authenticationManagerBuilder.authenticationProvider(authenticationProvider);

		LOGGER.info("END");
	}

	/**
	 * 
	 * The configuration ensures that:
	 * <ul>
	 * <li>every request requires the user to be authenticated</li>
	 * <li>form based authentication is supported</li>
	 * <li>HTTP Basic Authentication is supported</li>
	 * </ul>
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		LOGGER.info("BEGIN");

		// require all requests to be authenticated except for the resources
		httpSecurity.authorizeRequests().antMatchers("/javax.faces.resource/**").permitAll().anyRequest()
				.authenticated();

		// login
		httpSecurity.formLogin().loginPage("/index.xhtml").permitAll().successForwardUrl("/pages/home.xhtml")
				.failureUrl("/index.xhtml?error=true");

		/*
		httpSecurity.formLogin().  //login configuration
                loginPage("/index.xhtml").
                loginProcessingUrl("/login").
                usernameParameter("username").
                passwordParameter("password").
                defaultSuccessUrl("/pages/home.xhtml").
                failureUrl("/index.xhtml?error=true");*/
		
		// logout
		httpSecurity.logout().logoutSuccessUrl("/index.xhtml");

		// not needed as JSF 2.2 is implicitly protected against CSRF
		httpSecurity.csrf().disable();

		LOGGER.info("END");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
