package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.EmpresaEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class EmpresaEntityListener extends EntityListener<EmpresaEntity> {

	private static final Logger LOGGER = LogManager.getLogger(EmpresaEntityListener.class);

	@Override
	protected void logProperties(EmpresaEntity empresaEntity) {
		LOGGER.info("Pre-Persist:");

		LOGGER.debug("EMPRESA.NM_EMPRESA: ............ [{}]", empresaEntity.getNameEmpresa());
		LOGGER.debug("EMPRESA.CD_EMPRESA: ............ [{}]", empresaEntity.getCdEmpresa());
		LOGGER.debug("EMPRESA.DT_CREATED: ............ [{}]", empresaEntity.getCreated());
		LOGGER.debug("EMPRESA.DT_ALTERED: ............ [{}]", empresaEntity.getAltered());
	}
}
