package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.EmpresaDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.EmpresaEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public class EmpresaDaoImpl extends AbstractDaoImpl<EmpresaEntity>
		implements
		EmpresaDao {
	private static final Logger LOGGER = LogManager.getLogger(EmpresaDaoImpl.class);

}
