package br.com.spread.qualicorp.coparticipacao.bean;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.spread.qualicorp.coparticipacao.exception.BeanException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.UserService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class AbstractBean {

	private static final Logger LOGGER = LogManager.getLogger(UploadBean.class);

	private static final Long USER_ID_ADMIN = 1l;

	@Autowired
	private UserService userService;

	@PostConstruct
	public void initBean() throws BeanException {

	}

	protected UserUi getUser() throws BeanException {
		UserUi userUi;

		try {
			LOGGER.info("BEGIN");

			userUi = userService.findById(USER_ID_ADMIN);

			LOGGER.info("END");
			return userUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BeanException(e);
		}
	}
}
