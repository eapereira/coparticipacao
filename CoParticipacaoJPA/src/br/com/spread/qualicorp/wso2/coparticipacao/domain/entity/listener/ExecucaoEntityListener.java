package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.listener;

import javax.persistence.PrePersist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ExecucaoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class ExecucaoEntityListener {//extends EntityListener<ExecucaoEntity> {

	private static final Logger LOGGER = LogManager.getLogger(ExecucaoEntityListener.class);
	
	//@Override
	@PrePersist
	public void logProperties(ExecucaoEntity execucaoEntity) {
		LOGGER.debug("Pre-Persist:");
		LOGGER.debug("EXECUCAO.ID_EMPRESA: ............ [{}]", execucaoEntity.getEmpresa().getCdEmpresa());
		LOGGER.debug("EXECUCAO.TP_STATUS: ............. [{}]", execucaoEntity.getExecucaoType().getDescription());
		LOGGER.debug("EXECUCAO.DT_CREATED: ............ [{}]", execucaoEntity.getCreated());
		LOGGER.debug("EXECUCAO.DT_ALTERED: ............ [{}]", execucaoEntity.getAltered());
		LOGGER.debug("EXECUCAO.USER_CREATED: .......... [{}]", execucaoEntity.getUserCreated().getNameLogin());

		if (execucaoEntity.getUserAltered() != null) {
			LOGGER.debug("EXECUCAO.USER_ALTERED: .......... [{}]", execucaoEntity.getUserAltered().getNameLogin());
		}

	}
}
