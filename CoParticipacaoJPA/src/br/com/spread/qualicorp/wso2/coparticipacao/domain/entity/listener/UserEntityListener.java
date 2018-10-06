package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.UserEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class UserEntityListener extends EntityListener<UserEntity> {

	private static final Logger LOGGER = LogManager.getLogger(UserEntityListener.class);

	@Override
	protected void logProperties(UserEntity entity) {
		LOGGER.debug("Pre-Persist:");
		LOGGER.debug("USER.NM_LOGIN ................. [{}]", entity.getNameLogin());
		LOGGER.debug("USER.DESCR_NAME ............... [{}]", entity.getDescrName());
		
		LOGGER.debug("USER.DT_CREATED ............... [{}]", entity.getCreated());
		LOGGER.debug("USER.DT_ALTERED ............... [{}]", entity.getAltered());
	}

}
