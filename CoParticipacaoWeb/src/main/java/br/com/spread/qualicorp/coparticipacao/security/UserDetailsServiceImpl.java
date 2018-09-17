package br.com.spread.qualicorp.coparticipacao.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.UserService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger LOGGER = LogManager.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
		UserDetails userDetails = null;
		UserUi userUi;

		try {
			LOGGER.info("BEGIN");

			userUi = userService.findByLogin(userLogin);

			if (userUi != null) {
				userDetails = new UserDetailsImpl(userUi);
			} else {
				throw new UsernameNotFoundException(String.format("User [%s] not found", userLogin));
			}

			LOGGER.info("END");
			return userDetails;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

}
