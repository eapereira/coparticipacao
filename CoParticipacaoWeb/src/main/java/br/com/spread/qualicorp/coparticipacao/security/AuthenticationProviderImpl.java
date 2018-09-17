package br.com.spread.qualicorp.coparticipacao.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

	private static final Logger LOGGER = LogManager.getLogger(AuthenticationProviderImpl.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public AuthenticationProviderImpl() {
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String password;
		String login;
		UserDetails userDetails;

		try {
			LOGGER.info("BEGIN");

			login = authentication.getName();
			password = authentication.getCredentials().toString();

			userDetails = userDetailsService.loadUserByUsername(login);

			if (userDetails == null) {
				throw new UsernameNotFoundException(String.format("User [%s] not found.", login));
			} else if (!passwordEncoder.matches(password, userDetails.getPassword())) {
				throw new BadCredentialsException("Invalid user/passowrd.");
			}

			LOGGER.debug(
					"User [{}] with password [{}] validated:",
					userDetails.getUsername(),
					userDetails.getPassword());

			/*
			 * use the credentials and authenticate against the third-party
			 * system
			 */
			LOGGER.info("END");
			return new UsernamePasswordAuthenticationToken(
					userDetails.getUsername(),
					userDetails.getPassword(),
					userDetails.getAuthorities());
		} catch (AuthenticationException e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		return UsernamePasswordAuthenticationToken.class.equals(authenticationType);
	}
}
