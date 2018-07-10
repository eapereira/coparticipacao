package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraResultDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraResultEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class RegraResultDaoImpl extends AbstractDaoImpl<RegraResultEntity>
		implements RegraResultDao {

	private static final Logger LOGGER = LogManager
			.getLogger(RegraResultDaoImpl.class);

}
