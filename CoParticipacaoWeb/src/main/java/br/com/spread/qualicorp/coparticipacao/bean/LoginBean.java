package br.com.spread.qualicorp.coparticipacao.bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Controller
@SessionScope
public class LoginBean {
	private static final Logger LOGGER = LogManager.getLogger(LoginBean.class);

	public String login() throws CoParticipacaoException {
		LOGGER.info("BEGIN");

		LOGGER.info("END");
		return "home";
	}

	public String logout() throws CoParticipacaoException {
		LOGGER.info("BEGIN");
		HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false);

		if (httpSession != null) {
			httpSession.invalidate();
		}

		LOGGER.info("END");
		return "logout";
	}
}
