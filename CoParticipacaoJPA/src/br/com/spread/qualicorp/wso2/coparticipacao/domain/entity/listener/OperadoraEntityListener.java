package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.OperadoraEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class OperadoraEntityListener extends EntityListener<OperadoraEntity> {

	private static final Logger LOGGER = LogManager.getLogger(OperadoraEntityListener.class);

	@Override
	protected void logProperties(OperadoraEntity operadoraEntity) {
		LOGGER.info("Pre-Persist:");

		LOGGER.debug("OPERADORA.NM_OPERADORA: .......... [{}]", operadoraEntity.getNameOperadora());
		LOGGER.debug("OPERADORA.DT_CREATED: ............ [{}]", operadoraEntity.getCreated());
		LOGGER.debug("OPERADORA.DT_ALTERED: ............ [{}]", operadoraEntity.getAltered());
	}

}
